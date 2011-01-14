package hubin.watchme.search;

import hubin.watchme.WatchMeMain;
import hubin.watchme.gui.FollowAppAction;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Find extends FollowAppAction {
	public static final String NAME = "find";

	private FindDialog dialog;

	public Find(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.Find.name"),
				WatchMeMain.getResourceString("action.Find.mnemonic"),
				WatchMeMain.getResourceString("action.Find.accelerator"),
				WatchMeMain.getIcon(Find.class, "action.Find.icon"),
				ActionContext.SINGLE_FILE);
	}

	public void actionPerformed(ActionEvent e) {
		getApp().setCursor(Cursor.WAIT_CURSOR);
		if (dialog == null) {
			dialog = new FindDialog(this);
			dialog.setLocationRelativeTo(getApp().getFrame());
			dialog.setLocation(100, 100);
			dialog.pack();
		}
		dialog.initFocus();
		dialog.setVisible(true);
		getApp().setCursor(Cursor.DEFAULT_CURSOR);
	}
}