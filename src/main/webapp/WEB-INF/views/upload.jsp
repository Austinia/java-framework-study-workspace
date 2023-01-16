<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload!!</title>
</head>
<body>
    <h1>
        File Upload!!!
    </h1>
    <!-- upload 액션으로 post한다 -->
    <form action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" />
    </form>
    <!-- post한 이미지를 보여준다 -->
    <img src="${url}" />
</body>
</html>
