package org.cyclop.service.completion.impl.parser.altertable;

import org.cyclop.model.CqlKeyword;
import org.cyclop.model.CqlNotSupported;
import org.cyclop.model.CqlQueryType;
import org.cyclop.service.completion.impl.parser.CqlPartCompletion;
import org.cyclop.service.completion.impl.parser.DecisionListSupport;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/** @author Maciej Miklas */
@Named
class AlterTableDecisionListSupport implements DecisionListSupport {

	private final CqlKeyword supports = new CqlNotSupported("alter table");

	private CqlPartCompletion[][] decisionList;

	@Inject
	AlterCompletion alterCompletion;

	@PostConstruct
	public void init() {
		decisionList = new CqlPartCompletion[][]{{alterCompletion}};
	}

	@Override
	public CqlPartCompletion[][] getDecisionList() {
		return decisionList;
	}

	@Override
	public CqlKeyword supports() {
		return supports;
	}

	@Override
	public CqlQueryType queryName() {
		return CqlQueryType.ALTER_TABLE;
	}

}
