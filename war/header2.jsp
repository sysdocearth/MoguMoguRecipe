<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory" %>

<!-- ■■header■■ -->
<div id="h-inner">

<div class="container1">
  <div class="box">
  <div id="header-logo" class="clearfix">
  <h1><img src="/img/logo_dummy.png" alt="mogumogu" /></h1>
  </div>
  </div>
  <div class="box">
    <p class="logout">
    <span id="accountId" class="account mrR20">こんにちは&nbsp;<%= UserServiceFactory.getUserService().getCurrentUser().getEmail() %>&nbsp;さん</span>
    <a href="<%= UserServiceFactory.getUserService().createLogoutURL(request.getRequestURI()) %>">ログアウト</a>
    </p>
  </div>
  </div>
</div>

