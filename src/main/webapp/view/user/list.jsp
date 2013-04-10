<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong>list user</strong> <p />

<c:forEach items="${users }" var="user">
	${user.loginId }
	${user.password }
	${user.createDate }
	<br />
</c:forEach>