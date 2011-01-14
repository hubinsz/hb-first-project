package hubin.watchme.nav;

import hubin.watchme.WatchMeMain;
import hubin.watchme.gui.FollowAppAction;

import java.awt.event.ActionEvent;

/**
 * Switches to the previous tab in the frame.
 */
@SuppressWarnings("serial")
public class PreviousTab extends FollowAppAction {
	public static final String NAME = "previousTab";

	public PreviousTab(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.PreviousFile.name"),
				WatchMeMain.getResourceString("action.PreviousFile.mnemonic"),
				WatchMeMain
						.getResourceString("action.PreviousFile.accelerator"),
				WatchMeMain.getIcon(PreviousTab.class,
						"action.PreviousFile.icon"), ActionContext.MULTI_FILE);
	}

	/**
	 * Moves to next tab if not at the last tab.
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		int currentIndex = getApp().getTabbedPane().getSelectedIndex();
		if (currentIndex > 0) {
			getApp().getTabbedPane().setSelectedIndex(currentIndex - 1);
		}
	}
}