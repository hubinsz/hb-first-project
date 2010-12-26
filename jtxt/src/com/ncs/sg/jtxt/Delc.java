package com.ncs.sg.jtxt;

import java.io.File;

public class Delc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("C:/Documents and Settings/hubin/Local Settings/Temporary Internet Files/iPharmWeb/");
		System.out.println(file);
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		file.delete();
	}

}
