package org.cyclop.common;

import org.apache.commons.lang.StringUtils;
import org.cyclop.model.CqlKeySpace;
import org.cyclop.model.CqlKeyword;
import org.cyclop.model.CqlQuery;
import org.cyclop.model.CqlTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author Maciej Miklas */
public class QueryHelper {

	private final static Logger LOG = LoggerFactory.getLogger(QueryHelper.class);

	public static CqlKeySpace extractSpace(CqlQuery query) {
		String cqlLc = query.partLc.replaceAll("[;]", "");
		if (!cqlLc.startsWith("use")) {
			return null;
		}

		String space = cqlLc.substring(3, cqlLc.length()).trim();
		space = StringUtils.trimToNull(space);
		if (space == null) {
			return null;
		}
		CqlKeySpace exspace = new CqlKeySpace(space);
		LOG.debug("Extrancted space {} from {}", exspace, query);
		return exspace;
	}

	public static CqlTable extractTableName(CqlKeyword cqlKeyword, CqlQuery query) {
		String cqlLc = query.partLc;
		int kwStart = cqlLc.indexOf(cqlKeyword.valueSp);
		if (kwStart == -1) {
			return null;
		}
		kwStart += cqlKeyword.valueSp.length();

		int end = cqlLc.indexOf(" ", kwStart + 1);
		if (end == -1) {
			end = cqlLc.length();
		}

		String candidate = cqlLc.substring(kwStart, end);
		candidate = StringUtils.trimToNull(candidate);
		if (candidate == null) {
			return null;
		}

		// check whether we have table with keyspace
		CqlTable result = null;
		if (candidate.contains(".") && !candidate.endsWith(".")) {
			String[] talStr = candidate.split("[.]");
			String keyspaceStr = StringUtils.trimToNull(talStr[0]);
			String tableStr = StringUtils.trimToNull(talStr[1]);
			if (tableStr == null) {
				result = null;
			} else {
				if (keyspaceStr == null) {
					result = new CqlTable(candidate);
				} else {
					result = new CqlTable(keyspaceStr, tableStr);
				}
			}

		} else {
			result = new CqlTable(candidate);
		}

		LOG.debug("Extracted table {} from {}, {}", result, cqlKeyword, query);
		return result;
	}

	public static CqlKeySpace extractKeyspace(CqlQuery query, CqlKeyword... cqlKeyword) {
		for (CqlKeyword kw : cqlKeyword) {
			CqlKeySpace keySpace = extractKeyspaceSingle(query, kw);
			if (keySpace != null) {
				LOG.debug("Extracted {} from {} for {}", keySpace, query, kw);
				return keySpace;
			}
		}
		return null;
	}

	private static CqlKeySpace extractKeyspaceSingle(CqlQuery query, CqlKeyword cqlKeyword) {
		String cqlLc = query.partLc;
		int kwStart = cqlLc.indexOf(cqlKeyword.valueSp);
		if (kwStart == -1) {
			return null;
		}
		kwStart += cqlKeyword.valueSp.length();

		int end = cqlLc.indexOf(".", kwStart + 1);
		if (end == -1) {
			return null;
		}

		String candidate = cqlLc.substring(kwStart, end);
		candidate = StringUtils.trimToNull(candidate);
		if (candidate == null) {
			return null;
		}

		CqlKeySpace space = new CqlKeySpace(candidate);
		return space;
	}
}
