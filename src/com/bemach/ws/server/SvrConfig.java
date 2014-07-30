package com.bemach.ws.server;

import com.bemach.data.DbConfig;

/**
 * The SvrConfig class consists of information that is used to form a service endpoint for both styles 
 * – document and RPC. 
 * Furthermore, the class contains the configuration parameters that the data access code uses in order 
 * to access the database.
 * */
public class SvrConfig {
	private String listenHostname = "localhost";
	private String listenPort = "9999";
	private String listenInterface = "HelloWorld";
	private String listenProtocol = "http";
	private DbConfig dbCfg = new DbConfig();
	
	public String getListenHostname() {
		return listenHostname;
	}
	public void setListenHostname(String listenHostname) {
		this.listenHostname = listenHostname;
	}
	public String getListenPort() {
		return listenPort;
	}
	public void setListenPort(String listenPort) {
		this.listenPort = listenPort;
	}
	public String getListenInterface() {
		return listenInterface;
	}
	public void setListenInterface(String listenInterface) {
		this.listenInterface = listenInterface;
	}
	public String getListenProtocol() {
		return listenProtocol;
	}
	public void setListenProtocol(String listenProtocol) {
		this.listenProtocol = listenProtocol;
	}
	public DbConfig getDbCfg() {
		return dbCfg;
	}
	public void setDbCfg(DbConfig dbCfg) {
		this.dbCfg = dbCfg;
	}
	
	
}
