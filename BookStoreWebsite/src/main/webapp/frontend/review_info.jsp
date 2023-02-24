<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Review</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>Write a Review</h2>
	</div>

	<div align="center">

		<form action="submit_review" method="post" id="reviewForm">

			<table style="border: none;  width: 60%">

				<tr>
					<td>
						<h3>You already wrote a review for this book</h3>
					</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>${loggedCustomer.fullname}</td>
				</tr>
				
				<tr>
					<td>
						<hr>
					</td>
				</tr>  
				  
				<tr>
					<td><b style="size: larger">${book.title}</b><br><br>
						<img
					src="data:image/jpg;base64,${book.base64Image}" width="300"
					height="400">
					</td>
					<td>
						<div id="rateYo"></div>
						<input type="hidden" id="rating" name="rating">
						<input type="hidden" id="bookId" name="bookId" value="${book.bookId}">
						<br><br>
						<input type="text" name="headline" size="60" readonly="readonly" value="${review.headline}">
						<br>
						<br>
						<textarea rows="10" cols="70" name="comment" readonly="readonly">${review.comment}</textarea>
					</td>
				</tr>
				
			</table>     
    
		</form>  
   
	</div>  

	<jsp:include page="footer.jsp"></jsp:include>
	

</body> 

  <script type="text/javascript">
  
  $(document).ready(function(){
	
	  $("#rateYo").rateYo({
		    starWidth: "40px",
		    fullStar: true,
		    rating: ${review.rating},
		    readOnly: true
		    
		  });  
  });
  
  </script> 


</html>