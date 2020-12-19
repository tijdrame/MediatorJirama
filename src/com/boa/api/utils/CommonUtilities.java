package com.boa.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommonUtilities {
	
	public static String NameCurrentTraceFile = "logJirama.log";
	//"/h2db/logsISO/EPAYUG_"+CommonUtilities.now(Date)+".log";//"/home/adminboa/serveurWSO2/wso2esb-5.0.0/repository/components/lib/config/epay/epayUG";
	public static File CurrentTraceFile = new File(NameCurrentTraceFile);

	public static String WriteInFile(String FileName, String LineToWrite) throws IOException {
		FileWriter writer = null;
		String message = null;
		try {
			writer = new FileWriter(FileName, true);
			writer.write(LineToWrite, 0, LineToWrite.length());
			writer.append("\n");

		} catch (IOException ex) {
			message = ex.getMessage();
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

		return message;
	}
}
