package org.cyclop.service.completion.impl.parser.dropkeyspace;

import org.cyclop.model.CqlKeyword;
import org.cyclop.model.CqlQueryType;
import org.cyclop.service.completion.impl.parser.CqlPartCompletion;
import org.cyclop.service.completion.impl.parser.DecisionListSupport;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/** @author Maciej Miklas */
@Named
class DropKeyspaceDecisionListSupport implements DecisionListSupport {

	private final CqlKeyword supports = CqlKeyword.Def.DROP_KEYSPACE.value;

	private CqlPartCompletion[][] decisionList;

	@Inject
	DropCompletion dropCompletion;

	@PostConstruct
	public void init() {
		decisionList = new CqlPartCompletion[][]{{dropCompletion}};
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
		return CqlQueryType.DROP_KEYSPACE;
	}

}
