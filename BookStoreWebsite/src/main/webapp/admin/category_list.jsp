<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<h2 class="pageheading">Category Management</h2>
</div>

<div align="center">
	<a href="category_form.jsp">Create New Category</a>
</div>
<br>

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
			ID
		</th>
		<th>
			Category Name
		</th>
		<th>
			Actions
		</th>
	</tr>
	
	
		<c:forEach var="cat" items="${listCategory}" varStatus="staus">
		
			<tr>
			
				<td>
					${status.index + 1}
				</td>
				<td>
					${cat.categoryId}
				</td>
				<td>
					${cat.name}
				</td>
				<td>
					<a href="edit_category?id=${cat.categoryId}">Edit</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" id="${cat.categoryId}" class="deleteLink">Delete</a>
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
			categoryId=$(this).attr("id");
		
			if(confirm("Are you sure you want to delete the category with ID " + categoryId + "?")){
			
				window.location = 'delete_category?id='+categoryId;
			
			}
		});
	});
	
});
</script>

</html>