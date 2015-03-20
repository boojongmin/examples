<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap 101 Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 -->
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">

</head>
<body>
  <nav role="navigation" class="navbar navbar-default navbar-static-top">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" data-target="#navbarCollapse"
          data-toggle="collapse" class="navbar-toggle">
          <span class="sr-only">Toggle navigation</span> <span
            class="icon-bar"></span> <span class="icon-bar"></span> <span
            class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand">BANK</a>
      </div>
      <!-- Collection of nav links and other content for toggling -->
      <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">Profile</a></li>
          <li><a href="#">Messages</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">Login</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container">
    <div class="row">
      ${ sessionScope.User.name }님 환영합니다.
    </div>
  </div>


  <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
  <script src="//code.jquery.com/jquery.js"></script>
  <!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
  <script src="/js/bootstrap.min.js"></script>

  <!-- Respond.js 으로 IE8 에서 반응형 기능을 활성화하세요 (https://github.com/scottjehl/Respond) -->
  <script src="/js/respond.js"></script>
</body>
</html>