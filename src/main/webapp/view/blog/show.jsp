<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../header.jsp" %>

<strong>blog info</strong> <p />

	<div id="main_box" class="comm_box">
				<div id="bg_ttl">
					<h3>${blog.title }</h3>
				</div>
				<div id="ctn_bd">
					${blog.content }
				</div>
				
				<div id="sub_info"  class="sub_syl">
					<a href="/MyBlog/blog/edit/${blog.id }">Edit</a>
					<a href="/MyBlog/blog/del/${blog.id }">Del</a>
					created: ${blog.createDateStr }
					last updated: ${blog.updateDateStr } by ${blog.author.loginId }
				</div>
	</div>
	
	<div id="cmt_box" class="comm_box">
		<c:forEach items="${blog.comments }" var="cmt">
			<div class="cmt_syl">
				<div style="padding:5px;">${cmt.content }</div>
				<div class="sub_syl">
					<c:if test="${cmt.cmtUser.loginId == loginUser.loginId }">
					<a href="/MyBlog/blog/del_cmt/${cmt.id }/${blog.id}" >Del </a>
					</c:if>
					
						created: ${cmt.cmtDateStr } by ${cmt.cmtUser.loginId }
				</div>
			</div>
		</c:forEach>
		<a id="cmt"></a>
		<form action="/MyBlog/blog/add_cmt" method="post">
		
		Your comment:<br />
		<input type="hidden" name="blogId" value="${blog.id }" />
		<textarea name="content" rows="8" cols="100"></textarea>
		<input type="submit" value="add" />
		</form>
	</div>
	