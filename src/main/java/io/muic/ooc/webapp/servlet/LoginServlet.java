package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

import static io.muic.ooc.webapp.Webapp.*;

/**
 * Created by Sea on 2/14/17.
 */
public class LoginServlet extends HttpServlet {
    private SecurityService securityService;
    private DatabaseService databaseService;

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void setSecurityManager(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(req, databaseService);
        if(authorized){
            resp.sendRedirect("/user");
        } else {
            String username = req.getParameter("username");
            String password = securityService.hashPass(req.getParameter("password"));
            System.out.println(password);
            ResultSet resultSet = databaseService.select("select * from USER_INFO WHERE username = '" +username+ "' AND password = '"+ password + "';");


            if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
                if(securityService.authenticate(username, password, req, resultSet)){
                    resp.sendRedirect("/");
                } else {
                    String error = "Wrong username or password.";
                    req.setAttribute("error", error);
                    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
                    rd.include(req, resp);
                }
            } else {
                String error = "Username or password is missing.";
                req.setAttribute("error", error);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
                rd.include(req, resp);
            }
        }

    }
}
