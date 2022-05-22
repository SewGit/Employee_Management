package com;

import com.EmployeManagement;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeAPI
 */
@WebServlet("/EmployeAPI")
public class EmployeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmployeManagement empObj =new EmployeManagement();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeAPI() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = empObj.insertEmp(request.getParameter("employeName"),      
				request.getParameter("employePosition"),
				request.getParameter("employeSalary"),
				request.getParameter("employeType"),
				request.getParameter("employePNO"));
				response.getWriter().write(output);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = empObj.updateEmp(paras.get("hideIDSave").toString(),     
		    		paras.get("employeName").toString(),     
		    		paras.get("employePosition").toString(),
		    		paras.get("employeSalary").toString(),
		    		paras.get("employeType").toString(),
		    		paras.get("employePNO").toString()); 
		 			response.getWriter().write(output);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 
		 String output = empObj.deleteEmp(paras.get("eID").toString());  
		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
		
		String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

}
