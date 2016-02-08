<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.sysdoccode.model.RecipeHead" %>

<c:set var="sitetitle"><fmt:message key="messages.sitetitle" /></c:set>
<c:set var="categoryselect"><fmt:message key="messages.select" /></c:set>

<c:set var="refMeat" value="<%= RecipeHead.CATEGORY.MEAT %>" />
<c:set var="refFish" value="<%= RecipeHead.CATEGORY.FISH %>" />
<c:set var="refEggTofu" value="<%= RecipeHead.CATEGORY.EGGSTOFU %>" />
<c:set var="refMainfSoup" value="<%= RecipeHead.CATEGORY.MAINFSOUP %>" />
<c:set var="refSideDish" value="<%= RecipeHead.CATEGORY.SIDEDISH %>" />
<c:set var="refDessert" value="<%= RecipeHead.CATEGORY.DESSERT %>" />

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
<!-- <msdropdown> -->
<link rel="stylesheet" type="text/css" href="/css/msdropdown/dd.css" />
<script src="/js/msdropdown/jquery.dd.js"></script>
<!-- </msdropdown> -->
<script>
function requiredcheck(){
		$("#recipeupdate").validate({
		rules: {
			title:"required"
			,category:"required"
			,ingredients:"required"
			,directions :"required"
			,introduction:"required"
			,author:"required"
			,password:"required"
		},
		messages: {
			title: "「レシピタイトル」を入力してください"
			,category:"「レシピカテゴリ」を入力してください"
			,ingredients:"「材料」を入力してください"
			,directions:"「作り方」を入力してください"
			,introduction:"「レシピ照会文」を入力してください"
			,author:"「ペンネーム」を入力してください"
			,password:"「パスワード」を入力してください"
		},
		 errorPlacement: function(error,element){
           // (入力フィールドの)name+’_err’のidをもつlabelに出力
		   error.insertAfter($('#'+ element.attr('name') + '_err'));
		 },
		 errorClass: "err"
		 ,errorLabelContainer: "#errorList"
		 ,wrapper: "li"
	}).form();
	if($("#recipeupdate").valid()){
		document.recipeupdate.submit();
	}
}
$(document).ready(function(e) {		
	//convert
	$("select").msDropdown({roundedBorder:false});
});
(function($){
    $.fn.visiblePassBox = function(){
        $(this).each(function(){
            var passInput = $(this);
            var passVisible = $('<input type="text" value="" name="'+passInput.attr('name')+'" id="'+passInput.attr('id')+'_visible" class="form-txt w150" />');
            var passCheck = $('<input type="checkbox"/>');
            passInput.after(passCheck);
            
            passCheck.click(function(){
                if(this.checked){
                    passInput.replaceWith(passVisible.val(passInput.val()));
                }else{
                    passVisible.replaceWith(passInput.val(passVisible.val()));
                }
            });
        });
    }
})(jQuery);
jQuery(function($){
    $('#password').visiblePassBox();
});
</script>
<script type="text/javascript">  
$(function() {
	var chk_invalid = $('#check_invalid').prop('checked');
	if(chk_invalid){
		document.getElementById("span_starRating").style.visibility = "hidden";
	}else{
		document.getElementById("span_starRating").style.visibility = "visible";  
	}
	
	$('#check_invalid').on('change',function(){
			var chk = $(this).prop('checked'),
		  	obj = $('#span_starRating');
			if(chk) {
				document.getElementById("span_starRating").style.visibility = "hidden";
			} else {
				document.getElementById("span_starRating").style.visibility = "visible";
			}
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
  <li><a href="/recipe">HOME</a></li>
  <li><a href="/recipe/list?kind=${recipeHead.category}">${f:h(pankzbefore)}</a></li>
  <li><a href="/recipe/detail?key=${f:h(recipeHead.key)}">${f:h(title)}</a></li>
  <li class="last ahere">レシピを編集する</li>
  </ul>
</div>
<!-- // pankuz  -->

<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">


<h3 class="page-con"><img src="/img/Knife-fork.png" alt="" class="page-title"/>レシピを編集する</h3>

<div class="clearfix">
<form name="recipesearch" method="post" action="/recipe/detail" style="display: inline">
  <input type="hidden" name="key" value=""/>
</form>
<form id="recipeupdate" name="recipeupdate" method="post" action="/recipe/updateEntry" style="display: inline">
  <input type="hidden" name="key" value="${f:h(recipeHead.key)}"/>
  <div class="ta-form mrB30 mrT30">
    <table class="typeD w870">
    <tr>
      <th scope="col" class="w120"><p class="coltitle req">レシピタイトル</p></th>
      <td colspan="3" >
      <input type="text" ${f:text("title")} class="form-txt w99p" />
      </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle req">レシピカテゴリ</p></th>
      <td colspan="3">
        <select name="category" class="form-select w300" style="margin-left:2px">
          <c:forEach var="orgCategory" items="${categoryList}" varStatus="status">
            <c:if test="${orgCategory!=null && status.index==0}">
              <option ${f:select("category", null)} data-image="/img/blank.gif">${categoryselect}</option>
            </c:if>
            <c:choose>
              <c:when test="${orgCategory==null}">
                <option ${f:select("category", null)} selected="selected" data-image="/img/blank.gif">${categoryselect}</option>
              </c:when>
              <c:when test="${orgCategory==recipeHead.category}">
                <option ${f:select("category", f:h(orgCategory))} data-image="${f:h(orgCategory.imgFileName)}" selected="selected">${f:h(orgCategory.strValue)}</option>
              </c:when>
              <c:otherwise>
                <option ${f:select("category", f:h(orgCategory))} data-image="${f:h(orgCategory.imgFileName)}">${f:h(orgCategory.strValue)}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
        <c:if test="${classicRecipe==null or classicRecipe==''}">         
        <input type="checkbox" name="check_classic" id="check_classic" class="mrL20"><label for="check_classic">&nbsp;定番レシピに登録する</label>
        </c:if>
        <c:if test="${classicRecipe=='y'}">         
        <input type="checkbox" name="check_classic" id="check_classic" class="mrL20" checked="checked"><label for="check_classic">&nbsp;定番レシピに登録する</label>
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle">レシピの紹介</p></th>
      <td colspan="3">
      	<textarea name="introduction" class="form-textarea w99p" style="margin-top:5px">${f:h(introduction)}</textarea>
      </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle req">もぐもぐレベル</p></th>
      <td style="border-right: none;">
      <div id="span_starRating" >
        <p>低<span class="starRating">
        <c:if test="${recipeHead.mogumoguLevel!=5}">
        <input id="rating5" type="radio" name="mogumoguLevel" value="5">
        </c:if>
        <!-- 5 -->
        <c:if test="${recipeHead.mogumoguLevel==5}">
        <input id="rating5" type="radio" name="mogumoguLevel" value="5" checked>
        </c:if>
        <label for="rating5">5</label>
        <!-- 4 -->
        <c:if test="${recipeHead.mogumoguLevel!=4}">
        <input id="rating4" type="radio" name="mogumoguLevel" value="4">
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel==4}">
        <input id="rating4" type="radio" name="mogumoguLevel" value="4" checked>
        </c:if>
        <label for="rating4">4</label>
        <!-- 3 -->
        <c:if test="${recipeHead.mogumoguLevel!=3}">
        <input id="rating3" type="radio" name="mogumoguLevel" value="3">
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel==3}">
        <input id="rating3" type="radio" name="mogumoguLevel" value="3" checked>
        </c:if>
        <label for="rating3">3</label>
        <!-- 2 -->
        <c:if test="${recipeHead.mogumoguLevel!=2}">
        <input id="rating2" type="radio" name="mogumoguLevel" value="2">
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel==2}">
        <input id="rating2" type="radio" name="mogumoguLevel" value="2" checked>
        </c:if>
        <label for="rating2">2</label>
        <!-- 1 -->
        <c:if test="${recipeHead.mogumoguLevel!=1}">
        <input id="rating1" type="radio" name="mogumoguLevel" value="1">
        </c:if>
        <c:if test="${recipeHead.mogumoguLevel==1}">
        <input id="rating1" type="radio" name="mogumoguLevel" value="1" checked>
        </c:if>
        <label for="rating1">1</label>
        </span>高</p>
        </div>
        <div class="mrT10">
        <c:if test="${noRatings==null or noRatings==''}">
        <input type="checkbox" name="check_invalid" id="check_invalid"><label for="check_invalid">&nbsp;ひとまずメモ登録（評価なし）</label>
        </c:if>
        <c:if test="${noRatings=='y'}">
        <input type="checkbox" name="check_invalid" id="check_invalid" checked="checked"><label for="check_invalid">&nbsp;ひとまずメモ登録（評価なし）</label>
        </c:if>
        </div>
      </td>
      <th scope="col" class="w120"><p class="coltitle req">分量・時間(分)</p></th>
        <td style="border-left: none;">
          <p>
          <input type="text" ${f:text("servings")} name="servings" class="form-txt w50" />&nbsp;人分&nbsp;&nbsp;&nbsp;
          <input type="text" ${f:text("minute")}   name="minute"   class="form-txt w50" />&nbsp;分&nbsp;&nbsp;&nbsp;
          <input type="text" ${f:text("kcal")}     name="kcal"     class="form-txt w50" />&nbsp;Kcal
          </p>
          <div class="mrT10"><img width="5" height="5" src="/img/blank.gif" ></div>
        </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle req">材料</p></th>
      <td style="border-right: none;">
        <textarea name="ingredients" class="form-textarea-h" style="width:99%; height:300px; margin-top:5px">${f:h(ingredients)}</textarea>
      </td>
      <th scope="col"><p class="coltitle req">作り方</p></th>
      <td>
      	<textarea name="directions" class="form-textarea-h" style="width:99%; height:300px; margin-top:5px">${f:h(directions)}</textarea>
      </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle">ポイント<br>コツなど</p></th>
      <td colspan="3">
      	<textarea name="tips" class="form-textarea w99p" style="margin-top:5px">${f:h(tips)}</textarea>
      </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle">家族の評価<br>コメント</p></th>
      <td colspan="3">
      	<textarea name="familyComment" class="form-textarea w99p" style="margin-top:5px">${f:h(familyComment)}</textarea>
      </td>
    </tr>
    </table>
    
    <div class="ta-form mrB30 mrT30">
    <table class="typeD w870">
    <tr>
      <th scope="col" class="w120"><p class="coltitle req">ペンネーム</p></th>
      <td style="border-right: none;">
      <input type="text" ${f:text("author")} class="form-txt w99p" />
      </td>
      <th scope="col" class="w120"><p class="coltitle req">編集パスワード</th>
      <td>
      <input id="password" type="password" ${f:text("password")} class="form-txt w150" />Show password
      </td>
    </tr>
    </table>
    </div>
    
    <div class="err mrT10">
    <ul id="errorList">${errors.message}</ul>
    </div>
      	
  </div>
  <p class="btm-s">
    <a href="javascript:void(0);" onClick="requiredcheck();" class="submit_btn">更新する</a>
    <a href="/recipe/" class="cancel_btn">キャンセル</a>
  </p>
</form>

<div class="mrT20">
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
