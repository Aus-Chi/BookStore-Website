<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Order Details</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

<jsp:directive.include file="header.jsp" />
<div align="center">
<c:if test="${order == null}">
	<h2>Sorry, you are not authorized to view this order</h2>
</c:if>
</div>
<c:if test="${order != null}">
	


<div align="center">
	<h2 class="pageheading">Your Order ID: ${order.orderId}</h2>
</div>


<div align="center">
	
	<table>
	
	<tr>
		<td><b>Order Status:</b></td>
		<td>${order.status}</td>
	</tr>
	
	<tr>
		<td><b>Order Date:</b></td>
		<td>${order.orderDate}</td>
	</tr>
	
	<tr>
		<td><b>Book Copies:</b></td>
		<td>${order.bookCopies}</td>
	</tr>
	<tr>
		<td><b>Total Amount:</b></td>
		<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
	</tr>
	<tr>
		<td><b>Recipient Name:</b></td>
		<td>${order.recipientName}</td>
	</tr>
	<tr>
		<td><b>Recipient Phone:</b></td>
		<td>${order.recipientPhone}</td>
	</tr>
	
	<tr>
		<td><b>Ship to:</b></td>
		<td>${order.shippingAddress}</td>
	</tr>
	<tr>
		<td><b>Payment Method:</b></td>
		<td>${order.paymentMethod}</td>
	</tr>
	
	</table>
	
	</div>
	
	<div align="center">
	
	<h2>Ordered Books</h2>
	
	<table>
	
		<tr>
			<th>Index</th>
			<th>Book Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Sub Total</th>
			
		</tr>
		
		<c:forEach items="${order.orderDetails}" var="detail" varStatus="status">
		
			<tr>
				<td>${status.index + 1}</td>
				<td>${detail.book.title}</td>
				<td>${detail.book.author}</td>
				<td><fmt:formatNumber value="${detail.book.price}" type="currency" /></td>
				<td>${detail.quantity}</td>
				<td><fmt:formatNumber value="${detail.subtotal}" type="currency" /></td>
			
			</tr>
		
		</c:forEach>
		 
		<tr>
			<td colspan="3" align="right"><b><i>TOTAL:</i></b></td>
			<td><b>${order.bookCopies}</b></td>
			<td><b><fmt:formatNumber value="${order.total}" type="currency" /></b></td>	
	</table>
	<br />

</div>

<br> 
<div align="center">

</div>
</c:if>
<jsp:directive.include file="footer.jsp"/>

</body>

</html>