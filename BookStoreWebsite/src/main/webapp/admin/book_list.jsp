<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Books - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Books Management</h2>
	</div>
	
	<div align="center">
	<h3><a href="new_book">Create New Book</a></h3>
	<h4 class="message"><i>${message}</i></h4>
	</div>

	<div align="center">

		<table border="1" colspan="1" cellpadding="5">

			<tr>
				<th>Index</th> 
				<th>ID</th>
				<th>Title</th>
				<th>Image</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>

			<c:forEach var="book" items="${listBook}" varStatus="status">
			
			<tr>
				<td>${status.index+1}</td>
				<td>${book.bookId}</td>
				<td><img src="data:image/jpg;base64,${book.base64Image}" width="100" height="150"></td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.category.name}</td>
				<td>$${book.price}</td>
				<td>${book.lastUpdateTime}</td>
				<td><a href="edit_book?id=${book.bookId}">Action</a><br/>
						<a href="javascript:void(0);" id="${book.bookId}" class="deleteLink">Delete</a>
					</td>
					</tr>
			  
			</c:forEach>
				

		</table>
 

	</div> 
	
<jsp:directive.include file="footer.jsp" />

<script>
	$(document).ready(function(){
		  
		$(".deleteLink").each(function(){
			$(this).on("click", function(){
				bookId=$(this).attr("id");
			
				if(confirm("Are you sure you want to delete the book with ID " + bookId + "?")){
				
					window.location = 'delete_book?id='+bookId;
				
				}
			});
		});
		
	});
	
</script>

</body>
</html>