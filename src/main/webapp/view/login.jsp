<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style>
span.error {
    color: red;
}
</style>
</head>
<body>
<form name="Login" method="post" action="/login">
 <h1>Login</h1>
 <span class="error">${errMsg}</span>
 <p>LoginID</p>
 <p>
 <input type="text" name="loginId">
 </p>
 <p>Password</p>
 <p>
  <input type="password" name="password">
 </p>
 <p>
  <input type="submit" value="login">
</p>
  </form>
</body>
</html>