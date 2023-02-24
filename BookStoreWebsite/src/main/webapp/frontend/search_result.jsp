<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results for ${keyword} Evergreen Books - Online Book
	Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<c:if test="${fn:length(result) == 0}">

			<h2>No Results for "${keyword}"</h2>

		</c:if>
		<c:if test="${fn:length(result) > 0}">

			<h2>Results for "${keyword}"</h2>


			<div align="center">
				<h3 class="message">
					<i>${message}</i>
				</h3>
			</div>

			<div align="center" style="width: 100%; margin: 0 auto;">

				<c:forEach items="${result}" var="book">

					<div align="center" style="max-width: 50%">

						<div align="left">


							<div style="display: flex">
								<div style="display: inline-block; margin: 20px">
									<a href="view_book?id=${book.bookId}"> <img
										src="data:image/jpg;base64,${book.base64Image}" width="100"
										height="150">
									</a>
								</div>
								<div style="margin: 20px; text-align: justify">
									${fn:substring(book.description, 0, 200)}....</div>
							</div>
							<div style="display: inline-block; margin: 20px">
								<div>
									<h3>
										<a href="view_book?id=${book.bookId}"><b>${book.title}</b>
										</a>
									</h3>
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
									<b>$${book.price}</b>
								</div>

							</div>

						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>

	<footer><jsp:directive.include file="footer.jsp" /></footer>

</body>
</html>
