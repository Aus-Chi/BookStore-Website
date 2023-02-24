<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Austin's Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>

	<div align="center">

		<hr width="60%">

		<h2 class="pageheading">Quick Actions:</h2>
		<b> <a href="book_form.jsp">New Book</a> &nbsp; <a
			href="user_form.jsp">New User</a> &nbsp; <a href="category_form.jsp">New
				Category</a> &nbsp; <a href="customer_form.jsp">New Customer</a> &nbsp;

		</b>
		<hr width="60%">
	</div>

	<div align="center">

		<h2 class="pageheading">Recent Sales:</h2>
		<hr width="60%">
		<table>

			<tr>
				<th>Order ID</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>
			<c:forEach items="${listMostRecentSales}" varStatus="staus"
				var="order">

			<tr>
				<td>${order.orderId}</td>
				<td>${order.customer.fullname}</td>
				<td>${order.bookCopies}</td>
				<td><a href="view_order?id=${order.orderId}"><fmt:formatNumber value="${order.total}" type="currency" /></a></td>
				<td>${order.paymentMethod}</td>
				<td>${order.status}</td>
				<td>${order.orderDate}</td>
			</tr>

			</c:forEach>

		</table>
	</div>

	<div align="center">

		<h2 class="pageheading">Recent Reviews:</h2>
		<hr width="60%">
		
		<table>

			<tr>
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
			</tr>
			<c:forEach items="${listMostRecentReviews}" varStatus="staus"
				var="review">

			<tr>
				<td>${review.book.title}</td>
				<td>${review.rating}</td>
				<td>${review.headline}</td>
				<td>${review.customer.fullname}</td>
				<td>${review.reviewTime}</td>
			</tr>

			</c:forEach>

		</table>

	</div>

	<div align="center">

		<h2 class="pageheading">Statistics:</h2>
		<hr width="60%">

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
</html>