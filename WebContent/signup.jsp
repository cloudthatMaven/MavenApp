<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up Form</title>
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

	<div id="logo_image">
		<img src="css/logo.png">
	</div>

	<div class="login">
		<h1>Create your account</h1>
		
			<div class="login-form">

				<br>
				<h3>Email:</h3>
				<input type="text" name="email" id="email"
					placeholder="Enter your email-id here" /><br> <br>
				<h3>Password:</h3>
				<input type="password" name="pwd" id="Password"
					placeholder="Enter your password here" /> <br> <br> 
					<input type="submit" value="Sign Up"  onclick="SignupUsers()" class="input_textbox" /> <br> <br>
				<h4>
					Already have an account ? <a href="signin.jsp">Sign In</a>
				</h4>
			</div>
	</div>
	</form>

</body>
</html>