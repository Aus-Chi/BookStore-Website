<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Posted - Online Book Store</title>
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

			<table style="border: none;  width: 60%">

				<tr>
					<td>
						<h2>Your Reviews.</h2>
					</td>
					<td style="margin-right:28px"><b>${loggedCustomer.fullname}</b></td>
					<td></td>
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
					<td style="margin-right: 28px">
						<i>${message}
						</i>
						</td>
				</tr> 
				
  
			</table>     
    
		 
     
	</div>  

	<jsp:include page="footer.jsp"></jsp:include>
	

</body>    


</html>