<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="com.austinia.user.UserDao" %>
<%@ page import="com.austinia.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.austinia.user");
    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
    User user = userDao.get(26);
%>
<html>
<head>
    <title>I'm SERVER!!</title>
</head>
<body>
<h1>
    Hello <%=user.getName()%>!!!
</h1>
</body>
</html>
