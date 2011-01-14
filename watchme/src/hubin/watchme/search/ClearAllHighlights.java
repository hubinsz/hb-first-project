package hubin.watchme.search;

import hubin.watchme.WatchMeMain;
import hubin.watchme.gui.FileFollowingPane;
import hubin.watchme.gui.FollowAppAction;

import java.awt.event.ActionEvent;

/**
 * Action which clears the highlighted search in all panes
 */
@SuppressWarnings("serial")
public class ClearAllHighlights extends FollowAppAction {
	public static final String NAME = "clearAllHighlights";

	public ClearAllHighlights(WatchMeMain app) {
		super(
				app,
				WatchMeMain.getResourceString("action.ClearAllHighlights.name"),
				WatchMeMain
						.getResourceString("action.ClearAllHighlights.mnemonic"),
				WatchMeMain
						.getResourceString("action.ClearAllHighlights.accelerator"),
				ActionContext.MULTI_FILE);
	}

	public void actionPerformed(ActionEvent e) {
		for (FileFollowingPane pane : getApp().getFileToFollowingPaneMap()
				.values()) {
			// get the current selected tab
			// search the tab with the given text
			SearchableTextPane textArea = pane.getTextPane();
			textArea.removeHighlights();
		}
	}
}