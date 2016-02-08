<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.sysdoccode.model.RecipeHead" %>

<c:set var="sitetitle"><fmt:message key="messages.sitetitle" /></c:set>

<c:set var="refMeat" value="<%= RecipeHead.CATEGORY.MEAT %>" />
<c:set var="refFish" value="<%= RecipeHead.CATEGORY.FISH %>" />
<c:set var="refEggTofu" value="<%= RecipeHead.CATEGORY.EGGSTOFU %>" />
<c:set var="refMainfSoup" value="<%= RecipeHead.CATEGORY.MAINFSOUP %>" />
<c:set var="refSideDish" value="<%= RecipeHead.CATEGORY.SIDEDISH %>" />
<c:set var="refDessert" value="<%= RecipeHead.CATEGORY.DESSERT %>" />

<c:set var="sMeat" value="<%= RecipeHead.CATEGORY.MEAT.getStrValue() %>" />
<c:set var="sFish" value="<%= RecipeHead.CATEGORY.FISH.getStrValue() %>" />
<c:set var="sEggTofu" value="<%= RecipeHead.CATEGORY.EGGSTOFU.getStrValue() %>" />
<c:set var="sMainfSoup" value="<%= RecipeHead.CATEGORY.MAINFSOUP.getStrValue() %>" />
<c:set var="sSideDish" value="<%= RecipeHead.CATEGORY.SIDEDISH.getStrValue() %>" />
<c:set var="sDessert" value="<%= RecipeHead.CATEGORY.DESSERT.getStrValue() %>" />

<c:set var="imgMeat" value="<%= RecipeHead.CATEGORY.MEAT.getImgFileName() %>" />
<c:set var="imgFish" value="<%= RecipeHead.CATEGORY.FISH.getImgFileName() %>" />
<c:set var="imgEggTofu" value="<%= RecipeHead.CATEGORY.EGGSTOFU.getImgFileName() %>" />
<c:set var="imgMainfSoup" value="<%= RecipeHead.CATEGORY.MAINFSOUP.getImgFileName() %>" />
<c:set var="imgSideDish" value="<%= RecipeHead.CATEGORY.SIDEDISH.getImgFileName() %>" />
<c:set var="imgDessert" value="<%= RecipeHead.CATEGORY.DESSERT.getImgFileName() %>" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />

<title>${sitetitle}</title>
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
  <li class="last ahere">${f:br(systemInfo.siteConcept)}</li>
  </ul>
</div>
<!-- // pankuz  -->

<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">

<h3>レシピカテゴリ<span class="line mrR10" style="float:right">全&nbsp;${allCount}&nbsp;件</span></h3>
<div class="clearfix">
<form name="recipesearch" method="post" action="/recipe/detail" style="display: inline">
  <input type="hidden" name="key" value=""/>
</form>

<!-- 1 -->
<div id="compeList" class="mrB20">
<div class="compe01">

<ul class="compe02 clearfix">
<c:if test="${meatCount==0}">
<li>
<div class="compeCheck" >
<p>
<img src="${imgMeat}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(meatCount)}&nbsp;件</span><span class="m01 mrT5">主菜（お肉）</span><br />
<span class="m02 mrT10">お肉がメインのおかず<br />炒め物・ハンバーグ など</span>
</p>
</div>
</li>
</c:if>
<c:if test="${meatCount>0}">
<li>
<div class="compeCheck" >
<p>
<a class="link" href="/recipe/list?kind=${refMeat}"><img src="${imgMeat}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(meatCount)}&nbsp;件</span><span class="m01 mrT5">主菜（お肉）</span><br />
<span class="m02 mrT10">お肉がメインのおかず<br />炒め物・ハンバーグ など</span></a>
</p>
</div>
</li>
</c:if>
<c:if test="${fishCount==0}">
<li class="pre">
<div class="compeCheck">
<p>
<img src="${imgFish}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(fishCount)}&nbsp;件</span><span class="m01 mrT5">主菜（お魚）</span><br />
<span class="m02 mrT10">お魚がメインのおかず<br />煮魚・フライ  など</span></p>
</div>
</li>
</c:if>
<c:if test="${fishCount>0}">
<li class="pre">
<div class="compeCheck">
<p>
<a class="link" href="/recipe/list?kind=${refFish}"><img src="${imgFish}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(fishCount)}&nbsp;件</span><span class="m01 mrT5">主菜（お魚）</span><br />
<span class="m02 mrT10">お魚がメインのおかず<br />煮魚・フライ  など</span></a></p>
</div>
</li>
</c:if>

