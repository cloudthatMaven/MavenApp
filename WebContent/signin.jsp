<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Form</title>
   <link rel="stylesheet" href="css/style.css">
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

<div class="login">
  <div class="login-form">
  <form action="" method="post">
    <br>
    <h3>Email:</h3>
    <input type="text" id="email" name="email" placeholder="your email-id goes here"/><br>
    <br>
    <h3>Password:</h3>
    <input type="password" id="password" name="pwd" placeholder="your password here"/>
    <br>
    <br>
    <input type="button" value="Sign In" onclick="login()" class="login-button"/>
    <br>
    <br>
    <h4>New Here ? <a href="signup.jsp">Sign Up!</a></h4>
  </div>
</div>
</form>
</body>
</html>