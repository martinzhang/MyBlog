<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../header.jsp" %>

<strong>blog info</strong> <p />

	${blog.title } <br/>
	${blog.author.loginId } ${blog.createDateStr }  ${blog.updateDateStr }<p />
	${blog.content }
	