<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../header.jsp" %>

<strong>list blog</strong> <p />

<div id="wrapper">
		<c:forEach items="${blogs }" var="blog">
			<div id="main_box" class="comm_box">
				<div id="bg_ttl">
					<h3><a href="show/${blog.id }">${blog.title }</a></h3>
				</div>
				<div id="ctn_bd">
					${fn:substring(blog.content, 0, 1500) }...
				</div>
				
				<div id="sub_info" class="sub_syl">
				<c:if test="${blog.author.loginId == loginUser.loginId }">
					<a href="edit/${blog.id }">Edit</a>
					<a href="javascript:if (confirm('Delete the blog?')) {window.location='del/${blog.id }'}">Del</a>
					</c:if>
					created: ${blog.createDateStr }
					last updated: ${blog.updateDateStr } by ${blog.author.loginId }
				</div>
			</div>
		</c:forEach>
		
</div>