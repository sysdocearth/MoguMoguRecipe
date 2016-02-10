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
<script>
function checkPassword4Edit() {
	$("#recipeform").validate({
	rules: {
		password:"required"
	},
	messages: {
		password: "編集するには「パスワード」を入力してください"
	},
	 errorPlacement: function(error,element) {
       // (入力フィールドの)name+’_err’のidをもつlabelに出力
	   error.insertAfter($('#'+ element.attr('name') + '_err'));
	 },
	 errorClass: "err"
	 ,errorLabelContainer: "#errorList"
	 ,wrapper: "li"
}).form();
if($("#recipeform").valid()){
	var password 	= $( '#password' ).val();
	document.recipeedit.password.value=password;
	document.recipeedit.submit();
}
}

function checkPassword4Delete($title) {
	$("#recipeform").validate({
	rules: {
		password:"required"
	},
	messages: {
		password: "削除するには「パスワード」を入力してください"
	},
	 errorPlacement: function(error,element) {
       // (入力フィールドの)name+’_err’のidをもつlabelに出力
	   error.insertAfter($('#'+ element.attr('name') + '_err'));
	 },
	 errorClass: "err"
	 ,errorLabelContainer: "#errorList"
	 ,wrapper: "li"
}).form();
if($("#recipeform").valid()){
	var ret = confirm("「"+$title + "」"+ "　のレシピを削除します。\nよろしいですか。");
	if(ret==false){return;}
	var password 	= $( '#password' ).val();
	document.recipedelete.password.value=password;
	document.recipedelete.submit();
}
}
</script>
<script type="text/javascript">
$(function() {
	  $('.bal').balloon({
		  position: "top right", 
		  showDuration: "slow",
		  showAnimation: function(d) { this.fadeIn(d);},
	  });
});
</script>
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
  <li><a href="/recipe/">HOME</a></li>
  <li><a href="/recipe/list?kind=${recipeHead.category}">${f:h(pankzbefore)}</a></li>
  <li class="last ahere">${recipeHead.title}</li>
  </ul>
</div>
<!-- // pankuz  -->


<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">

<div class="clearfix">

<form id="recipeedit" name="recipeedit" method="post" action="/recipe/edit" style="display: inline">
  <input type="hidden" name="key" value="${f:h(recipeHead.key)}">
  <input type="hidden" name="password" value="">
</form>
<form id="recipedelete" name="recipedelete" method="post" action="/recipe/deleteEntry" style="display: inline">
  <input type="hidden" name="key" value="${f:h(recipeHead.key)}">
  <input type="hidden" name="password" value="">
</form>
<form name="recipesearch" method="post" action="/recipe/detail" style="display: inline">
  <input type="hidden" name="key" value=""/>
</form>

<div class="err mrL20" style="margin-top:-30px;">
　　<ul id="errorList">${errors.message}</ul>
</div>

<div style="padding-bottom:30px; margin:0 auto;" class="funcMenuBlock w850">
<div class="funcListFrame">

<form id="recipeform" name="recipeform"  style="display: inline">
  <input type="password" ${f:text("password")} id="password" name="password" class="form-password w100 mrB20" placeholder="Password"/>
  <img width="10" height="25" src="/img/blank.gif" >
  <a href="javascript:void(0);" onClick="javascript:checkPassword4Edit()">
    <img width="25" height="25" src="/img/edit-off.ico" style="padding-right:17px;">
  </a>
  <a href="javascript:void(0);" onClick="javascript:checkPassword4Delete('${f:h(recipeHead.title)}')">
    <img width="25" height="25" src="/img/trash-off.ico">
  </a>
</form>
</div>
</div>

<div style="border:solid 1px #CCC;border-width:1px;padding:16px; margin:0 auto;" class="w820 mrB30">

<p class="recipetitle-detail">${recipeHead.title}</p>
<c:if test="${recipeHead.noRatings=='y'}">
<span style="position: relative;">
<img class="page-detail" src="/img/heart-0.png"/>（メモ登録 未作成）
</c:if>
<c:if test="${recipeHead.noRatings==null or recipeHead.noRatings==''}">
        <c:if test="${recipeHead.mogumoguLevel == 5}">
        <img class="page-detail" src="/img/heart-5.png" title="もぐもぐレベル 5 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel == 4}">
        <img class="page-detail" src="/img/heart-4.png" title="もぐもぐレベル 4 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel == 3}">
        <img class="page-detail" src="/img/heart-3.png" title="もぐもぐレベル 3 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel == 2}">
        <img class="page-detail" src="/img/heart-2.png" title="もぐもぐレベル 2 - 家族の評価" class="bal"/>
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel == 1}" >
        <img class="page-detail" src="/img/heart-1.png" title="もぐもぐレベル 1 - 家族の評価" class="bal"/>
        </c:if>
</c:if>
        <span class="line mrL50"><img width="23" height="23" src="/img/watch.ico" class="page-detail">&nbsp;${recipeHead.minute}分</span>
        <span class="line mrL50"><img width="28" height="28" src="/img/serve.ico" class="page-detail">&nbsp;${recipeHead.servings}人前</span>
        <c:if test="${recipeHead.classicRecipe=='y'}">
        <span class="line mrL50"><img width="23" height="23" src="/img/star.png" class="page-detail">&nbsp;定番レシピ</span>
        </c:if>
        <span class="line-thin mrR10" style="float:right">Recipe&nbsp;by&nbsp;${f:h(recipeHead.author)}</span>
<hr class="style-one mrB10">
<span class="line">${f:br(recipeHead.introduction)}</span>

<div class="ta-form mrB10 mrT10">
　　<table class="typeC w820">
    <tr>
      <th class="w90" scope="col"><p class="coltitle">材料</p></th>
      <td class="w220" >${f:br(recipeBody.ingredients)}</td>
      <th class="w90" scope="col"><p class="coltitle">作り方</p></th>
      <td class="line-desc" style="line-height : 200% ;">${f:br(recipeBody.directions)}</td>
    </tr>
  </table>

　　<table class="typeC w820">
    <tr>
      <th class="w90" scope="col"><p class="coltitle">ポイント</p></th>
      <td>${f:br(recipeBody.tips)}</td>
    </tr>
    <tr>
      <th class="w90" scope="col"><p class="coltitle">家族コメント</p></th>
      <td>${f:br(recipeBody.familyComment)}</td>
    </tr>
  </table>
  <div>
  <br>
  <span class="line mrB10" style="float:right;">公開日：<fmt:formatDate value="${recipeHead.createDate}" pattern="yyyy-MM-dd" />
  &nbsp;&nbsp;更新日：<fmt:formatDate value="${recipeHead.updateDate}" pattern="yyyy-MM-dd" />
  </span>
  </div>
</div>
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
