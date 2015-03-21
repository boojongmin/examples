<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/WEB-INF/view/layout/default/header.jsp" %>
  <div class="container">
    <div class="row">
      <div class="col-md-offset-3 col-md-6 ">
        <table class="table">
          <thead>
            <tr>
              <th>seq</th>
              <th>계좌번호</th>
              <th>잔액</th>
              <th>상세보기</th>
              <th>계좌삭제</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${ accounts }" var="account" varStatus="idx">
              <tr>
                <td><c:out value="${idx.count }"/></td>
                <td><c:out value="${account.accountNumber }"/></td>
                <td><c:out value="${account.amount }"/></td>
                <td><button name="accountDetailBtn" data-account-number="${account.accountNumber }" class="btn btn-success">확인</button></td>
                <td><button name="removeAccountBtn" data-account-number="${account.accountNumber }" class="btn btn-danger">삭제</button></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <div class="row" style="margin-top: 50px;">
      <div class="col-md-12 text-center">
        <a href="/school/account/createAccount" class="btn btn-primary btn-lg" >계좌생성</a>
        <a href="#" class="btn btn-success btn-lg"  data-toggle="modal" data-target="#depositModal" >입금</a>
        <a href="#" class="btn btn-warning btn-lg" data-toggle="modal" data-target="#withdrawalModal" >출금</a>
        <a href="/school/account/withdrawalValidator" class="btn btn-warning btn-lg">출금_validator</a>
        <a href="#" class="btn btn-info btn-lg" data-toggle="modal" data-target="#transferModal" >계좌이체</a>
      </div>
    </div>
  </div>


<!-- 입금 Modal -->
<div class="modal fade" id="depositModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">입금</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="accountNumber">계좌번호</label>
            <input type="number" class="form-control" id="accountNumber" name="accountNumber" placeholder="계좌번호를 입력해주세요.">
          </div>
          <div class="form-group">
            <label for="amount">금액</label>
            <input type="number" class="form-control" id="amount" name="amount" placeholder="금액을 입력해주세요.">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="depositBtn">저장</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
<!-- 출금 Modal -->
<div class="modal fade" id="withdrawalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">출금</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="accountNumber">계좌번호</label>
            <input type="number" class="form-control" id="accountNumber" name="accountNumber" placeholder="계좌번호를 입력해주세요.">
          </div>
          <div class="form-group">
            <label for="amount">금액</label>
            <input type="number" class="form-control" id="amount" name="amount" placeholder="금액을 입력해주세요.">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="withdrawalBtn">저장</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
<!-- transfer Modal -->
<div class="modal fade" id="transferModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">계좌이체</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="accountNumber">내 계좌번호</label>
            <input type="number" class="form-control" name="myAccountNumber" placeholder="계좌번호를 입력해주세요.">
          </div>
          <div class="form-group">
            <label for="accountNumber">상대방 계좌번호</label>
            <input type="number" class="form-control" name="otherAccountNumber" placeholder="계좌번호를 입력해주세요.">
          </div>

          <div class="form-group">
            <label for="amount">금액</label>
            <input type="number" class="form-control" name="amount" placeholder="금액을 입력해주세요.">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="transferBtn">계좌이체1</button>
        <button type="button" class="btn btn-primary" id="transferBtn2">계좌이체2</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>


<%@include file="/WEB-INF/view/layout/default/script.jsp" %>
<script>
$(function(){
	$('[name=accountDetailBtn]').click(function(){
		location.href = '/school/account/accountDetail/' + $(this).data("account-number");
	})


	$('[name=removeAccountBtn]').click(function(){
		confirm('')
    location.href = '/school/account/removeAccount/' + $(this).data("account-number");
  })

  $('#depositBtn').click(function(){
    var data = $('#depositModal form input').serializeArray();
    $.ajax({
      url: '/school/account/deposit',
      //type: 'post',
      //contentType: "application/json",
      //data: JSON.stringify(data),
      data: $('#depositModal form').serialize() ,
      dataType:'json',
      success: function(data, textStatus, jqXHR){
    	  if(data.success){
          location.reload();
    	  }else{
    		  alert(data.description);
    	  }
      },
      error: function(jqXHR, textStatus, errorThrown ){
        alert('입금중 오류가 발생했습니다.\n' + jqXHR.responseText);
      }
    })
  })


  $('#withdrawalBtn').click(function(){
    var data = $('#withdrawalModal form input').serializeArray();
    $.ajax({
      url: '/school/account/withdrawal',
      //type: 'post',
      //contentType: "application/json",
      //data: JSON.stringify(data),
      data: $('#withdrawalModal form').serialize() ,
      dataType:'json',
      success: function(data, textStatus, jqXHR){
        if(data.success){
          location.reload();
        }else{
          alert(data.description);
        }
      },
      error: function(jqXHR, textStatus, errorThrown ){
        alert('출금중 오류가 발생했습니다.\n' + jqXHR.responseText);
      }
    })
  })

  $('#transferBtn').click(function(){
	    var param = $('#transferModal form input').serialize();
	    location.href = '/school/account/transfer?' + param;
  })

  $('#transferBtn2').click(function(){
      var param = $('#transferModal form input').serialize();
      location.href = '/school/account/transfer2?' + param;
  })
})
</script>
<%@include file="/WEB-INF/view/layout/default/footer.jsp" %>