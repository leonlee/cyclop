package org.cyclop.service.completion.impl.parser.insert;

import com.google.common.base.Objects;
import org.cyclop.model.CqlCompletion;
import org.cyclop.model.CqlKeyword;
import org.cyclop.model.CqlQuery;
import org.cyclop.service.completion.impl.parser.CompletionHelper;
import org.cyclop.service.completion.impl.parser.MarkerBasedCompletion;

import javax.inject.Inject;
import javax.inject.Named;

/** @author Maciej Miklas */
@Named("insert.TableNameCompletion")
class TableNameCompletion extends MarkerBasedCompletion {

	@Inject
	private CompletionHelper completionHelper;

	public TableNameCompletion() {
		super(CqlKeyword.Def.INSERT_INTO.value);
	}

	@Override
	public CqlCompletion getCompletion(CqlQuery query) {
		CqlCompletion.Builder completion = completionHelper
				.computeTableNameCompletion(query, CqlKeyword.Def.INSERT_INTO.value);
		return completion.build();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).toString();
	}

}
