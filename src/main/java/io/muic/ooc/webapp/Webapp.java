package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Created by Sea on 2/14/17.
 */
public class Webapp {

    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/webapp?" + "user=root&password=littlesea";

    public static void main(String[] args) {

        String docBase = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);
        SecurityService securityService = new SecurityService();
        DatabaseService databaseService = new DatabaseService(MYSQL_DRIVER, MYSQL_URL);
        databaseService.getConnection();

        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);
        servletRouter.setDatabaseService(databaseService);

        Context ctx;
        try {
            ctx = tomcat.addWebapp("", new File(docBase).getAbsolutePath());
            servletRouter.init(ctx);
            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }

    }

}
