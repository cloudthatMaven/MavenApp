<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Verify Form</title>
<link rel="stylesheet" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verification Page</title>
 
 <script src="js/aws-sdk-2.7.19.min.js"></script>
    <script src="js/aws-cognito-sdk.min.js"></script>
    <script src="js/aws-cognito-sdk.js"></script>
    <script src="js/sjcl.js"></script>
    <script src="js/jsbn.js"></script>
    <script src="js/jsbn2.js"></script>
    <script src="js/amazon-cognito-identity.min.js"></script>
    <script src="js/authentication.js"></script>
</head>
<body>
<div id="logo_image">
<img src="css/logo.png" >
<h1 >Enter your verification code</h1>
</div>
 <div class="login">
 	
  <div class="login-form">
    <br>
    <h3>Verification Code:</h3>
    <input type="text" id="verification-code" placeholder="Verification Code"/><br>
    <br>
    <input type="submit" value="Sign Up"  onclick="confirmRegistration()" class="input_textbox" /> <br> <br>
    
    <br>
  </div>
</div>

</body>
</html>