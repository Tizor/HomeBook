<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacts</title>
    </head>
    <body>
    	<div align="center">
	        <h1>Contact List</h1>
	        <h3><a href="newContact">New Contact</a></h3>
	        <table border="1">
	        	<th>id</th>
	        	<th>Name</th>
	        	<th>City</th>
	        	<th>Phone</th>
	        	<th>Action</th>
	        	
				<c:forEach var="contact" items="${listContact}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${contact.name}</td>
					<td>${contact.city}</td>
					<td>${contact.phone}</td>

					<td>
						<a href="editContact?id=${contact.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteContact?id=${contact.id}">Delete</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
