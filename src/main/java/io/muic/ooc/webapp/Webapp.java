package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by Sea on 2/14/17.
 */
public class Webapp {

    public static void main(String[] args) {

        final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
        final String MYSQL_URL = "jdbc:mysql://localhost:3306/webapp?" + "user=root&password=littlesea";

        String docBase = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        SecurityService securityService = new SecurityService();
        DatabaseService databaseService = new DatabaseService(MYSQL_DRIVER, MYSQL_URL);
        databaseService.getConnection();

        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);
        servletRouter.setDatabaseService(databaseService);

        ErrorPage errorPage = new ErrorPage();
        errorPage.setErrorCode(HttpServletResponse.SC_NOT_FOUND);
        errorPage.setLocation("/");

        Context ctx;
        try {
            ctx = tomcat.addWebapp("", new File(docBase).getAbsolutePath());
            ctx.addErrorPage(errorPage);
            servletRouter.init(ctx);
            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }

    }

}
