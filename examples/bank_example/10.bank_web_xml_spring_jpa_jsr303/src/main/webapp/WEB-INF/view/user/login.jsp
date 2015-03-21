<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/WEB-INF/view/layout/default/header.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-md-offset-3 col-md-6 ">
        <div class="panel panel-default">
          <div class="panel-heading">로그인</div>
          <div class="panel-body">
            <span style="color:red;">${ param.login == 'fail'? '로그인에 실패했습니다.' : '' }</span>
            <form class="form-horizontal" role="form" action="/school/user/loginProc"
                method="post">
              <div class="form-group">
                <label for="inputEmail1" class="col-lg-2 control-label">ID</label>
                <div class="col-lg-10">
                  <input type="text" class="form-control" name="userId" value="${sessionScope.User.userId }"
                    placeholder="ID를 입력해주세요">
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword1" class="col-lg-2 control-label">Password</label>
                <div class="col-lg-10">
                  <input type="password" class="form-control" name="password"
                    placeholder="Password">
                </div>
              </div>
              <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10 text-center">
                  <button type="submit" class="btn btn-default">Sign in</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
<%@include file="/WEB-INF/view/layout/default/script.jsp" %>
<%@include file="/WEB-INF/view/layout/default/footer.jsp" %>