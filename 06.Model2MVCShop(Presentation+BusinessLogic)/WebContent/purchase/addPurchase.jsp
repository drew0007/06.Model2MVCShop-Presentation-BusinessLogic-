<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
</head>

<body>

<form name="addPurchase" method="post">

다음과 같이 구매가 되었습니다.

<table width="1000" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;" align="center">
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품이미지</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">구매수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">구매금액</td>
		</td>	
	</tr>
	<tr>
		<td colspan="9" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="sum" value="0"/>
	<c:set var="i" value="0"/>
	<c:forEach var="purchase" items="${list}">
	<c:set var="i" value="${i+1 }"/>
	<c:set var="sum" value="${sum + purchase.purchaseProd.price * purchase.tranCnt}"/>
	<tr class="ct_list_pop">
		<td align="center">${i}
		</td>
		<td></td>
		<td align="left"><a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo}&menu=search">${purchase.purchaseProd.prodName}</a></td>
		<td></td>
		<td align="center">
			<c:if test="${!empty purchase.purchaseProd.fileName }">
				<img src = "/images/uploadFiles/${purchase.purchaseProd.fileName }" width="150"/>
			</c:if>
		</td>
		<td></td>
		<td align="center">
			${purchase.tranCnt}
		</td>
		<td></td>
		<td align="right">${purchase.purchaseProd.price * purchase.tranCnt} 원</td>
	</tr>
	<tr>
		<td colspan="9" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	</c:forEach>
</table>
<br/>

<table width="600" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			구매자아이디 <img 	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${user.userId}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매방법</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			${list[0].paymentOption.equals('1')?"현금구매":"신용구매" }
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자이름</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			${list[0].receiverName}
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자연락처</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			${list[0].receiverPhone}
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			${list[0].divyAddr}
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매요청사항</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			${list[0].divyRequest}
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">배송희망일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td width="200" class="ct_write01">
			${list[0].divyDate}
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			총 주문금액
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01" >${sum} 원</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>
</form>

</body>
</html>