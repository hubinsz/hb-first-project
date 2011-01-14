package hubin.watchme.nav;

import hubin.watchme.WatchMeMain;
import hubin.watchme.gui.FollowAppAction;

import java.awt.event.ActionEvent;

/**
 * Switches to the next tab in the frame.
 */
@SuppressWarnings("serial")
public class NextTab extends FollowAppAction {
	public static final String NAME = "nextTab";

	public NextTab(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.NextFile.name"),
				WatchMeMain.getResourceString("action.NextFile.mnemonic"),
				WatchMeMain.getResourceString("action.NextFile.accelerator"),
				WatchMeMain.getIcon(NextTab.class, "action.NextFile.icon"),
				ActionContext.MULTI_FILE);
	}

	/**
	 * Moves to next tab if not at the last tab.
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		int currentIndex = getApp().getTabbedPane().getSelectedIndex();

		if (currentIndex < getApp().getTabbedPane().getTabCount() - 1) {
			getApp().getTabbedPane().setSelectedIndex(currentIndex + 1);
		}
	}
}