<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.table1 {
  border: 1px solid gray;
}
.table1 th, .table1 td {
  border: 1px solid gray;
}
</style>
<script src="/js/jquery.min.js"></script>
<script src="/js/uikit.min.js"></script>
<script src="/js/uikit-icons.min.js"></script>

<script type="text/javascript">
$(function() {
    $('select').change(function() {

       var val = $('select').val();
       window.location.href ="test?select="+val;
    })
});
</script>
</head>

<h1>占い結果画面</h1>
<body>
    <select  >
    	<option value="">選択して下さい</option>
      <c:forEach items="${ymd}" var="item">
      <c:choose>
      	<c:when test="${selected == item.fortuneYmd}">
          <option selected value=${item.fortuneYmd}>${item.fortuneYmd}</option>
        </c:when>
        <c:when test="${selected != item.fortuneYmd}">
          <option value=${item.fortuneYmd}>${item.fortuneYmd}</option>
        </c:when>
      </c:choose>
     </c:forEach>
    </select>
    <table border="1"	>
   <tr>
     <th	width="200"	bgcolor="red">名前</th><th	width="200"	bgcolor="red">score</th>
   </tr>
      <c:forEach items="${result}" var="item">
          <tr>
           <th	width="200">${item.loginId}</th><th	width="200">${item.score}</th>
          </tr>
     </c:forEach>
   </table>
</body>
</html>