<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${resultPage.beginUnitPage>1 }">
	<a href="javascript:fncListPage('${resultPage.beginUnitPage-1}');">◀이전</a>
</c:if>

<c:forEach var="i" begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}">
	<c:if test="${i==resultPage.currentPage }">
		<a href="javascript:fncListPage('${i}');"><b>${i}</b></a>
	</c:if>
	<c:if test="${i!=resultPage.currentPage }">
		<a href="javascript:fncListPage('${i}');">${i}</a>
	</c:if>
</c:forEach>

<c:if test="${resultPage.endUnitPage<resultPage.maxPage }">
	<a href="javascript:fncListPage('${resultPage.endUnitPage+1}');">다음▶</a>
</c:if>