<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Order - Online BookStore</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<jsp:directive.include file="header.jsp" />

<div align="center">
	<h2 class="pageheading">Details of Order ID: ${order.orderId}</h2>
</div>

 <form action="update_order" method="post" id="orderForm">
<div align="center">
<h4 class="message">${message}</h4>
	
	<table>
	
	<tr> 
	
		<td><b>Ordered by:</b></td>
		<td>${order.customer.fullname}</td>
	</tr> 
	<tr>
		<td><b>Order Date:</b></td>
		<td>${order.orderDate}</td>
	</tr>
	<tr>
		<td><b>Recipient Name:</b></td>
		<td><input type="text" name="recipientName" value="${order.recipientName}" size="45" /></td>
	</tr>
	<tr>
		<td><b>Recipient Phone:</b></td>
		<td><input type="text" name="recipientPhone" value="${order.recipientPhone}" size="45" /></td>
	</tr>
	<tr>
		<td><b>Ship to:</b></td>
		<td><input type="text" name="shippingAddress" value="${order.shippingAddress}" size="45" /></td>
	</tr>
	<tr>
		<td><b>Payment Method:</b></td>
		<td>
			<select name="paymentMethod">
				<option>Cash On Delivery</option>
			</select>
		</td>
	</tr>
	<tr>
		<td><b>Order Status:</b></td>
		<td><select name="orderStatus">
				<option value="Processing">Processing</option>
				<option value="shipping">Shipping</option>
				<option value="Delivered">Delivered</option>
				<option value="Completed">Completed</option>
				<option value="Cancelled">Cancelled</option>
			</select></td>
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
			<th><a href="javascript:showAddBookPopup()">Add Books</a>
			
		</tr>
		
		<c:forEach items="${order.orderDetails}" var="detail" varStatus="status">
		
			<tr>
				<td>${status.index + 1}</td>
				<td>${detail.book.title}</td>
				<td>${detail.book.author}</td>
				<td><fmt:formatNumber value="${detail.book.price}" type="currency" /></td>
				<td><input type="text" name="quantity" value="${detail.quantity}" size="2" /></td>
				<td><fmt:formatNumber value="${detail.subtotal}" type="currency" /></td>
				<td><a href="">Remove</a></td>
			</tr>
		
		</c:forEach>
		 
		<tr>
			<td colspan="3" align="right"><b><i>TOTAL:</i></b></td>
			<td><b>${order.bookCopies}</b></td>
			<td><b><fmt:formatNumber value="${order.total}" type="currency" /></b></td>	
	</table>
	<br />
	<div align="center">  
	
		<button type="submit">Save</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button id="buttonCancel">Cancel</button>
		
	
	</div>
 
</div>
</form> 
<br> 
<div align="center">

</div>
<jsp:directive.include file="footer.jsp"/>
 
</body>

<script type="text/javascript">

function showAddBookPopup(){
	var width = 800;
	var height = 200;
	var left = (screen.width - width) / 2;
	var top = (screen.height - height) / 2;
	window.open('add_book_form', '_blank', 'width=' + width + ' , height=' + height + ', top=' + top + ', left=' + left);
}

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