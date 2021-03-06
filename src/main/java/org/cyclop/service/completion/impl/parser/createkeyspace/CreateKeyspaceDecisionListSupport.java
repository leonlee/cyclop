package org.cyclop.service.completion.impl.parser.createkeyspace;

import org.cyclop.model.CqlKeyword;
import org.cyclop.model.CqlQueryType;
import org.cyclop.service.completion.impl.parser.CqlPartCompletion;
import org.cyclop.service.completion.impl.parser.DecisionListSupport;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/** @author Maciej Miklas */
@Named
class CreateKeyspaceDecisionListSupport implements DecisionListSupport {

	private final CqlKeyword supports = CqlKeyword.Def.CREATE_KEYSPACE.value;

	private CqlPartCompletion[][] decisionList;

	@Inject
	private AfterKeyspaceNameCompletion afterKeyspaceNameCompletion;

	@Inject
	private WithCompletion withCompletion;

	@PostConstruct
	public void init() {
		decisionList = new CqlPartCompletion[][]{{afterKeyspaceNameCompletion}, {withCompletion}};
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
		return CqlQueryType.CREATE_KEYSPACE;
	}

}
