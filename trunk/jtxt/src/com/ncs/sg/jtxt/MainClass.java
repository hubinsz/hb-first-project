package com.ncs.sg.jtxt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainClass {

	private static String fileContent;
	private static String srcFilePath="D:\\sms\\08082010IMH_CSO_SendOut.txt";
	//special file need this String to hold them
	private static StringBuffer oneStrForFile;
	
	public static void main(String[] args) {
		try {
			pauseSrcFile();
			//writeDesFile();
			System.out.println(oneStrForFile.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void pauseSrcFile() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new FileReader( new File(srcFilePath)));
		
		String tempString = null;
		oneStrForFile = new StringBuffer();
        //int line = 1;
        
        while ((tempString = bufferedReader.readLine()) != null) {
            
            oneStrForFile.append(tempString);
            
        }
        bufferedReader.close();
        
   
        
        /**/
//        while ((tempString = bufferedReader.readLine()) != null) {
//            
//            System.out.println("line " + line + ": " + tempString);
//            line++;
//        }
//        bufferedReader.close();
		
	}

	private static void writeDesFile() {
		
	}
	
	


}
