<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/WEB-INF/view/layout/default/header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>

<div class="container">
	<div class="row">
		<div class="col-md-offset-3 col-md-6 ">
			<springForm:form modelAttribute="account" method="post" action="/school/account/withdrawalValidatorProc">
				<div class="form-group">
					<label for="accountNumber">계좌번호</label>
					<springForm:input cssClass="form-control" path="accountNumber" placeholder="계좌번호를 입력해주세요." />
					<springForm:errors path="accountNumber" cssStyle="color:red;"/>

				</div>
				<div class="form-group">
					<label for="amount">금액</label>
					<springForm:input cssClass="form-control" path="amount" placeholder="금액을 입력해주세요." />
					<springForm:errors path="amount" cssStyle="color:red;"/>
				</div>
				<div class="row">
					<div class="col-md-12 text-center">
					  <input type="submit" value="확인" class="btn btn-primary" />
					</div>
				</div>
			</springForm:form>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/view/layout/default/script.jsp"%>
<%@include file="/WEB-INF/view/layout/default/footer.jsp"%>