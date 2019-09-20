<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form name="Login" method="post" action="/cgi-bin/Login.cgi">
 <h1>Login</h1>
 <p>UserID</p>
 <p>
 <input type="text" name="user_id">
 </p>
 <p>Password</p>
 <p>
  <input type="password" name="password">
 </p>
 <p>
  <input type="submit" value="login">
  </form>
</p>
</body>
</html>