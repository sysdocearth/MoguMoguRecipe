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
<link rel="stylesheet" type="text/css" href="/css/pi/import.css" media="all" />
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
  <li class="last ahere">好き嫌いの激しい 長女 ゆいちゃん が おいしく食べたレシピを保存していきます。</li>
  </ul>
</div>
<!-- // pankuz  -->

<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">

<h3>レシピカテゴリ</h3>
<div class="clearfix">

<!-- 1 -->
<div id="compeList" class="mrB20">
<div class="compe01">

<ul class="compe02 clearfix">
<li>
<div class="compeCheck" >
<p>
<a href="/category1/"><img src="/img/meat.png" width="48" height="48" alt=""  class="pd17"/><span class="count">2 件</span><span class="m01 mrT5">主菜（お肉）</span><br />
<span class="m02 mrT10">お肉がメインのおかず<br />炒め物・ハンバーグ など</span></a>
</p>
</div>
</li>

<li class="pre">
<div class="compeCheck">
<p>
<a href="/payslip/config/index?corpKey=${f:h(corpKey)}"><img src="/img/fish.png" width="48" height="48" alt=""  class="pd17"/><span class="count">2 件</span><span class="m01 mrT5">主菜（お魚）</span><br />
<span class="m02 mrT10">お魚がメインのおかず<br />煮魚・フライ  など</span></a></p>
</div>
</li>


<li class="last">
<div class="compeCheck">
<p>
<a href="/payslip/partner/index?corpKey=${f:h(corpKey)}"><img src="/img/egg.png" width="48" height="48" alt=""  class="pd17"/><span class="count">2 件</span><span class="m01 mrT5">主菜（卵・豆腐）</span><br />
<span class="m02 mrT10">卵や豆腐がメインのおかず<br />茶碗蒸し・オムレツなど</span></a></p>
</div>
</li>

</ul>
</div>
</div>
<!-- //1 -->

<!-- 2 -->
<div id="compeList">
<div class="compe01">

<ul class="compe02 clearfix">
<li>
<div class="compeCheck" >
<p>
<a href="javascript:readPayslipFormat('${f:h(corpKey)}')"><img src="/img/soop.png" width="48" height="48" alt=""  class="pd17"/><span class="count">2 件</span><span class="m01 mrT5">主食・汁もの</span><br />
<span class="m02 mrT10">お米・パスタ・グラタン<br />シチュー・スープ など</span></a></p>
</div>
</li>

<li class="pre">
<div class="compeCheck">
<p>
<a href="/payslip/config/index?corpKey=${f:h(corpKey)}"><img src="/img/carrots.png" width="48" height="48" alt=""  class="pd17"/><span class="count">2 件</span><span class="m01 mrT5">副菜（野菜・きのこ）</span><br />
<span class="m02 mrT10">野菜やきのこのおかず<br />サラダ・あえもの など</span></a></p>
</div>
</li>

<li class="last">
<div class="compeCheck">
<p>
<a href="/payslip/partner/index?corpKey=${f:h(corpKey)}"><img src="/img/cake.png" width="48" height="48" alt=""  class="pd17"/><span class="count" >13 件</span><span class="m01 mrT5">愛情の一品</span><br />
<span class="m02 mrT10">デザート・お菓子のおやつ<br />パンケーキ・きなこ など</span></a></p>
</div>
</li>

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
<c:import url="footer.jsp">
  <c:param name="footerString"><fmt:message key="label.footerString" /></c:param>
</c:import>
<!-- //■■footer■■ -->
</div>
</body>
</html>
