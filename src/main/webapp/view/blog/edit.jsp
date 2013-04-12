<%@include file="../header.jsp" %>
<strong>Edit Blog</strong> <p/>
<form action="/MyBlog/blog/edit" method="post">
<input type="hidden" name="id" value="${blog.id }" />
title : <input type="text" name="title" value="${blog.title }" /> <br />
content : <br />
<textarea rows="20" cols="130" name="content">${blog.content }</textarea>  <br />
<input type="submit"/> <span style="color:red">${message }</span>
</form>