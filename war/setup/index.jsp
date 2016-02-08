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
<script type="text/javascript" src="/js/import.js"></script>
<script>
function requiredcheck(){
		$("#setuppost").validate({
		rules: {
			ownerName:"required"
			,mailAddress:"required"
		},
		messages: {
			ownerName: "「オーナー名前」を入力してください"
			,mailAddress:"「連絡先メールアドレス」を入力してください"
		},
		 errorPlacement: function(error,element){
           // (入力フィールドの)name+’_err’のidをもつlabelに出力
		   error.insertAfter($('#'+ element.attr('name') + '_err'));
		 },
		 errorClass: "err"
		 ,errorLabelContainer: "#errorList"
		 ,wrapper: "li"
	}).form();
	if($("#setuppost").valid()){
		document.setuppost.submit();
	}
}
</script>

</head>

<body>
<div id="wrapper">
<!-- ■header■ -->
<div id="header">
<c:import url="/header2.jsp" />
</div>
<!-- // header  -->

<!-- ■■contents■■ -->
<div id="contents">
<div id="contents-inner">


<h3>セットアップ</h3>
<div class="clearfix">

<form id="setuppost" name="setuppost" method="post" action="/setup/postEntry" style="display: inline">
  <input type="hidden" name="systemInfoKey" value="${f:h(systemInfoKey)}"/>
  <div class="ta-form mrB30 mrT30">
    <table class="typeA w870">
    <tr>
      <th scope="col" class="w200 req">オーナー名前</th>
      <td>
      <input type="text" ${f:text("ownerName")} class="form-txt w99p" />
      </td>
    </tr>
    <tr>
      <th scope="col" class="req">連絡先メールアドレス</th>
      <td>
        <input type="text" ${f:text("mailAddress")} class="form-txt w99p" />
      </td>
    </tr>
    <tr>
      <th scope="col">サイトコンセプト</th>
      <td colspan="3">
      	<textarea name="siteConcept" class="form-textarea-h w99p h150">${f:h(siteConcept)}</textarea>
      </td>
    </tr>
    </table>
    <div class="err mrT10">
    <ul id="errorList">${errors.message}</ul>
  	</div>
  	
  </div>
  <p class="btm-s">
    <a href="javascript:void(0);" onClick="requiredcheck();" class="submit_btn">${button_desc}</a>
  </p>
</form>

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
