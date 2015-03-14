<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.school.domain.User" %>

<html>
<head>
    <title>로그인페이지</title>
</head>
<body>
<h2>로그인 성공</h2>
환영합니다. ${sessionScope.User.name }님
</body>
</html>