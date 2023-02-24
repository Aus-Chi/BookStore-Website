<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<div align="center">
<h1>Book Store Administration</h1>
</div>

<div align="center">
<h2>Admin Login</h2>
</div>

<div align="center">
<h4 class="message">${message}</h4>
</div>
<br />

<div align="center">
<form action="login" method="post" id="formLogin">
<table class="form">
<tr><td><label>E-mail: </label></td><td><input type="text" id="email" name="email" size="20"></td></tr>
<tr><td><label>Password: </label></td><td><input type="password" id="password" name="password" size="20"></td></tr>
<tr><td colspan="2" align="center"><button type="submit">Login</button>
</table>
</form>
</div>
</body>

<script type="text/javascript">

$(document).ready( function(){
	$("#formLogin").validate({
		rules:{
			email:{
				required: true,
				email: true
			},
			
			password:{
				required: true
			}
			
		},
		
		messages: {
			email: {
				
			required: " Please enter email!",
			email: "Please enter a valid email address"
			
			},
		
			password: " Please enter password!"
		}
	});
	
	
});



</script>

</html>