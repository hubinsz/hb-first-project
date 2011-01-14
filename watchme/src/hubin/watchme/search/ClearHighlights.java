package hubin.watchme.search;

import hubin.watchme.WatchMeMain;
import hubin.watchme.gui.FileFollowingPane;
import hubin.watchme.gui.FollowAppAction;

import java.awt.event.ActionEvent;

/**
 * Action which clears the highlighted search in the current pane
 */
@SuppressWarnings("serial")
public class ClearHighlights extends FollowAppAction {
	public static final String NAME = "clearHighlights";

	public ClearHighlights(WatchMeMain app) {
		super(
				app,
				WatchMeMain.getResourceString("action.ClearHighlights.name"),
				WatchMeMain
						.getResourceString("action.ClearHighlights.mnemonic"),
				WatchMeMain
						.getResourceString("action.ClearHighlights.accelerator"),
				ActionContext.SINGLE_FILE);
	}

	public void actionPerformed(ActionEvent e) {
		// get the current selected tab
		FileFollowingPane pane = getApp().getSelectedFileFollowingPane();
		// search the tab with the given text
		SearchableTextPane textArea = pane.getTextPane();
		textArea.removeHighlights();
	}
}