<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<body>

<form action="./fortune" method="post">
  <button type=“submit”>占う</button>
</form>
<c:if test="${isAdmin}" >
    <button type=“button” onclick="location.href='./summary'">管理者</button>
</c:if>

<button type=“button” onclick="location.href='./logout'">ログアウト</button>




</body>
</html>