<%@include file="../header.jsp" %>
<strong>Login</strong> <p/>
<form action="/MyBlog/user/login" method="post">
login id : <input type="text" name="loginId" value="${user.loginId }" /> <br />
password : <input type="text" name="password" value="${user.password }"/> <br />
<input type="submit" value="Login"/>
 <span style="color:red">${message }</span>
</form>