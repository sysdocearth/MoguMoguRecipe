<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="sitetitle"><fmt:message key="messages.sitetitle" /></c:set>
<c:set var="categoryselect"><fmt:message key="messages.select" /></c:set>

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
		$("#recipepost").validate({
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
	if($("#recipepost").valid()){
		document.recipepost.submit();
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
	$('#check_invalid').on('change',function(){
			var chk = $(this).prop('checked'),
		  	obj = $('#span_starRating');
			if(chk) {
				//window.alert('checkされました'); 
				//obj.style.visibility = "hidden";
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
  <li class="last ahere">レシピを書く</li>
  </ul>
</div>
<!-- // pankuz  -->

<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">


<h3 class="page-con"><img src="/img/create-t.png" alt="" class="page-title"/>レシピを書く</h3>

<div class="clearfix">
<form name="recipesearch" method="post" action="/recipe/detail" style="display: inline">
  <input type="hidden" name="key" value=""/>
</form>
<form id="recipepost" name="recipepost" method="post" action="/recipe/postEntry" style="display: inline">
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
      <div>
        <select name="category" class="form-select w300" style="margin-left:2px">
          <option ${f:select("category"  , null)} data-image="/img/blank.gif">${categoryselect}</option>
          <c:forEach var="orgCategory" items="${categoryList}" varStatus="status">
            <option ${f:select("category", f:h(orgCategory))} data-image="${f:h(orgCategory.imgFileName)}">${f:h(orgCategory.strValue)}</option>
          </c:forEach>
        </select>
        <input type="checkbox" name="check_classic" id="check_classic" class="mrL20"><label for="check_classic">&nbsp;定番レシピに登録する</label>
      </div>
      </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle req">レシピの紹介</p></th>
      <td colspan="3">
      	<textarea name="introduction" class="form-textarea w99p" style="margin-top:5px">${f:h(introduction)}</textarea>
      </td>
    </tr>
    <tr>
      <th scope="col"><p class="coltitle req">もぐもぐレベル</p></th>
      <td style="border-right: none;">
        <div id="span_starRating" >
        <p>低<span class="starRating">
        <input id="rating5" type="radio" name="mogumoguLevel" value="5">
        <label for="rating5">5</label>
        <input id="rating4" type="radio" name="mogumoguLevel" value="4">
        <label for="rating4">4</label>
        <input id="rating3" type="radio" name="mogumoguLevel" value="3" checked>
        <label for="rating3">3</label>
        <input id="rating2" type="radio" name="mogumoguLevel" value="2">
        <label for="rating2">2</label>
        <input id="rating1" type="radio" name="mogumoguLevel" value="1">
        <label for="rating1">1</label>
        </span>高</p>
        </div>
        <div class="mrT10">
        <input type="checkbox" name="check_invalid" id="check_invalid"><label for="check_invalid">&nbsp;ひとまずメモ登録（評価なし）</label>
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
    <a href="javascript:void(0);" onClick="requiredcheck();" class="submit_btn">登録する</a>
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
