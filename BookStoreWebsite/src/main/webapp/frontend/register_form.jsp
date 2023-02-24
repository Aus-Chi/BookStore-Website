<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register as a Customer</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>


</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Register as a Customer</h2>
	</div>

	<div align="center">

	<form action="register_customer" id="customerForm" method="post">

 
		<table collspan="1" class="form">


			<tr>
				<td align="right"><label>Email: </label></td>
				<td><input value="${customer.email}" type="email" id="email"
					name="email" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>Full Name: </label></td>
				<td><input value="${customer.fullname}" type="text"
					id="fullName" name="fullName" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>Password: </label></td>
				<td><input value="${customer.password}" type="password"
					id="password" name="password" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>Confirm Password: </label></td>
				<td><input value="${customer.password}" type="password"
					id="confirmPassword" name="confirmPassword" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>Phone Number: </label></td>
				<td><input value="${customer.phone}" type="text"
					id="phoneNumber" name="phoneNumber" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>Address: </label></td>
				<td><input value="${customer.address}" type="text" id="address"
					name="address" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>City: </label></td>
				<td><input value="${customer.city}" type="text" id="city"
					name="city" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>ZipCode: </label></td>
				<td><input value="${customer.zipcode}" type="text" id="zipCode"
					name="zipCode" size="45"></td>
			</tr>
			<tr>
				<td align="right"><label>Country: </label></td>
				<td><input value="${customer.country}" type="text" id="country"
					name="country" size="45"></td>
			</tr>


			<tr>
				<td align="center" colspan="2"><button type="submit">Save</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">cancel</button></td></tr>
		</table>

	</form>

	</div>



</body>

<script type="text/javascript">
	$(document).ready(function() {

		$("#customerForm").validate({
			rules : {
				email : {
					email : true,
					required : true
				},
				fullName : "required",
				password : "required",
				confirmPassword : {
					required : true,
					equalTo : "#password"
				},
				phoneNumber : "required",
				address : "required",
				city : "required",
				zipCode : "required",
				country : "required"

			},

			messages : {
				email : {

					required : "Please enter e-mail address",
					email : "Please enter a valid e-mail address"

				},

				fullName : "please enter full name",
				password : "Please enter password",
				confirmPassword : {
					required : "Please confirm password",
					equalTo : "Confirm password does not match password"
				},
				phoneNumber : "Please enter phone number",
				address : "Please enter address",
				city : "Please enter city",
				zipCode : "Please enter zip code",
				country : "Please enter country"
			}
		});

		$("#buttonCancel").click(function() {
			history.go(-1);
		})
	});
</script>

</html>