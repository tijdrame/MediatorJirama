package com.boa.api;

import java.io.IOException;

public class SaTest {
	public static void main(String[] args) {
		DoRequestJirama doRequestJirama = new DoRequestJirama();
		String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
				"<SOAP-ENV:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" + 
				"	<SOAP-ENV:Body>" + 
				"		<Check_ref_ptfResponse xmlns=\"urn:WebservicePlus\">" + 
				"			<Check_ref_ptfResult>R0004#202003231837005008</Check_ref_ptfResult>" + 
				"		</Check_ref_ptfResponse>" + 
				"	</SOAP-ENV:Body>" + 
				"</SOAP-ENV:Envelope>";
		
		
		String urlClient = "http://localhost:8089/mockJirama";
		String typeApplication = "XML";
		
		try {
			request = request.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
			System.out.println("resp= "+request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String test = "<hgjhqcgjhjkqdh></request>";
		test = test.replaceAll("<request>", "");
		test = test.replaceAll("</request>", "");
		System.out.println("res="+test);*/
		//test = test.su
	}
}
