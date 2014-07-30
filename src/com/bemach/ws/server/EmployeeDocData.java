package com.bemach.ws.server;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.bemach.data.DbConfig;
import com.bemach.data.Employee;
import com.bemach.data.EmployeeDao;


/**
 * Class does not use service bean, so it works straight with DAO for quick live-testing purposes
 * */
@WebService
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
public class EmployeeDocData {
	
	private static final Logger log = Logger.getLogger(EmployeeDocData.class.getName());
	private EmployeeDao dao = null;
	
	public EmployeeDocData() {
		dao = new EmployeeDao(new DbConfig()); //loads default connection properties
	}
	
	@WebMethod
	public Employee getEmployee(@WebParam(name="emplNo") long emplNo) throws SOAPException, SQLException {
		log.info("Doc.readEmployee");
		Employee empl = dao.getEmpl((int)emplNo);
		
		if(empl==null)
			throw new SOAPException("Non existing employee");
		
		return empl;
	}
	
	@WebMethod
	public boolean updateEmployee(@WebParam(name="employee") Employee employee) throws SQLException {
		log.info("Doc.updateEmployee");
		return dao.updateEmpl(employee);
	}
	
	@WebMethod
	public long createEmployee(@WebParam(name="employee") Employee employee) throws SQLException {
		log.info("Doc.createEmployee");
		return dao.createEmployee(employee);
	}
	
	@WebMethod
	public boolean deleteEmployee(@WebParam(name="emplNo") long emplNo) throws SQLException {
		log.info("Doc.deleteEmployee");
		return dao.deleteEmpl((int)emplNo);
	}
	
}
