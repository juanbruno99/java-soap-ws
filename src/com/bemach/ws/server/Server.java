package com.bemach.ws.server;

import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import javax.xml.ws.EndpointReference;
import com.bemach.data.DbConfig;
import com.bemach.ws.server.EmployeeDocData;
import com.bemach.ws.hello.HelloWorld;

/**
*/
public final class Server {
	private static final Logger LOG = Logger.getLogger(Server.class.getName());
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_SID = "employees";
	private static final String DB_USER = "juan";
	private static final String DB_PSW = "juan";

	private Server() {
	}

	protected static DbConfig getDbConfig() {
		DbConfig dbCfg = new DbConfig();
		dbCfg.setDriverName(MYSQL_DRIVER);
		dbCfg.setHost(DB_HOST);
		dbCfg.setPort(DB_PORT);
		dbCfg.setDb(DB_SID);
		dbCfg.setUid(DB_USER);
		dbCfg.setPsw(DB_PSW);
		return dbCfg;
	}

	private static final String HOST_NAME = "localhost";
	private static final String PORT_NO = "9999";
	private static final String HELLO_SVC_NAME = "java-ws/hello";
	private static final String DOC_EMPL_SVC_NAME = "doc/employees";
	private static final String PROTOCOL = "http";

	protected static SvrConfig getSvrConfig() {
		SvrConfig svrCfg = new SvrConfig();
		svrCfg.setListenHostname(HOST_NAME);
		svrCfg.setListenPort(PORT_NO);
		svrCfg.setListenInterface(HELLO_SVC_NAME);
		svrCfg.setListenProtocol(PROTOCOL);
		return svrCfg;
	}

	protected static Endpoint publish(SvrConfig cfg, Object svc) {
		String url = String.format("%s://%s:%s/%s", cfg.getListenProtocol(),
				cfg.getListenHostname(), cfg.getListenPort(),
				cfg.getListenInterface());
		Endpoint ep = Endpoint.publish(url, svc);
		EndpointReference epr = ep.getEndpointReference();
		LOG.info("\nEndpoint Ref:\n" + epr.toString());
		return ep;
	}

	protected static void startHelloWorld() {
		SvrConfig cfg = getSvrConfig();
		cfg.setListenHostname(HOST_NAME);
		cfg.setListenInterface(HELLO_SVC_NAME);
		cfg.setListenPort(PORT_NO);
		cfg.setListenProtocol(PROTOCOL);
		HelloWorld hello = new HelloWorld();
		publish(cfg, hello);
		LOG.info("HelloWorld service started successfully ...");
	}

	protected static void startDocEmployees() {
		SvrConfig svrCfg = getSvrConfig();
		svrCfg.setListenHostname(HOST_NAME);
		svrCfg.setListenInterface(DOC_EMPL_SVC_NAME);
		svrCfg.setListenPort(PORT_NO);
		svrCfg.setListenProtocol(PROTOCOL);
		DbConfig dbCfg = getDbConfig();
		svrCfg.setDbCfg(dbCfg);
		EmployeeDocData docEmpl = new EmployeeDocData();
		publish(svrCfg, docEmpl);
		LOG.info("Employees (Document) service started successfully ...");
	}

	/**
	 * Start WS Server with multiple service endpoints...
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		startHelloWorld();
		startDocEmployees();
	}

}