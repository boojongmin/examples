<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/WEB-INF/view/layout/default/header.jsp" %>
  <div class="container">
    <div class="row">
      ${ sessionScope.User.name }님 환영합니다.
    </div>
  </div>

<%@include file="/WEB-INF/view/layout/default/script.jsp" %>
<%@include file="/WEB-INF/view/layout/default/footer.jsp" %>