package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.User;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sea on 2/14/17.
 */
public class UserServlet extends HttpServlet {
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
            ResultSet rs = databaseService.select("select * from USER_INFO;");
            List<User> userList = new ArrayList<>();
            String curUser = (String) req.getSession().getAttribute("username");
            req.setAttribute("curUser", curUser);
            try {
                while (rs.next()) {
                    User user = new User();
                    String username = rs.getString("username");
                    user.setUsername(username);
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setEmail(rs.getString("email"));
                    userList.add(user);
                    if(StringUtils.equals(curUser,username)){
                        req.setAttribute("curUserName", rs.getString("name"));
                        req.setAttribute("curUserSurname", rs.getString("surname"));
                    }
                }
            } catch (Exception e){
                System.out.println("Cannot get from DB.");
            }
            req.setAttribute("userList", userList);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/user.jsp");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("button").equals("Add User")){
            resp.sendRedirect("/register");
        } else {
            securityService.logout(req, databaseService);
            resp.sendRedirect("/login");
        }
    }
}
