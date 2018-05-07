<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ page import="java.net.URLEncoder"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetProductList(currentPage){
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();
}
function fncListPage(currentPage){
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("searchCondition").value = "${search.searchCondition}";
	document.getElementById("searchKeyword").value = "${search.searchKeyword}";
	document.detailForm.submit();
}
function fncSort(currentPage, sort){
	document.getElementById("sort").value = sort;
	fncListPage(currentPage);
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${param.menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
						${param.menu.equals('manage')?"판매상품관리":"상품 목록조회"}
					
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
		<td align="right">
			<select id = "searchCondition" name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${! empty search.searchCondition && search.searchCondition.equals("0")?"selected":""}>상품번호</option>
				<option value="1" ${! empty search.searchCondition && search.searchCondition.equals("1")?"selected":""}>상품명</option>
				<option value="2" ${! empty search.searchCondition && search.searchCondition.equals("2")?"selected":""}>상품가격</option>
			</select>
			<input type="text" id="searchKeyword" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" 
					value="${! empty search.searchKeyword ? search.searchKeyword : ''}" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="4" >
			전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지
		</td>
		<td colspan="12" align="right">
			<c:if test="${search.sort.equals('asc')}">
				<img src="/images/ct_icon_red.gif" width="5" height="5" align="middle"/> 
				<a href="javascript:fncSort('1', 'default');">
				<b>낮은 가격 순</b>
				</a>
			</c:if>
			<c:if test="${!search.sort.equals('asc')}">
				<img src="/images/empty.gif" width="5" height="5" align="middle"/> 
				<a href="javascript:fncSort('1', 'asc');">
				낮은 가격 순
				</a>
			</c:if>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${search.sort.equals('dsc')}">
				<img src="/images/ct_icon_red.gif" width="5" height="5" align="middle"/> 
				<a href="javascript:fncSort('1', 'default');">
				<b>높은 가격 순</b>
				</a>
			</c:if>
			<c:if test="${!search.sort.equals('dsc')}">
				<img src="/images/empty.gif" width="5" height="5" align="middle"/> 
				<a href="javascript:fncSort('1', 'dsc');">
				높은 가격 순
				</a>
			</c:if>
		</td>
		<input type="hidden" id="sort" name="sort" value="${search.sort }"/>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품이미지</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">재고수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">
		<c:if test="${param.menu.equals('manage') }">
			판매내역
		</c:if>
		</td>	
	</tr>
	<tr>
		<td colspan="15" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0"/>
	<c:forEach var="product" items="${list }">
	<c:set var="i" value="${i+1 }"/>
	<tr class="ct_list_pop">
		<td align="center">${i}</td>
		<td></td>
				<c:if test="${param.menu.equals('manage') }">
					<td align="left"><a href="/updateProductView.do?prodNo=${product.prodNo}&menu=manage">${product.prodName}</a></td>
				</c:if>
				<c:if test="${param.menu.equals('search') }">
					<td align="left"><a href="/getProduct.do?prodNo=${product.prodNo}&menu=search">${product.prodName}</a></td>
				</c:if>
		<td></td>
		<td align="center">
			<c:if test="${!empty product.fileName }">
				<img src = "/images/uploadFiles/${product.fileName }" width="150"/>
			</c:if>
		</td>
		<td></td>
		<td align="right">${product.price} 원</td>
		<td></td>
		<td align="center">${product.prodCnt}</td>
		<td></td>
		<td align="center">${product.regDate}</td>
		<td></td>
		<td align="center">
			${product.prodCnt != 0?"판매중":"재고없음" }
		</td>	
		<td></td>
		<td align="center">
		<c:if test="${param.menu.equals('manage') }">
			<a href="/historyPurchase.do?prodNo=${product.prodNo}">확인</a>
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="15" bgcolor="D6D7D6" height="1"></td>
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
