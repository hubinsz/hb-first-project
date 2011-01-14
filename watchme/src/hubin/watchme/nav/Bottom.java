package hubin.watchme.nav;

import hubin.watchme.WatchMeMain;
import hubin.watchme.gui.FileFollowingPane;
import hubin.watchme.gui.FollowAppAction;
import hubin.watchme.search.SearchableTextPane;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JViewport;

/**
 * Action which jumps to the last line of the currently followed file.
 */
@SuppressWarnings("serial")
public class Bottom extends FollowAppAction {
	public static final String NAME = "bottom";

	public Bottom(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.Bottom.name"),
				WatchMeMain.getResourceString("action.Bottom.mnemonic"),
				WatchMeMain.getResourceString("action.Bottom.accelerator"),
				WatchMeMain.getIcon(Bottom.class, "action.Bottom.icon"),
				ActionContext.SINGLE_FILE);
	}

	public void actionPerformed(ActionEvent e) {
		FileFollowingPane fileFollowingPane = getApp()
				.getSelectedFileFollowingPane();
		SearchableTextPane textArea = fileFollowingPane.getTextPane();
		textArea.setCaretPosition(textArea.getDocument().getLength());
		JViewport viewport = fileFollowingPane.getViewport();
		int y = (int) (viewport.getViewSize().getHeight() - viewport
				.getExtentSize().getHeight());
		Point bottomPosition = new Point(0, y);
		viewport.setViewPosition(bottomPosition);
		viewport.revalidate();
	}
}