package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import static io.muic.ooc.webapp.Webapp.*;

/**
 * Created by Sea on 2/14/17.
 */
public class RegisterServlet extends HttpServlet {
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
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/register.jsp");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String un = req.getParameter("username");
        String pw = securityService.hashPass(req.getParameter("password"));
        String nameIn = req.getParameter("name");
        String surnameIn = req.getParameter("surname");
        String emailIn = req.getParameter("email");

        try {
            String sql = " insert into USER_INFO (username, password, name, surname, email)"
                    + " values ('" + un + "', '" + pw + "', '" + nameIn + "', '" + surnameIn + "', '" + emailIn + "')";
            databaseService.insert(sql);
            resp.sendRedirect("/user");
        } catch (Exception e){
            String error = "This username is already used.";
            req.setAttribute("error", error);
//            ReqestDispatcher rd = req.getRequestDispatcher("WEB-INF/register.jsp");
//            rd.include(req, resp);
            doGet(req, resp);
        }
    }
}
