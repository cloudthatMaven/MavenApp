<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">

</script>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome To CloudThat</title>

<div id="logo_image"><img src="css/logo.png"/></div>
<h1>Please Enter the following Details....!!!</h1>
<link rel="stylesheet" href="css/main.css">
</head>
 
<body>

<form method="post" action="MessageDemo" name="myform" enctype="multipart/form-data"> 
	<div class="input_textbox" >
 		<input type="text" name="sender"   id="sender" placeholder="Sender Name" required="required" ><br>
		<input type="text" name="message" id="message" placeholder="Message to be sent" required="required" ><br>
	<input type="file" name="imageFile" required="required">
	<p>Please Upload .jpg Images</p>
	<br>
		<input type="submit" value="submit">
	</div>
</form>
	<h2>List of Values in your Database Table </h2>

		<table class="input_textbox">
			<thead>
				<tr>
					<th>Sender Name</th>
					<th>Message </th>
					<th>S3 Object URL</th>
					
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach  items="${msgList}"  var="msgModel">
						<tr>
							<td><c:out value="${msgModel.sender}" /></td>
							<td><c:out value="${msgModel.message}" /></td>
							<td><c:out value="${msgModel.objecturl}" /></td>
							
					</c:forEach> 
					
					</tr>
			</tbody>
		</table>
		
		


</body>
	<div class="footer" align="center">
		contact us at<a href="http://www.cloudthat.in/">&nbsp;cloudthat
	</a>
	</div>	

</html>