<c:if test="${eggTofuCount==0}">
<li class="last">
<div class="compeCheck">
<p>
<img src="${imgEggTofu}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(eggTofuCount)}&nbsp;件</span><span class="m01 mrT5">主菜（卵・豆腐）</span><br />
<span class="m02 mrT10">卵や豆腐がメインのおかず<br />茶碗蒸し・オムレツなど</span></p>
</div>
</li>
</c:if>
<c:if test="${eggTofuCount>0}">
<li class="last">
<div class="compeCheck">
<p>
<a class="link" href="/recipe/list?kind=${refEggTofu}"><img src="${imgEggTofu}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(eggTofuCount)}&nbsp;件</span><span class="m01 mrT5">主菜（卵・豆腐）</span><br />
<span class="m02 mrT10">卵や豆腐がメインのおかず<br />茶碗蒸し・オムレツなど</span></a></p>
</div>
</li>
</c:if>

</ul>
</div>
</div>
<!-- //1 -->

<!-- 2 -->
<div id="compeList">
<div class="compe01">

<ul class="compe02 clearfix">
<c:if test="${mainSoupCount==0}">
<li>
<div class="compeCheck" >
<p><img src="${imgMainfSoup}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(mainSoupCount)}&nbsp;件</span><span class="m01 mrT5">主食・汁もの</span><br />
<span class="m02 mrT10">お米・パスタ・グラタン<br />シチュー・スープ など</span></p>
</div>
</li>
</c:if>
<c:if test="${mainSoupCount>0}">
<li>
<div class="compeCheck" >
<p>
<a class="link" href="/recipe/list?kind=${refMainfSoup}"><img src="${imgMainfSoup}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(mainSoupCount)}&nbsp;件</span><span class="m01 mrT5">主食・汁もの</span><br />
<span class="m02 mrT10">お米・パスタ・グラタン<br />シチュー・スープ など</span></a></p>
</div>
</li>
</c:if>

<c:if test="${sideDishCount==0}">
<li class="pre">
<div class="compeCheck">
<p><img src="${imgSideDish}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(sideDishCount)}&nbsp;件</span><span class="m01 mrT5">副菜（野菜・きのこ）</span><br />
<span class="m02 mrT10">野菜やきのこのおかず<br />サラダ・あえもの など</span></p>
</div>
</li>
</c:if>
<c:if test="${sideDishCount>0}">
<li class="pre">
<div class="compeCheck">
<p>
<a class="link" href="/recipe/list?kind=${refSideDish}"><img src="${imgSideDish}" width="48" height="48" alt=""  class="pd17"/><span class="count">${f:h(sideDishCount)}&nbsp;件</span><span class="m01 mrT5">副菜（野菜・きのこ）</span><br />
<span class="m02 mrT10">野菜やきのこのおかず<br />サラダ・あえもの など</span></a></p>
</div>
</li>
</c:if>

<c:if test="${dessertCount==0}">
<li class="last">
<div class="compeCheck">
<p><img src="${imgDessert}" width="48" height="48" alt=""  class="pd17"/><span class="count" >${f:h(dessertCount)}&nbsp;件</span><span class="m01 mrT5">愛情の一品</span><br />
<span class="m02 mrT10">デザート・お菓子のおやつ<br />パンケーキ・きなこ など</span></p>
</div>
</li>
</c:if>
<c:if test="${dessertCount>0}">
<li class="last">
<div class="compeCheck">
<p>
<a class="link" href="/recipe/list?kind=${refDessert}"><img src="${imgDessert}" width="48" height="48" alt=""  class="pd17"/><span class="count" >${f:h(dessertCount)}&nbsp;件</span><span class="m01 mrT5">愛情の一品</span><br />
<span class="m02 mrT10">デザート・お菓子のおやつ<br />パンケーキ・きなこ など</span></a></p>
</div>
</li>
</c:if>


</ul>
</div>
</div>
<!-- //2　-->

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
