/*
 * Copyright (C) 2000-2003 Greg Merrill (greghmerrill@yahoo.com)
 * 
 * This file is part of Follow (http://follow.sf.net).
 * 
 * Follow is free software; you can redistribute it and/or modify it under the
 * terms of version 2 of the GNU General Public License as published by the Free
 * Software Foundation.
 * 
 * Follow is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Follow; if not, write to the Free Software Foundation, Inc., 59 Temple Place,
 * Suite 330, Boston, MA 02111-1307 USA
 */

package hubin.watchme.gui;

import hubin.watchme.WatchMeMain;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Action which deletes the contents of the currently followed file.
 * 
 * @author <a href="mailto:greghmerrill@yahoo.com">Greg Merrill</a>
 */
public class Delete extends FollowAppAction
{
	public static final String NAME = "delete";
	private Logger log = Logger.getLogger(Delete.class.getName());

	public Delete(WatchMeMain app) throws IOException
	{
		super(app, WatchMeMain.getResourceString("action.Delete.name"),
				WatchMeMain.getResourceString("action.Delete.mnemonic"),
				WatchMeMain.getResourceString("action.Delete.accelerator"),
				WatchMeMain.getIcon(Delete.class, "action.Delete.icon"),
				ActionContext.SINGLE_FILE);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (getApp().getAttributes().confirmDelete())
		{
			DisableableConfirm confirm = new DisableableConfirm(getApp().getFrame(),
					WatchMeMain.getResourceString("dialog.confirmDelete.title"),
					WatchMeMain.getResourceString("dialog.confirmDelete.message"),
					WatchMeMain.getResourceString("dialog.confirmDelete.confirmButtonText"),
					WatchMeMain.getResourceString("dialog.confirmDelete.doNotConfirmButtonText"),
					WatchMeMain.getResourceString("dialog.confirmDelete.disableText"));
			confirm.pack();
			confirm.setVisible(true);
			if (confirm.markedDisabled())
			{
				getApp().getAttributes().setConfirmDelete(false);
			}
			if (confirm.markedConfirmed())
			{
				performDelete();
			}
		}
		else
		{
			performDelete();
		}
	}

	private void performDelete()
	{
		getApp().setCursor(Cursor.WAIT_CURSOR);
		FileFollowingPane fileFollowingPane = getApp().getSelectedFileFollowingPane();
		try
		{
			fileFollowingPane.clear();
		}
		catch (IOException ioe)
		{
			log.log(Level.SEVERE, "IOException in Delete", ioe);
			getApp().setCursor(Cursor.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(getApp().getFrame(),
					WatchMeMain.getResourceString("message.unableToDelete.text"),
					WatchMeMain.getResourceString("message.unableToDelete.title"),
					JOptionPane.WARNING_MESSAGE);
		}
		getApp().setCursor(Cursor.DEFAULT_CURSOR);
	}
}