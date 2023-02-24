
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
<a href="${pageContext.request.contextPath}/admin/">
<img src="../images/BookstoreAdminLogo.png" />
</a>
<div>
Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a>
</div>
<br>
<b>
<div id="headermenu">

<div class="menu_item">

	<a href="list_users">
		<img id="img" alt="" src="../images/man.png"><br />Users
	</a> 

</div>

<div>
<a href="list_category">
<img id="img" alt="" src="../images/categories.png"><br />Categories
</a> 

</div>

<div>
<a href="list_books"><img id="img" alt="" src="../images/stack-of-books.png"><br />Books</a> 

</div>

<div>
<a href="list_customers"><img id="img" alt="" src="../images/customer.png"><br />Customers</a> 

</div>
 
<div>
<a href="list_reviews"><img id="img" alt="" src="../images/code-review.png"><br />Reviews</a> 

</div>

<div>
<a href="list_order"><img id="img" alt="" src="../images/received.png"><br />Orders</a>

</div>

</div>
</b>
</div>