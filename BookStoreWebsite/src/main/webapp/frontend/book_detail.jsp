<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books Detail - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	<br>
	<div align="center">
		<i>${message}</i>
		<table style="width: 80%; border: none">

			<tr>
				<td colspan="3" align="left"><h3>${book.title}</h3>By
					${book.author}</td>
			</tr>
			<tr>
				<td rowspan="2"><img
					src="data:image/jpg;base64,${book.base64Image}" width="300"
					height="400"></td>
				<td valign="top" align="left"><c:forTokens
						items="${book.ratingStars}" delims="," var="star">

						<c:if test="${star eq 'on'}">

							<img alt="" src="images/rating_on.png">

						</c:if>

						<c:if test="${star eq 'off'}">

							<img alt="" src="images/rating_off.png">

						</c:if>

						<c:if test="${star eq 'half'}">

							<img alt="" src="images/rating_half.png">

						</c:if>

					</c:forTokens>&nbsp;&nbsp;<a href="#reviews">${fn:length(book.reviews)} Reviews</a></td>
				<td valign="top" rowspan="2" width="20%"><h3>$${book.price}</h3>
					<button id="buttonAddToCart">Add to Cart</button></td>

			</tr>

			<tr>
				<td valign="top" style="text-align: justify">
					${book.description}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td id="reviews">Customer Reviews</td>
				<td colspan="2"><button id="buttonWriteReview">Write a Customer Review</button></td>
			</tr>

			<tr>
				<td colspan="3">

					<table style="border: none"> 
						<c:forEach items="${book.reviews}" var="review">
							<tr>

								<td><c:forTokens items="${review.stars}" delims=","
										var="star">

										<c:if test="${star eq 'on'}">

											<img alt="" src="images/rating_on.png">

										</c:if>

										<c:if test="${star eq 'off'}">

											<img alt="" src="images/rating_off.png">

					 					</c:if>
									</c:forTokens> - <b>${review.headline}</b></td>


							</tr>

							<tr>
								<td>by ${review.customer.fullname} on ${review.reviewTime}
								</td>
							</tr>

							<tr>
								<td><i>${review.comment}</i></td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>

				</td>
			</tr>

		</table>

	</div>

	<footer><jsp:directive.include file="footer.jsp" /></footer>

<script type="text/javascript">

$(document).ready(function(){
	

$("#buttonWriteReview").click(function() {
	window.location = 'write_review?book_id='+${book.bookId};
});

$("#buttonAddToCart").click(function() {
	window.location = 'add_to_cart?book_id='+${book.bookId};
});

});
 
</script>

</body>
</html>
