<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>占い結果</title>
</head>
<body>

<h1>${fortune.message}</h1>
<img src="${fortune.path }" alt="${fortune.message}" />

<a href="./menu">戻る</a>

</body>
</html>