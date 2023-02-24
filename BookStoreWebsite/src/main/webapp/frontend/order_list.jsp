<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Order History</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">My Order History</h2>
	</div>

	<c:if test="${fn:length(listOrders) == 0}">
		<h3>You have not placed any orders</h3>
	</c:if>

	<c:if test="${fn:length(listOrders) > 0}">


		<br>
		<div align="center">
			<table border="1" cellpadding="5" class="form">

				<tr>
					<th>Index</th>
					<th>Order ID</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Order Date</th>
					<th>Status</th> 
					<th>Actions</th>
				</tr>

				<c:forEach var="order" items="${listOrders}" varStatus="status">

					<tr>

						<td>${status.index + 1}</td>
						<td>${order.orderId}</td>
						
						<td>${order.bookCopies}</td>
						<td><fmt:formatNumber value="${order.total}" type="currency" />
						</td>
						<td>${order.orderDate}</td>
						<td>${order.status}</td>
						
						<td><a href="show_order_detail?id=${order.orderId}">View Details</a>&nbsp;&nbsp;&nbsp;
							</td>

					</tr>

				</c:forEach>

				



			</table>
			
		</div>
		</c:if>
		<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$(".deleteLink")
								.each(
										function() {
											$(this)
													.on(
															"click",
															function() {
																reviewId = $(
																		this)
																		.attr(
																				"id");

																if (confirm("Are you sure you want to delete the review with ID "
																		+ reviewId
																		+ "?")) {

																	window.location = 'delete_review?id='
																			+ reviewId;

																}
															});
										});

					});
</script>

</html>