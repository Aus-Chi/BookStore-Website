<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
						<h2>Your Reviews</h2>
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
						<input type="text" name="headline" size="60" placeholder="Headline or summary for your review (required)">
						<br>
						<br>
						<textarea rows="10" cols="70" name="comment" placeholder="write your review details..."></textarea>
					</td>
				</tr>
				
				<tr>
				<td align="center" colspan="2"><button type="submit">Save</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">cancel</button></td></tr>
  
			</table>     
    
		</form>  
   
	</div>  

	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		
		$("#reviewForm").validate({
			rules : {
				
					headline: "required",
					comment: "required"
				},
				
			messages : {
					
					headline: "Please enter headline",
					comment: "Please enter review details"
					
			}
				
		});

		$("#buttonCancel").click(function() {
			history.go(-1);
		});
		 
		  $("#rateYo").rateYo({
		    starWidth: "40px",
		    fullStar: true,
		    onSet: function(rating, rateYoInstance){
		    	$("#rating").val(rating)
		    }
		  });
		 
		});
	
	</script>

</body>    





</html>