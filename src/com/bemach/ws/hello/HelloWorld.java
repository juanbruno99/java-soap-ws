package com.bemach.ws.hello;

import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public class HelloWorld {
	private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
	
	@WebMethod
	public String say(@WebParam(name="name") String name) {
		logger.info("Webservice is called");
		return String.format("Hello, %s!", name);
	}

	public static void main(String[] args) {
		String msg = new HelloWorld().say("Johnny, B. Good");
		logger.info(msg);
	}
}
