<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<html>
<head>
<title>판매 이력조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncListPage(currentPage) {
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/historyPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">판매 이력조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<input type="hidden" id="prodNo" name="prodNo" value="0"/>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="23">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="50">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="70">주문번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="80">아이디</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="70">이름</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="120">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="250">주소</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="50">수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">구매방법</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">주문일시</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">배송희망일자</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">구매요청사항</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="120">상태</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="80"></td>
	</tr>
	<tr>
		<td colspan="25" bgcolor="808285" height="1"></td>
	</tr>

	
	<c:set var="i" value="0"/>
	<c:forEach var="purchase" items="${list }">
	<c:set var="i" value="${i+1 }"/>
	<tr class="ct_list_pop">
		<td align="center">${i}</td>
		<td></td>
		<td align="center">
			<a href="/getPurchase.do?tranNo=${purchase.tranNo}">${purchase.tranNo}</a>
		</td>
		<td></td>
		<td align="center">${purchase.buyer.userId}</td>
		<td></td>
		<td align="center">${purchase.receiverName}</td>
		<td></td>
		<td align="center">${purchase.receiverPhone}</td>
		<td></td>
		<td align="left">${purchase.divyAddr}</td>
		<td></td>
		<td align="center">${purchase.tranCnt}</td>
		<td></td>
		<td align="center">${purchase.paymentOption.equals('1')?"현금구매":"신용구매"}</td>
		<td></td>
		<td align="center">${purchase.orderDate}</td>
		<td></td>
		<td align="center">${purchase.divyDate}</td>
		<td></td>
		<td align="center">${purchase.divyRequest}</td>
		<td></td>
		<td align="center">
		${purchase.tranCode.equals('1')?"구매완료":purchase.tranCode.equals('2')?"배송중":purchase.tranCode.equals('3')?"배송완료":purchase.tranCode.equals('4')?"구매취소요청":purchase.tranCode.equals('5')?"구매취소":"" }
		 상태
		</td>
		<td></td>
		<td align="center">
			<c:if test="${purchase.tranCode.equals('1') }">
				<a href="/updateTranCode.do?prodNo=${purchase.purchaseProd.prodNo}&tranNo=${purchase.tranNo}&tranCnt=${purchase.tranCnt}&tranCode=2">배송하기</a>
			</c:if>
			<c:if test="${purchase.tranCode.equals('4') }">
				<a href="/updateTranCode.do?prodNo=${purchase.purchaseProd.prodNo}&tranNo=${purchase.tranNo}&tranCnt=${purchase.tranCnt}&tranCode=5">구매취소</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="25" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>