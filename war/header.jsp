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

  <div class="recipeallsearch">
  <form method="get" id="psearch" method="get" action="/recipe/search">
    <input type="text" id="recipeautocomplete" name="title" class="autocomplete" placeholder="レシピを検索する" style="width:240px;height:28px;"/>
  </form>
  </div>      
  
  </div>
</div>

<div class="container2">

  <div id="menu">
  <ul>
  <li class="postRecipe"><a href="/recipe/create" >レシピを書く</a></li>
  <li class="updateList"><a href="/recipe/newList" >新着レシピ</a></li>
  <li class="classicRecipe"><a href="/recipe/classic" >定番レシピ</a></li>
  </ul> 
  </div>

</div>

