package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.servlet.*;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by Sea on 2/14/17.
 */
public class ServletRouter {
    private SecurityService securityService;
    private DatabaseService databaseService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void init(Context ctx) {
        initHome(ctx);
        initLogin(ctx);
        initRegister(ctx);
        initUser(ctx);
        initEdit(ctx);
        initDelete(ctx);
    }

    private void initHome(Context ctx) {
        HomeServlet homeServlet = new HomeServlet();
        homeServlet.setSecurityManager(securityService);
        homeServlet.setDatabaseService(databaseService);
        Tomcat.addServlet(ctx, "HomeServlet", homeServlet);
        ctx.addServletMapping("/index.jsp", "HomeServlet");
    }

    private void initLogin(Context ctx) {
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityManager(securityService);
        loginServlet.setDatabaseService(databaseService);
        Tomcat.addServlet(ctx, "LoginServlet", loginServlet);
        ctx.addServletMapping("/login", "LoginServlet");
    }

    private void initRegister(Context ctx) {
        RegisterServlet registerServlet = new RegisterServlet();
        registerServlet.setSecurityManager(securityService);
        registerServlet.setDatabaseService(databaseService);
        Tomcat.addServlet(ctx, "RegisterServlet", registerServlet);
        ctx.addServletMapping("/register", "RegisterServlet");
    }

    private void initUser(Context ctx) {
        UserServlet userServlet = new UserServlet();
        userServlet.setSecurityManager(securityService);
        userServlet.setDatabaseService(databaseService);
        Tomcat.addServlet(ctx, "UserServlet", userServlet);
        ctx.addServletMapping("/user", "UserServlet");
    }

    private void initEdit(Context ctx) {
        EditServlet editServlet = new EditServlet();
        editServlet.setSecurityManager(securityService);
        editServlet.setDatabaseService(databaseService);
        Tomcat.addServlet(ctx, "EditServlet", editServlet);
        ctx.addServletMapping("/user/edit", "EditServlet");
    }

    private void initDelete(Context ctx){
        DeleteServlet deleteServlet = new DeleteServlet();
        deleteServlet.setSecurityManager(securityService);
        deleteServlet.setDatabaseService(databaseService);
        Tomcat.addServlet(ctx, "DeleteServlet", deleteServlet);
        ctx.addServletMapping("/user/delete", "DeleteServlet");
    }
}
