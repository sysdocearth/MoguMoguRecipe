<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="sitetitle"><fmt:message key="messages.sitetitle" /></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />

<title>${sitetitle}</title>

<link type="text/css" href="/css/jquery-ui-1.10.4/themes/base/jquery.ui.theme.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/css/pi/import.css" media="all" />
<link rel="stylesheet" href="/css/layout-common.css" type="text/css"/>
<!-- Start JavaScript -->
<script type="text/javascript" src="/js/import.js"></script>
<!-- //Start JavaScript -->
</head>

<body>
<div id="wrapper">
<!-- ■header■ -->
<div id="header">
<c:import url="/header.jsp" />
</div>
<!-- // header  -->

<!-- ■pankuz■ -->
<div id="pankuz">
  <ul class="pankuz-navi">
  <li class="last ahere"></li>
  </ul>
</div>
<!-- // pankuz  -->

<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">
<form name="recipesearch" method="post" action="/recipe/detail" style="display: inline">
  <input type="hidden" name="key" value=""/>
</form>

<h3 class="page-con"><img src="/img/Knife-fork.png" alt="" class="page-title"/>システムエラー</h3>

<div class="ui-widget w870" style="margin:30 auto">
  <div class="ui-state-error ui-corner-all" style="margin-bottom: 20px; padding: 1.0em;"> 
   <p class="text-left"><span class="ui-icon ui-icon-alert" style="float: left; margin-right: 1.0em;"></span>
   <strong>サーバーへのアクセスで失敗しました。管理者へご連絡ください。</strong>
   </p>
  </div>
  <span style="padding: 0, 3.0em;">${f:br(errormessage)}</span>
</div>

  
<div>
<ul class="toBack">
<li class="txtLink"><a href="/">＜&nbsp;HOME へ</a></li></ul>
</div>

</div>
<!-- //clearfix -->
</div>
<!--　//contents-inner　-->
</div>
<!-- //■■contents■■ -->
<!-- ■■footer■■ -->
<c:import url="/footer.jsp">
  <c:param name="footerString"><fmt:message key="label.footerString" /></c:param>
</c:import>
<!-- //■■footer■■ -->
</div>
</body>
</html>
