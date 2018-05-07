<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ page import="java.net.URLEncoder"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<html>
<head>
<title>장바구니 조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncCntChange(i){
	var CntChange = document.getElementById("cntChange"+i);
	CntChange.type = "button";
}

function fncCntChangeSet(cartNo, i){
	var cartCnt = document.getElementById("cartCnt"+i).value;
	var prodCnt = document.getElementById("prodCnt"+i).value;

	if(cartCnt == null || cartCnt.length<1){
		alert("수량은 반드시 입력하셔야 합니다.");
		return;
	}
	if(parseInt(cartCnt) > parseInt(prodCnt)){
		alert("재고 수량을 초과할 수 없습니다.");
		return;
	}
	
	location.href="/updateCart.do?cartNo="+cartNo+"&cartCnt="+cartCnt;
}

function fncPurchase(){
	var cartList = "";
	var listSize = ${list.size()};
	
	for(i=1; i<=listSize; i++){
		if(document.getElementById("cartChk"+i).checked){
			cartList += document.getElementById("cartNo"+i).value + ",";
		}
	}
	
	if(cartList == ""){
		alert("1개 이상의 상품을 선택해주세요.");
		return;
	}
	
	location.href="/addPurchaseView.do?cartList=" + cartList;
}

function fncSumPrice(){
	var listSize = ${list.size()};
	var sumPrice = 0;

	for(i=1; i<=listSize; i++){
		if(document.getElementById("cartChk"+i).checked){
			sumPrice += parseInt(document.getElementById("price"+i).value);
		}
	}
	
	document.getElementById("sumPrice").value = sumPrice;
}

function fncChkAll(){
	var listSize = ${list.size()};
	
	for(i=1; i<=listSize; i++){
		if(document.getElementById("cartChkAll").checked){
			document.getElementById("cartChk"+i).checked = true;
		}else{
			document.getElementById("cartChk"+i).checked = false;
		}
	}
	
	fncSumPrice();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listCart.do?" method="post">



<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
						장바구니
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="4" >
			전체 ${resultPage.totalCount} 건수
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">
			<input type="checkbox" id="cartChkAll" onclick="fncChkAll()">
		</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품이미지</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">재고수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">구매수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">구매금액</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100"></td>
		<td class="ct_line02"></td>
		</td>	
	</tr>
	<tr>
		<td colspan="16" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0"/>
	<c:forEach var="cart" items="${list }">
	<c:set var="i" value="${i+1 }"/>
	<tr class="ct_list_pop">
		<td align="center">
			<input type=${cart.cartCnt <= cart.cartProd.prodCnt ? "checkbox" : "hidden"} id="cartChk${i}" onclick="fncSumPrice()">
			<input type="hidden" id="cartNo${i}" value="${cart.cartNo}">
		</td>
		<td></td>
			<td align="left"><a href="/getProduct.do?prodNo=${cart.cartProd.prodNo}&menu=search">${cart.cartProd.prodName}</a></td>
		<td></td>
		<td align="center">
			<c:if test="${!empty cart.cartProd.fileName }">
				<img src = "/images/uploadFiles/${cart.cartProd.fileName }" width="150"/>
			</c:if>
		</td>
		<td></td>
		<td align="center">
			${cart.cartProd.prodCnt}
			<input type="hidden" name="prodCnt" id="prodCnt${i}" value="${cart.cartProd.prodCnt }"/>
		</td>
		<td></td>
		<td align="right">
			${cart.cartProd.price} 원
		</td>
		<td></td>
		<td align="center">
			<input type="text" name="cartCnt" id="cartCnt${i}" 	class="ct_input_g" onchange="fncCntChange(${i})" 
							style="width: 50px; height: 15px" maxLength="5" value="${cart.cartCnt}" /><br/><br/>
			<input type="hidden" name="cntChange" id="cntChange${i}" value="수량변경" onclick="fncCntChangeSet(${cart.cartNo}, ${i})"
							style="width: 80px; height: 25px"/>
		</td>
		<td></td>
		<td align="right">
			${cart.cartProd.price * cart.cartCnt} 원
			<input type="hidden" id="price${i}" value="${cart.cartProd.price * cart.cartCnt}"/>
		</td>
		<td></td>
		<td align="center" height="70">
			<c:if test="${cart.cartCnt <= cart.cartProd.prodCnt}">
				<a href="/addPurchaseView.do?prodNo=${cart.cartProd.prodNo}&tranCnt=${cart.cartCnt}">구매</a><br/>
			</c:if>
			<c:if test="${cart.cartCnt > cart.cartProd.prodCnt}">
				재고없음<br/>
			</c:if>
			<a href="/deleteCart.do?cartNo=${cart.cartNo}">삭제</a>
		</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="16" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	</c:forEach>
</table>


<input type="hidden" id="cartList" >

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td >
						총 구매금액 : 
						<input style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align:right" 
								type="text" id="sumPrice" size="10" value="0">
						 원
						<td width="30"></td>
						<td width="17" height="23">
							<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
						</td>
						<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
							<a href="javascript:fncPurchase()">구매하기</a>
						</td>
						<td width="14" height="23">
							<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
						</td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>

</div>
</body>
</html>
