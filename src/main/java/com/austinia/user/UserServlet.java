package com.austinia.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserServlet extends GenericServlet {
    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        System.out.println("**************** init ****************");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.austinia.user");
        this.userDao = applicationContext.getBean("userDao", UserDao.class);
        super.init();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("**************** Service ****************");
        User user = userDao.get(26);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>");
        stringBuffer.append("<h1>");
        stringBuffer.append(String.format("Hello %s!!", user.getName()));
        stringBuffer.append("</h1>");
        stringBuffer.append("</html>");
        res.setContentType("text/html;charset=UTF-8");
        res.getWriter().println(stringBuffer);
    }

    @Override
    public void destroy() {
        System.out.println("**************** destroy ****************");
        super.destroy();
    }
}
