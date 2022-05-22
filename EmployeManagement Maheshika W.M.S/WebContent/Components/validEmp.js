$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateEmployeForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidEmpIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "EmployeAPI",  
			type : type,  
			data : $("#formEmploye").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onEmpSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onEmpSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divEmpGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidEmpIDSave").val("");  
	$("#formEmploye")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidEmpIDSave").val($(this).closest("tr").find('#hidCompIDUpdate').val());     
	$("#employeName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#employePosition").val($(this).closest("tr").find('td:eq(1)').text());
	$("#employeSalary").val($(this).closest("tr").find('td:eq(2)').text());
	$("#employeType").val($(this).closest("tr").find('td:eq(2)').text());
	$("#employePNO").val($(this).closest("tr").find('td:eq(2)').text());
	    
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "EmployeAPI",   
		type : "DELETE",   
		data : "cID=" + $(this).data("cid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onEmpDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onEmpDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divCompGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateEmployeForm() 
{  
	// employeeNameOUNT  
	if ($("#employeName").val().trim() == "")  
	{   
		return "Insert employeName.";  
	}

	// employePosition-----------------------  
	if ($("#employePosition").val().trim() == "")  
	{   
		return "employePosition.";  
	} 
	
	// employeSalary------------------------  
	if ($("#employePosition").val().trim() == "")  
	{   
		return "Insert employePosition.";  
	}

	// employeType------------------------  
	if ($("#employeType").val().trim() == "")  
	{   
		return "Insert employeType.";  
	}

	// employePNO------------------------  
	if ($("#employePNO").val().trim() == "")  
	{   
		return "Insert employePNO.";  
	}
	

	return true; 
}

