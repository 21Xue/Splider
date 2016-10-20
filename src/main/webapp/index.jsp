<%@ page import="java.net.InetAddress" %>
<html><body>
<br><br><br><br>
<div align="center">
	<a style="color:green">
		<b>Welcome to DPlatform-Google push service...</b><br>
	</a>
	<hr>
	<%--<a style="color:orange">
		<a href="test.htm" style="font:bold">I'm working .</a>
	</a--%>
    <div align="center" style="color:#708090;">
        <a href="settings/task/query.action">management</a>
        <br/><%=InetAddress.getLocalHost().getHostAddress()%>
    </div>
</div>
</body></html>