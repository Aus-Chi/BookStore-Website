<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Orders</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<jsp:directive.include file="header.jsp" />

<div align="center">
	<h2 class="pageheading">Order Management</h2>
</div>


<div align="center">
<h4 class="message">${message}</h4>

</div>

<br>
<div align="center">
<table border="1" cellpadding="5" class="form">

	<tr>
		<th>
			Index
		</th>
		<th>
			Order ID
		</th>
		<th>
			Ordered by
		</th>
		<th>
			Book Copies
		</th>
		<th>
			Total
		</th>
		<th>
			Payment Method
		</th>
		<th>
			Status
		</th>
		<th>
			Order Date
		</th>
		<th>
			Actions
		</th>
	</tr>
	
	
		<c:forEach var="order" items="${listOrder}" varStatus="status">
		
			<tr>
			
				<td>
					${status.index + 1}
				</td>
				<td>
					${order.orderId}
				</td>
				<td>
					${order.customer.fullname}
				</td>
				<td>
					${order.bookCopies}
				</td>
				<td>
					<fmt:formatNumber value="${order.total}" type="currency" />
				</td>
				<td>
					${order.paymentMethod}
				</td>
				<td>
					${order.status} 
				</td> 
				<td>
					${order.orderDate}
				</td>
				<td>
					<a href="view_order?id=${order.orderId}">Details</a>&nbsp;&nbsp;&nbsp;
					<a href="edit_order?id=${order.orderId}">Edit</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" id="${order.orderId}" class="deleteLink">Delete</a>
				</td>
				
			</tr>
		
		</c:forEach>
	
	

</table>
</div>
<jsp:directive.include file="footer.jsp"/>

</body>

<script type="text/javascript">

$(document).ready(function(){
	
	$(".deleteLink").each(function(){
		$(this).on("click", function(){
			reviewId=$(this).attr("id");
		
			if(confirm("Are you sure you want to delete the review with ID " + reviewId + "?")){
			
				window.location = 'delete_review?id='+reviewId;
			
			}
		});
	});
	
});
</script>

</html>