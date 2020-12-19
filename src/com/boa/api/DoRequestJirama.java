package com.boa.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boa.api.utils.CommonUtilities;

/**
 * DoRequestJirama
 */
public class DoRequestJirama {
    //private final Logger log = LoggerFactory.getLogger(DoRequestJirama.class);
    

    public String sendRequest(String request, String urlClient, String typeApplication) throws IOException{
    	String result = "";
        try {
        	CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "request= "+request);
        	CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "urlClient= "+urlClient);
        	CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "typeApplication= "+typeApplication);
        	System.out.println("requ==="+request);
        	System.out.println("typApp==="+typeApplication);
        	System.out.println("url==="+urlClient);
            URL url = new URL(urlClient);
            //http://192.168.222.95/WEBSERVICE_ORACLE_WEB/awws/WebservicePlus.awws
            //http://10.132.4.155:8088/mockWebservicePlusSOAPBinding
            String typApp = typeApplication.equalsIgnoreCase("XML")?"text/xml":"text/json";
            CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "after call url");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", typApp);
            conn.setRequestProperty("Accept", typApp);

            CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "xml to send ========== =>>>"+request);
            
            OutputStream os  = conn.getOutputStream();
            byte[] postDataBytes = request.getBytes();
            

            os.write(postDataBytes);
            os.flush();

            BufferedReader br = null;
            System.out.println("date="+new Date());
            CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "Date=="+new Date());
            CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "resp code  ="+conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()), 4096);
                CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "content  ="+conn.getContent().toString());
                String ligne = "";
                ligne = br.readLine();
                while (ligne != null) {
            		result += ligne;
                    ligne = br.readLine();
                    
               }
                CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "result end ="+result);
                System.out.println("res end===="+result);
                os.close();
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                //log.info("Error ========== [{}]", br.readLine());
                CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "Error  ="+br.readLine());
                result = br.readLine();
                os.close();
            }

        } catch (Exception e) {
            //log.error("errorrr== [{}]", e.getMessage());
        	e.printStackTrace();
            CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "Error  ="+e.getMessage());
        }
        if(result!=null)
        	result = result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
        CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "result terminal===  ="+result);
        System.out.println("result terminal==="+result);
        return result;
    }
}