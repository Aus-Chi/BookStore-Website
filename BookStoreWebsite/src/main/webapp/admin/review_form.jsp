<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Review</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<h2 class=pageheading>Edit Review</h2>

	</div>

	&nbsp;

	<div align="center">


		<form action="update_review" method="post" id="reviewForm">
			<input type="hidden" name="id" value="${review.reviewId}">


			<table class="form">

				<tr>

					<td><label>Book: </label></td>
					<td><b>${review.book.title}</b></td>

				</tr>
				<tr>
					<td><label>Rating: </label></td>
					<td><b>${review.rating}</b></td>

				</tr>
				<tr>
					<td><label>Customer: </label></td>
					<td><b>${review.customer.fullname}</b></td>

				</tr>

				<tr>
					<td><label>Headline: </label></td>
					<td><input type="text" size="60" name="headline" id="headline"
						value="${review.headline}"></td>

				</tr>

				<tr>
					<td><label>Comment: </label></td>
					<td><textarea rows="5" cols="70" name="comment" id="comment">${review.comment}</textarea></td>

				</tr>

				<tr>
					<td colspan="2" align="center"><button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
						<button id="buttonCancel">Cancel</button></td>
				</tr>

			</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#reviewForm").validate({
			rules : {
				headline : "required",
				comment : "required"
			},

			messages : {
				headline : " Please enter headline!",
				comment : " Please enter comment"
			}
		});

		$("#buttonCancel").click(function() {
			history.go(-1);
		})
	});
</script>
</html>