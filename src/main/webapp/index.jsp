<%--
  Created by IntelliJ IDEA.
  User: Gerardo Aquino
  Date: 14/08/13
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/captcha/verify" method="post">
    <c:import url="/captcha/render"></c:import>
    <input type="submit" />
</form>
</body>
</html>