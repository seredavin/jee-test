<%--
  Created by IntelliJ IDEA.
  User: yoght
  Date: 01.12.2020
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>
<h1>Upload</h1>
<form method="post" action="/jee-test/upload" enctype="multipart/form-data">
    Select xml:
    <input type="file" name="file" />
    <br/><br/>
    <input type="submit" value="Upload" />
</form>
<p>${message}</p>
</body>
</html>
