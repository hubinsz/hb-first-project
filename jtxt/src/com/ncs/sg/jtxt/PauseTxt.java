package com.ncs.sg.jtxt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PauseTxt {

	private static StringBuffer fileContent;
	private static String srcFilePath = "D:\\sms\\08082010IMH_CSO_SendOut_line.txt";

	// special file need this String to hold them
	// private static StringBuffer oneStrForFile;

	public static void main(String[] args) {
		try {
			fileContent = new StringBuffer();
			pauseSrcFile();
			writeDesFile();
			// System.out.println(oneStrForFile.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void pauseSrcFile() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(srcFilePath)));

		String tempString = null;
		// oneStrForFile = new StringBuffer();
		int line = 1;
		String processedTime = "";
		String createdTime = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MM/dd/yyyy h:mm:ss a");
		SimpleDateFormat simpleDateFormatForOut = new SimpleDateFormat(
				"dd/MM/yyyy-hh:mm:ss-a");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		Date dateProcessed = new Date();
		Date dateCreated = new Date();
		String str1 = "";
		String str2 = "";

		String hold = "";
		long diffTime = 0l;
		while ((tempString = bufferedReader.readLine()) != null) {

			System.out.println("line " + line + ": " + tempString);
			//
			hold = tempString;
			for (int j = 0; j < 2; j++) {
				hold = hold.substring(0, hold.lastIndexOf("|"));
				System.out.println(hold);
			}
			hold = hold.substring(hold.lastIndexOf("|") + 1, hold.length());
			System.out.println("hoyld:" + hold);
			//
			StringBuffer stringBufferdd = new StringBuffer();

			processedTime = tempString.substring(tempString.indexOf("[") + 1,
					tempString.indexOf("]"));
			System.out.println("processedTime:==>" + processedTime);
			createdTime = tempString.substring(tempString
					.indexOf("|iPharm||IMH|") + 13, tempString
					.indexOf("|iPharm||IMH|") + 27);
			System.out.println("createdTime==>" + createdTime);
			try {
				dateProcessed = simpleDateFormat.parse(processedTime);
				dateCreated = simpleDateFormat2.parse(createdTime);
			} catch (Exception e) {
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx8");
			}
			System.out.println(dateProcessed);
			System.out.println(dateCreated);
			System.out.println("YYYYYYYYYYYYYYYYY");
			diffTime = dateProcessed.getTime() - dateCreated.getTime();
			System.out.println(diffTime / 1000);
			line++;
			// make file
			fileContent.append(simpleDateFormatForOut.format(dateProcessed))
					.append(",").append(
							simpleDateFormatForOut.format(dateCreated)).append(
							",").append((diffTime / 1000)).append(",").append(
							hold).append(",08082010IMH_CSO_SendOut.txt").append("\r\n");

		}

		bufferedReader.close();

	}

	private static void writeDesFile() throws Exception {
		System.out.println(fileContent.toString());

		FileWriter fileWriter = new FileWriter("D:\\aab.csv");
		fileWriter.write(fileContent.toString());
		fileWriter.flush();
		fileWriter.close();
	}

}
