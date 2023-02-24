<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>Your Cart Details</h2>
	</div>

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

			<form action="update_cart" method="post" id="cartForm">

				<table border="1">

					<tr>
						<th>No</th>
						<th>Book</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Subtotal</th>  
						<th style="color:green"><a href="clear_cart"><b>Clear Cart</b></a></th>
					</tr>

					<c:forEach items="${cart.items}" var="item" varStatus="status">

						<tr>
							<td>${status.index + 1}</td>
							<td><img width="100" height="150" src="data:image/jpg;base64,${item.key.base64Image}">
								</td>
							<td><fmt:formatNumber value="${item.key.price}"
									type="currency" /></td>
							<td>
							<input type="hidden" name="bookId" value="${item.key.bookId}" />
							<input type="text" name="quantity${status.index + 1}" value="${item.value}" size="4" /></td>
							<td><fmt:formatNumber value="${item.key.price * item.value}"
									type="currency" /></td>
							<td><a href="remove_from_cart?book_id=${item.key.bookId}">Remove</a></td>
						</tr>
					</c:forEach>  

					<tr>
						<td></td>
						<td></td>
						<td><b>${cart.totalQuantity} book(s)</b></td>
						<td>Total:</td>
						<td colspan="2"><fmt:formatNumber value="${cart.totalAmount}" type="currency" /></td>
				</table> 
				
				<table style="border: 0">
				
					<tr>
						<td></td>
						<td><button type="submit">Update</button></td>
						<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
						<td><a href="checkout">Checkout</a></td>
						
				
				</table>

			</form>

		</div>

	</c:if>



	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		
		$("#cartForm").validate({
			rules : {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
				
					quantity${status.index + 1}: {required: true, number: true, min: 1}
				
				</c:forEach>
				
			},
				
			messages : {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
				
				quantity${status.index + 1}: {required: "Please enter quantity",
											  number: "Quantity must be a number",
											  min: "Quantity must be greater than 0"},
					
				</c:forEach>
					
			} 
				
		});
  
		});
	
	</script>
	

</body>

</html>