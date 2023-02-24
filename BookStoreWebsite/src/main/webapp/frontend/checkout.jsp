<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout - Online BookStore</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	<br />

	<c:set var="cart" value="${sessionScope['cart']}" />

	<c:if test="${cart.totalItems == 0}">

		<h2 align="center">There's no item in your cart.</h2>

	</c:if>

	<c:if test="${cart.totalItems > 0}">

		<div align="center">
			<h2>
				Review Your Order Details <a href="view_cart">Edit</a>
			</h2>
			<table border="1">

				<tr>
					<th>No</th>
					<th colspan="2">Book</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Subtotal</th>

				</tr>

				<c:forEach items="${cart.items}" var="item" varStatus="status">

					<tr>
						<td>${status.index + 1}</td>
						<td><img width="100" height="150"
							src="data:image/jpg;base64,${item.key.base64Image}"></td>
						<td>${item.key.title}</td>
						<td>${item.key.author}</td>
						<td><fmt:formatNumber value="${item.key.price}"
								type="currency" /></td>
						<td><input type="hidden" name="bookId"
							value="${item.key.bookId}" /> <input type="text"
							name="quantity${status.index + 1}" value="${item.value}" size="4"
							readonly="readonly" /></td>
						<td><fmt:formatNumber value="${item.key.price * item.value}"
								type="currency" /></td>

					</tr>
				</c:forEach>

				<tr>
					<td></td>
					<td></td>
					<td><b>${cart.totalQuantity} book(s)</b></td>
					<td>Total:</td>
					<td colspan="2"><fmt:formatNumber value="${cart.totalAmount}"
							type="currency" /></td>
			</table>

			<h2>Your Shipping Information</h2>

			<form id="orderForm" action="place_order" method="post">
				<table style="border: 0">
					<tr>
						<td>Recipient Name:</td>
						<td><input type="text" name="recipientName"
							value="${loggedCustomer.fullname}"></td>
					</tr>
					<tr>
						<td>Recipient Phone:</td>
						<td><input type="text" name="recipientPhone"
							value="${loggedCustomer.phone}"></td>
					</tr>
					<tr>
						<td>Street Address:</td>
						<td><input type="text" name="address"
							value="${loggedCustomer.address}"></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><input type="text" name="city"
							value="${loggedCustomer.city}"></td>
					</tr>
					<tr>
						<td>Zip Code:</td>
						<td><input type="text" name="zipcode"
							value="${loggedCustomer.zipcode}"></td>
					</tr>
					<tr>
						<td>Country:</td>
						<td><input type="text" name="country"
							value="${loggedCustomer.country}"></td>
					</tr>

				</table>
				<div>
					<h2>Payment</h2>
					Choose your paymeny method: &nbsp;&nbsp;&nbsp; <select
						name="paymenyMethod">
						<option value="Cash On Delivery">Cash On Delivery</option>
					</select> &nbsp;&nbsp;&nbsp; &nbsp;
				</div>
				<div>

					<table style="border: 0">

						<tr>
							<td></td>
							<td><button type="submit">Place Order</button></td>
							<td><a href="${pageContext.request.contextPath}/">Continue
									Shopping</a></td>
						</tr>


					</table>
				</div>
			</form>
		</div>

	</c:if>



	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {

			$("#orderForm").validate({
				rules:{
					recipientName: "required",
					recipientPhone: "required",
					address: "required",
					city: "required",
					zipcode: "required",
					country: "required",
				},
				
				messages:{
					recipientName: "Please enter recipient name",
					recipientPhone: "Please enter phone number",
					address: "Please enter street address",
					city: "Please enter city",
					zipcode: "Please enter zip code",
					country: "Please enter country",
				}
			})
			
		});
	</script>
 

</body>

</html>