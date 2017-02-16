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

/**
 * Created by Sea on 2/15/17.
 */
public class DeleteServlet extends HttpServlet {
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
            String un = req.getParameter("username");
            req.setAttribute("username",un);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/confirmDelete.jsp");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(StringUtils.equals(req.getParameter("confirm"), "yes")){
            String username = req.getParameter("username");
            String sql = "DELETE FROM USER_INFO WHERE username = '" + username + "'";
            databaseService.delete(sql);
        }

        resp.sendRedirect("/user");
    }
}
