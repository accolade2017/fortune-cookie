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
<title>ユーザー管理(追加)</title>

<script type="text/javascript">
$(document).ready(function(){
  console.log('fin');
});
</script>
</head>
<body>
  <div class="uk-container">
      <h1 class="uk-heading-line uk-heading-small"><span>ユーザー管理(追加)</span></h1>
      <div>
        <form action="add" method="POST">
            ログインID：<input type="text" name="loginId" /> <br />
            パスワード：<input type="text" name="password" /> <br />
            名前：<input type="text" name="name" /> <br />
            スラック名:<input type="text" name="slackUserName" />
            <select name="userType"> <br />
                <option value="2">一般</option>
                <option value="1">管理者</option>
            </select> <br />
            <input type="submit" value="登録" />
        </form>
      </div>

  </div>

</body>
</html>