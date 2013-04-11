<%@include file="../header.jsp" %>
<strong>Add Blog</strong> <p/>
<form action="/MyBlog/blog/add" method="post">
title : <input type="text" name="title" value="${blog.title }" /> <br />
content : <br />
<textarea rows="20" cols="130" name="content">${blog.content }</textarea>  <br />
<input type="submit"/> <span style="color:red">${message }</span>
</form>