<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../header.jsp" %>

<strong>list blog</strong> <p />

<c:forEach items="${blogs }" var="blog">
	
	<a href="show/${blog.id }">${blog.title }</a>
	${blog.content }
	${blog.createDateStr }
	${blog.updateDateStr }
	<br />
</c:forEach>