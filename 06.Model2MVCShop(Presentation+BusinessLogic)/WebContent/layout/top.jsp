<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

</head>

<body topmargin="0" leftmargin="0">
 
<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="800" height="30"><h2>Model2 MVC Shop</h2></td>
    <td height="30" >&nbsp;</td>
  </tr>
  <tr>
    <td height="20" align="right" background="/images/img_bg.gif">
	    <table width="200" border="0" cellspacing="0" cellpadding="0">
	        <tr> 
	          <td>
	          <c:if test="${empty user}">
	          	<a href="/user/loginView.jsp" target="rightFrame">login</a>  
	          </c:if>    
	          </td>
	          <td width="14">&nbsp;</td>
	          <td>
	          <c:if test="${!empty user}">
	          	${user.userId} 님 환영합니다. <a href="/logout.do" target="_parent">logout</a>
	          </c:if>    
	          </td>
	        </tr>
	    </table>
    </td>
    <td height="20" background="/images/img_bg.gif">&nbsp;</td>
  </tr>
</table>

</body>
</html>
