package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created by Sea on 2/15/17.
 */
public class EditServlet extends HttpServlet{
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
        String username = req.getParameter("username");
        String sql = "select * from USER_INFO WHERE username = '" +username+ "';";
        ResultSet rs = databaseService.select(sql);
        try {
            while (rs.next()){
                String un = rs.getString("username");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                req.setAttribute("username", un);
                req.setAttribute("name", name);
                req.setAttribute("surname", surname);
                req.setAttribute("email", email);
            }
        } catch (Exception e){
            System.out.println("Cannot get from database.");
        }
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/editUser.jsp");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post");
        String un = req.getParameter("username");
        String nameIn = req.getParameter("name");
        String surnameIn = req.getParameter("surname");
        String emailIn = req.getParameter("email");
        String sql = "UPDATE USER_INFO SET username = '" + un + "', name = '" + nameIn + "', surname = '" + surnameIn + "', email = '" + emailIn + "' WHERE username = '" + un + "'";
        databaseService.update(sql);
        resp.sendRedirect("/user");
    }
}
