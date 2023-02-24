<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add book to order</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

<div align="center">

	<h2>Add Book to Order ID: ${order.orderId}</h2>
	<form action="add_book_to_order" method="post">
	
		<table>
		
			<tr>
				<td>Select a book: </td>
				<td>
					<select name="bookId">
					
						<c:forEach var="book" items="${listBook}" varStatus="status">
						
							<option value="${book.bookId}">${book.title} - ${book.author}</option>
						
						</c:forEach>
					
					</select>
					</td>
					</tr>
					<tr>
						<td>&nbsp;</td></tr>
						<tr><td>Number of Copies</td>
						<td>
							<select name="quantity">
							
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							
							</select>
							</td>
							</tr>
							<tr>
						<td>&nbsp;</td></tr>
						
						<tr align="center">
						<td colspan="2">
						<input type="submit" value="Add" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Cancel" onclick="javascript: self.close();" />
						</td>
		
		</table>
		
	</form>

</div>

</body>
</html>