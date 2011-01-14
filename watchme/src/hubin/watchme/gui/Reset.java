// Copyright (C) 2000 Greg Merrill (greghmerrill@yahoo.com)
// Distributed under the terms of the GNU General Public License (version 2)
// For details on the GNU GPL, please visit http://www.gnu.org/copyleft/gpl.html
// To find out more about this and other free software by Greg Merrill,
// please visit http://gregmerrill.imagineis.com

package hubin.watchme.gui;

import hubin.watchme.WatchMeMain;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Action which restarts the currently selected followed file.
 * 
 * @author <a href="mailto:greghmerrill@yahoo.com">Greg Merrill</a>
 */
public class Reset extends FollowAppAction
{
	public static final String NAME = "reset";

	public Reset(WatchMeMain app) throws IOException
	{
		super(app, WatchMeMain.getResourceString("action.Reset.name"),
				WatchMeMain.getResourceString("action.Reset.mnemonic"),
				WatchMeMain.getResourceString("action.Reset.accelerator"),
				WatchMeMain.getIcon(Reset.class, "action.Reset.icon"),
				ActionContext.SINGLE_FILE);
	}

	public void actionPerformed(ActionEvent e)
	{
		FileFollowingPane fileFollowingPane = getApp().getSelectedFileFollowingPane();
		if (fileFollowingPane != null)
		{
			fileFollowingPane.restartFollowing();
		}
	}
}