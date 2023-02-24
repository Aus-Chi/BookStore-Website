<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
	<c:if test="${cat != null}">
		<h2 class=pageheading>Edit Category</h2>
	</c:if>
	<c:if test="${cat == null}">
		<h2 class="pageheading">Create New Category</h2>
</c:if>
	</div>

	&nbsp;

	<div align="center">
<c:if test="${cat != null}">

	<form action="update_category" method="post" id="categoryForm">
<input type="hidden" name="id" value="${cat.categoryId}">
</c:if>

<c:if test="${cat == null}">
		<form action="create_category" method="post"id="categoryForm">
</c:if>
			<table class="form">

				<tr>
					
					<td><label>Name: </label></td>
					<td><input type="text" name="name" id="name" size="20" value="${cat.name}"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="submit">Save</button>&nbsp;&nbsp;&nbsp;<button id="buttonCancel">Cancel</button></td>
				</tr>

			</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
<script type="text/javascript">

$(document).ready(function(){
	$("#categoryForm").validate({
		rules:{
			name: "required"	
		},
		
		messages: {
			name: " Please enter name!"
		}
	});
	

	$("#buttonCancel").click(function() {
		history.go(-1);
	})
});

	
</script>
</html>