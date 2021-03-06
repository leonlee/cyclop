package org.cyclop.service.completion.impl.parser.delete;

import com.google.common.base.Objects;
import org.cyclop.model.CqlCompletion;
import org.cyclop.model.CqlKeyword;
import org.cyclop.model.CqlQuery;
import org.cyclop.service.completion.impl.parser.CompletionHelper;
import org.cyclop.service.completion.impl.parser.MarkerBasedCompletion;

import javax.inject.Inject;
import javax.inject.Named;

/** @author Maciej Miklas */
@Named("delete.FromClauseCompletion")
class FromClauseCompletion extends MarkerBasedCompletion {

	@Inject
	protected CompletionHelper completionHelper;

	public FromClauseCompletion() {
		super(CqlKeyword.Def.FROM.value);
	}

	@Override
	public CqlCompletion getCompletion(CqlQuery query) {
		CqlCompletion.Builder completion = completionHelper
				.computeTableNameCompletion(query, CqlKeyword.Def.FROM.value);
		return completion.build();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).toString();
	}

}
