<%@ page import="com.EmployeManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/validComp.js"></script> 
</head>
<body>

<div class="container"> 
	<div class="row">  
		<div class="col"> 
			<h1>Employee Management</h1>
			<br>
				<form id="formComplain" name="formComplain" method="post" action="Index.jsp">  
					Employee Name:  
 	 				<input id="customerName" name="customerName" type="text"  class="form-control form-control-sm">
					<br>Employee Position:   
  					<input id="customerPNO" name="customerPNO" type="text" class="form-control form-control-sm" >   
  					<br>Employee Salary:   
  					<input id="description" name="description" type="text"  class="form-control form-control-sm">
					<br>Employee Type:   
  					<input id="description" name="description" type="text"  class="form-control form-control-sm">
					<br>Employee Phone number:   
  					<input id="description" name="description" type="text"  class="form-control form-control-sm">
					<br>
					
					<div id="alertSuccess" class="alert alert-success"> </div>				
			   		<div id="alertError" class="alert alert-danger"></div>
			   		 
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidEmpIDSave" name="hidEmpIDSave" value=""> 
				</form>
				
				
			   <br>
				<div id="divEmpGrid">
					<%
					EmployeManagement empObj = new EmployeManagement();
						out.print(empObj.readEmp());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>