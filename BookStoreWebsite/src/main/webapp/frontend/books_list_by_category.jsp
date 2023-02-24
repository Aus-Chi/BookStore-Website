<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books in ${category.name} - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<jsp:directive.include file="header.jsp" />

<div align="center">

<h2>${category.name}</h2>

</div> 

<div align="center">
<h3 class="message"><i>${message}</i></h3> 


<div align="center" style="width:100%; margin:0 auto;">

<c:forEach items="${listBooks}" var="book">



<div style="display: inline-block; margin: 20px">



<div>
<a href="view_book?id=${book.bookId}">
<img src="data:image/jpg;base64,${book.base64Image}" width="100" height="150">
</a>
</div>
<div>
By <i>${book.author}</i>
</div>
<div>
<c:forTokens items="${book.ratingStars}" delims="," var="star">
						
							<c:if test="${star eq 'on'}">
							
								<img alt="" src="images/rating_on.png">
							
							</c:if>
							
							<c:if test="${star eq 'off'}">
							
								<img alt="" src="images/rating_off.png">
							
							</c:if>
							
							<c:if test="${star eq 'half'}">
							
								<img alt="" src="images/rating_half.png">
							
							</c:if>
						
						</c:forTokens>
</div>
<div>
<b>${book.title}</b>
</div>
<div>
<b>$${book.price}</b>
</div>



</div>
</c:forEach> 

</div>
</div>
<footer><jsp:directive.include file="footer.jsp" /></footer>

</body>
</html> 