<%@include file="../header.jsp" %>
<strong>Register a user</strong> <p/>
<form action="/MyBlog/user/add" method="post">
login id : <input type="text" name="loginId" /> <br />
password : <input type="text" name="password"/> <br />
<input type="submit" value="Register"/>
</form>