<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<html>
<head>
    <title>로그인페이지</title>
</head>

<body>
<h2>로그인 </h2>
<span style="color:red;">${param['login'] == 'fail' ? '로그인에 실패했습니다.' : '' }</span>
<form name="form" method="post" action="loginProcess">
<table>
    <tr height="40px">
        <td>사용자아이디</td>
        <td><input type="text" name="userId"></td>
    </tr>
    <tr height="40px">
        <td>패스워드</td>
        <td><input type="password" name="password"></td>
    </tr>
</table>
<table>
    <tr>
        <td align="center"><input type="submit" value="로그인"></td>
        <td align="center"><input type="reset" value="리셋"></td>
    </tr>
</table>
</form>
</body>
</html>