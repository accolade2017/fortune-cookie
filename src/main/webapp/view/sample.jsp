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
<title>Sample</title>

<script type="text/javascript">
$(document).ready(function(){
  console.log('fin');

});
</script>
</head>
<body>
  <div class="uk-container">
    <div class="uk-padding uk-section uk-section-primary">
      <h1 class="uk-heading-primary">sample222</h1>
      <button class='uk-button uk-button-default'>BUTTON</button>

      <c:forEach items="${result}" var="item">
        ${item.name}<br />
      </c:forEach>

    </div>
  </div>

</body>
</html>