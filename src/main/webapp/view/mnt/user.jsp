<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/uikit.min.css" />
<script src="/js/jquery.min.js"></script>
<script src="/js/uikit.min.js"></script>
<script src="/js/uikit-icons.min.js"></script>
<title>ユーザー管理</title>

<script type="text/javascript">
$(document).ready(function(){
  console.log('fin');
});
</script>
</head>
<body>
  <div class="uk-container">
      <h1 class="uk-heading-line uk-heading-small"><span>ユーザー管理</span></h1>
      <div>
      <a href="/mnt/user/add">
        <span uk-icon="icon: plus-circle; ratio: 2"></span>
        </a>
      </div>
      <table class="uk-table uk-table-striped uk-table-justify">
        <thead>
          <tr>
            <th>ログインID</th>
            <th>ユーザー名</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${users}" var="user">
            <tr>
              <td><c:out value="${user.loginId}" /></td>
              <td><c:out value="${user.name}" /></td>
              <td><c:out value="${user.userId}" /></td>
            </tr>
          </c:forEach>
          <tr>
            <td></td>
            <td></td>
            <td></td>
        </tbody>
      </table>

<!--
      <c:forEach items="${result}" var="item">
        ${item.name}<br />
      </c:forEach>
-->

  </div>

</body>
</html>