package io.muic.ooc.webapp.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 * Created by Sea on 2/14/17.
 */
public class SecurityService {

    public boolean isAuthorized(HttpServletRequest request, DatabaseService databaseService) {
        String username = (String) request.getSession().getAttribute("username");
        String un = "";
        ResultSet rs = databaseService.select("select * from USER_INFO WHERE username = '" +username+ "';");
        try{
            while (rs.next()){
                un = rs.getString("username");
            }
        } catch (Exception e){
            System.out.println("Cannot check in DB. from isAuthorized");
        }
        // do checking
        return username != null && StringUtils.equals(username,un);
    }

    public boolean authenticate(String username, String password, HttpServletRequest request, ResultSet rs) {
        // check in database
        String passinDB;
        boolean isMatched = false;
        try{
            while (rs.next()){
                passinDB = rs.getString("password");
                isMatched = StringUtils.equals(password, passinDB);
            }

        } catch (Exception e){
            System.out.println("Cannot check in db.");
        }
        if(isMatched){
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public String hashPass(String password){
        String hashed = "";
        String salt = "lit@#$%le%$@*)+&";
        hashed = DigestUtils.sha256Hex( password );
        hashed += salt;
        hashed = DigestUtils.sha256Hex(hashed);
        return hashed;
    }

    public void logout(HttpServletRequest request, DatabaseService databaseService){
        request.getSession().invalidate();
    }
}
