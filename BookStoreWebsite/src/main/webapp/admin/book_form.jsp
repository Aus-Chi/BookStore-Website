<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script src="../js/jquery.richtext.min.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="../js/jquery-ui.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">

		<c:if test="${Book != null}">

			<h2>Edit Book</h2>

		</c:if>

		<c:if test="${Book == null}">

			<h2>Create New Book</h2>

		</c:if>
	</div>

	<div align="center">
		<c:if test="${Book == null}">
			<form action="create_book" id="bookForm" method="post"
				enctype="multipart/form-data">
		</c:if>

		<c:if test="${Book != null}">

			<form action="update_book" id="bookForm" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="bookId" value="${Book.bookId}" />
		</c:if>

		<table collspan="1" class="form">

			<tr>
				<td align="right"><label>Category: </label></td>
				<td><select name="category">
						<c:forEach items="${listCategory}" var="category">

							<c:if test="${category.categoryId eq Book.category.categoryId}">

								<option value="${category.categoryId}" selected>
							</c:if>

							<c:if test="${category.categoryId ne Book.category.categoryId}">

								<option value="${category.categoryId}">
							</c:if>
				
					${category.name}
					</option>

						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="right"><label>Title: </label></td>
				<td><input value="${Book.title}" type="text" name="title"
					size="20"></td>
			</tr>
			<tr>
				<td align="right"><label>Author: </label></td>
				<td><input value="${Book.author}" type="text" name="author"
					size="20"></td>
			</tr>
			<tr>
				<td align="right"><label>ISBN: </label></td>
				<td><input value="${Book.isbn}" type="text" name="isbn"
					size="20"></td>
			</tr>
			<tr>
				<td align="right"><label>Publish Date: </label></td>
				<td><input
					value="<fmt:formatDate pattern='MM/dd/yyyy' value='${Book.publishDate}'/>"
					type="text" name="publishdate" id="publishDate" size="20"></td>
			</tr>
			<tr>
				<td align="right"><label>Book Image: </label></td>
				<td align="left"><input type="file" id="bookImage"
					name="bookImage" size="20"><br /> <img id="thumbnail"
					alt="Image Preview" width="100" height="100"
					style="margin-top: 10px"
					src="data:image/jpg;base64,${Book.base64Image}" /></td>
			</tr>
			<tr>
				<td align="right"><label>Price: </label></td>
				<td><input value="${Book.price}" type="text" name="price"
					size="20"></td>
			</tr>
			<tr>
				<td align="right"><label>Description: </label></td>
				<td><textarea rows="5" cols="40" name="description"
						id="description">${Book.description}</textarea>
			</tr>


			<tr>
				<td align="center" colspan="2"><button type="submit">Save</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">cancel</button>
		</table>

		</form>

	</div>



</body>

<script type="text/javascript">


	$(document).ready( function(){
		$('#publishDate').datepicker();
		$('.description').richText();
		$('#bookImage').change(function(){
			showImageThumbnail(this);
		});
		$("#bookForm").validate({
			rules:{
				category: "required",
				title: "required",
				author: "required",
				isbn: "required",
				publishdate: "required",
				<c:if test="${Book == null}">
				bookImage: "required",
				</c:if>
				price: "required",
				description: "required",
				
			},
			
			messages: {
				category: "Please select a category",
				title: "Please enter book title",
				author: "Please enter book author",
				isbn: "Please enter ISBN",
				publishdate: "Please enter book publish date",
				bookImage: "Please enter book image",
				price: "Please enter book price",
				description: "Please enter book description",
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});
	
	function showImageThumbnail(fileInput){
		
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e){
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
		
	}
	

</script>

</html>