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
  <li class="last ahere">レシピを絞り込む</li>
  </ul>
</div>
<!-- // pankuz  -->

<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">

<h3 class="page-con"><img src="/img/Knife-fork.png" alt="" class="page-title"/><span style="font-size:22px">「${searchTitle}」</span>&nbsp;に一致するレシピ&nbsp;${fn:length(hitRecipeList)}&nbsp;件</h3>

<c:if test="${fn:length(hitRecipeList) == 0}">
<div class="ui-widget w870" style="margin:0 auto">
  <div class="ui-state-highlight ui-corner-all" style="margin-bottom: 20px; padding: 1.0em;"> 
   <p class="text-left"><span class="ui-icon ui-icon-info" style="float: left; margin-right: 1.0em;"></span>
   <strong>「${searchTitle}」&nbsp;に一致するレシピが見つかりませんでした。</strong>
   </p>
   </div>
</div>
</c:if>


<div class="clearfix">
<form name="recipesearch" method="post" action="/recipe/detail" style="display: inline">
  <input type="hidden" name="key" value=""/>
</form>
<div class="ta-form mrB50">
  <table class="typeB w870">
    <c:forEach var="hitRecipe" items="${hitRecipeList}" varStatus="hs" >
      <tr class="upper">       
        <td class="text-center w40" style="padding-left:16px;padding-right:16px;" rowspan=2>${hs.count}</td>
        <td class="w480" style="padding-top:30px;">
        <a href="/recipe/detail?key=${f:h(hitRecipe.key)}">
          <span class="recipetitle">${f:h(hitRecipe.title)}</span>
        </a>
        </td>
        <td class="text-right" rowspan=2 class="w100">
        <img src="/img/watch.ico" width="25" height="25" />
        </td>        
        <td rowspan=2 class="w100"><span class="line">${f:h(hitRecipe.minute)}&nbsp;分<br>${f:h(hitRecipe.servings)}&nbsp;人前</span></td>
        <td rowspan=2 class="w150">
        <span class="line-thin">
        <fmt:formatDate value="${hitRecipe.updateDate}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;up</span>
        <c:if test="${hitRecipe.mogumoguLevel == 5}">
        <img class="page-hitRecipe" src="/img/heart-5.png" title="もぐもぐレベル 5 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${hitRecipe.mogumoguLevel == 4}">
        <img class="page-hitRecipe" src="/img/heart-4.png" title="もぐもぐレベル 4 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${hitRecipe.mogumoguLevel == 3}">
        <img class="page-hitRecipe" src="/img/heart-3.png" title="もぐもぐレベル 3 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${hitRecipe.mogumoguLevel == 2}">
        <img class="page-hitRecipe" src="/img/heart-2.png" title="もぐもぐレベル 2 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${hitRecipe.mogumoguLevel == 1}">
        <img class="page-hitRecipe" src="/img/heart-1.png" title="もぐもぐレベル 1 - 家族の評価" class="bal"/>
        </c:if>
        </td>
      </tr>
      <tr>
        <td style="padding-bottom:30px;">
          <p class="grey">${f:br(hitRecipe.introduction)}</p>
		  <span class="line-thin mrR30" style="float:right">Recipe&nbsp;by&nbsp;${f:h(hitRecipe.author)}</span>          
        </td>
      </tr>
    </c:forEach>
  </table>
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
