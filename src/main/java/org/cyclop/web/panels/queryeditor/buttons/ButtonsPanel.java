package org.cyclop.web.panels.queryeditor.buttons;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.cyclop.web.components.buttons.StateButton;

/** @author Maciej Miklas */
public class ButtonsPanel extends Panel {

	private static final JavaScriptResourceReference JS_BUTTONS = new JavaScriptResourceReference(ButtonsPanel.class,
			"buttons.js");

	public ButtonsPanel(String id, final ButtonListener buttonListener, boolean completionPressed) {
		super(id);
		setRenderBodyOnly(true);

		AjaxFallbackLink<Void> addToFavourites = new AjaxFallbackLink<Void>("addToFavourites") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				// buttonListener.onClickExecCql(target);
			}
		};
		add(addToFavourites);
		addToFavourites.setVisible(false);

		AjaxFallbackLink<Void> execQuery = new AjaxFallbackLink<Void>("execQuery") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				buttonListener.onClickExecCql(target);
				target.appendJavaScript("queryExecutedResponse()");
			}
		};
		add(execQuery);

		AjaxFallbackLink<Void> exportQueryResult = new AjaxFallbackLink<Void>("exportQueryResult") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				buttonListener.onClickQueryResultExport(target);
			}
		};
		add(exportQueryResult);

		AjaxFallbackLink<Void> completion = new StateButton("completion", completionPressed, "btn btn-sm btn-primary",
				"btn btn-sm btn-primary active") {
			@Override
			protected void onClick(AjaxRequestTarget target, boolean pressed) {
				buttonListener.onClickCompletion(target, pressed);
			}
		};
		add(completion);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptReferenceHeaderItem.forReference(JS_BUTTONS));
	}
}
