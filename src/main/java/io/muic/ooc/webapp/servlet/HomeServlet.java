package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.muic.ooc.webapp.Webapp.*;

/**
 * Created by Sea on 2/14/17.
 */
public class HomeServlet extends HttpServlet {
    private SecurityService securityService;
    private DatabaseService databaseService;

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void setSecurityManager(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(req, databaseService);
        if (authorized) {
            resp.sendRedirect("/user");
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post");
    }
}
