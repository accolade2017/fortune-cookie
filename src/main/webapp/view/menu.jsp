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

<button type=“button” onclick="location.href='http://localhost:8080/fortune'">占う</button>
	<c:if  test="${authority == 'admin' }" >
		<button type=“button” onclick="location.href='http://localhost:8080/summary'">管理者</button>
	</c:if>



</body>
</html>