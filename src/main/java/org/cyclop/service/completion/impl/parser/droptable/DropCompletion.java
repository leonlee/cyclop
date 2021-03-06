package org.cyclop.service.completion.impl.parser.droptable;

import com.google.common.base.Objects;
import org.cyclop.model.CqlCompletion;
import org.cyclop.model.CqlKeyword;
import org.cyclop.model.CqlQuery;
import org.cyclop.service.completion.impl.parser.CompletionHelper;
import org.cyclop.service.completion.impl.parser.MarkerBasedCompletion;

import javax.inject.Inject;
import javax.inject.Named;

/** @author Maciej Miklas */
@Named("droptable.DropCompletion")
class DropCompletion extends MarkerBasedCompletion {

	@Inject
	private CompletionHelper completionHelper;

	public DropCompletion() {
		super(CqlKeyword.Def.DROP_TABLE.value);
	}

	@Override
	public CqlCompletion getCompletion(CqlQuery query) {
		CqlCompletion.Builder completion = completionHelper
				.computeTableNameCompletion(query, CqlKeyword.Def.DROP_TABLE.value, CqlKeyword.Def.IF_EXISTS.value);
		completion.all(CqlKeyword.Def.IF_EXISTS.value);
		return completion.build();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).toString();
	}

}
