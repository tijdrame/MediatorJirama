package com.boa.api;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.boa.api.utils.CommonUtilities;

/**
 * CheckFactory
 */
public class MediatorJirama extends AbstractMediator implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String request;
    private String url;
    private String typeApplication;
    private String response;
    //private final Logger log = LoggerFactory.getLogger(MediatorJirama.class);


    private DoRequestJirama doRequestJirama = new DoRequestJirama();

    

    @Override
    public boolean mediate(MessageContext messageContext) {
        try {
        	CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "echo in mediator  ===");
        	Map<String, Object> lamap= messageContext.getContextEntries();
        	for(Map.Entry<String, Object> entry: lamap.entrySet()){
        		CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "entry key ="+entry.getKey());
        		CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "entry val ="+entry.getValue());
        	}
        	//for (iterable_type iterable_element : messageContext.getContextEntries()) {
				
			//}
            setRequest((String)messageContext.getProperty("request_content"));
            setUrl((String)messageContext.getProperty("url_link"));
            setTypeApplication((String)messageContext.getProperty("url_content"));
            CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "Request  ="+request);
        	CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "url  ="+url);
        	CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "typeApplication  ="+typeApplication);
            request = request.replaceAll("<request>", "");
            request = request.replaceAll("</request>", "");
            CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "after treatement Request  ="+request);
            setResponse(doRequestJirama.sendRequest(request, url, typeApplication));
            messageContext.setProperty("response", response);
        } catch (Exception e) {
            //log.error("Error in method mediate [{}]", e.getMessage());
            try {
				CommonUtilities.WriteInFile(CommonUtilities.NameCurrentTraceFile, "Error  ="+e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
        }
        return true;
    }
    
    /*public static void main(String[] args) {
		String test = "<request><hgjhqcgjhjkqdh></request>";
		test = test.substring(8);
		System.out.println("res="+test);
	}*/



    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTypeApplication() {
        return this.typeApplication;
    }

    public void setTypeApplication(String typeApplication) {
        this.typeApplication = typeApplication;
    }

    public DoRequestJirama getDoRequestJirama() {
        return this.doRequestJirama;
    }

    public void setDoRequestJirama(DoRequestJirama doRequestJirama) {
        this.doRequestJirama = doRequestJirama;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    
}