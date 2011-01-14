package hubin.watchme.nav;

import hubin.watchme.WatchMeMain;
import hubin.watchme.gui.FileFollowingPane;
import hubin.watchme.gui.FollowAppAction;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JViewport;

/**
 * Action which jumps to the last line of the currently followed file.
 */
@SuppressWarnings("serial")
public class Top extends FollowAppAction {
	public static final String NAME = "top";

	public Top(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.Top.name"),
				WatchMeMain.getResourceString("action.Top.mnemonic"),
				WatchMeMain.getResourceString("action.Top.accelerator"),
				WatchMeMain.getIcon(Top.class, "action.Top.icon"),
				ActionContext.SINGLE_FILE);
	}

	public void actionPerformed(ActionEvent e) {
		FileFollowingPane fileFollowingPane = getApp()
				.getSelectedFileFollowingPane();
		fileFollowingPane.getTextPane().setCaretPosition(0);
		JViewport viewport = fileFollowingPane.getViewport();
		viewport.setViewPosition(new Point(0, 0));
		viewport.revalidate();
	}
}