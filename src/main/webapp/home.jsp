<%@page import="dto.task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   
	   <h1 align="center">THIS IS HOME PAGE</h1>
	   <%List<task> tasklist=(List<task>)request.getAttribute("tasks"); %>
	     <div>
	      
	      <table border="1">
	       <tr>
		     <th>TASK NAME </th>
		     <th>DESCRIPTION</th>
		      <th>CREATE TIME</th>
		       <th>STATUS</th>
		        <th>DELETE</th>
		      <th>EDIT</th>
		      
		     </tr>
		     <%if(tasklist!=null){
		    	 for(task task:tasklist){%>
		    	 <tr>
		    	 <th><%=task.getName()%></th>
		    	 <th><%=task.getDescription()%></th>
		    	 <th><%=task.getCreatedTime()%></th>
		    	 <th>
		    	 <%if(task.isStatus()){ %>
		    	 completed
		    	 <%}else{ %>
		    	<a href="complete?id=<%=task.getId()%>"><button>Complete</button></a>
				<%} %></th>
				
				<th><a href="delete?id=<%=task.getId()%>"><button>Delete</button></a></th>
				<th><button>Edit</button></th>
				</tr>
		    	
		    	
		    	
		    	
		     	
		    	 <%} }%> 
		     
	      </table>
   </div>
       <a href="addtask.html"> <button class="extra">Add task</button></a>  
       <a href="login.html"> <button class="extra">Logout</button></a>
 

</body>
</html>