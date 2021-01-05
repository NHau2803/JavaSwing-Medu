/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.ConnectInterface;
import common.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HAU28
 */
public class ConnectJDBC implements ConnectInterface {
    @Override
    public Connection getConnection(String nameDB) {
        try {
            return DriverManager.getConnection(Constant.urlDB + nameDB, Constant.userDB, Constant.passDB);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void closeConnection(Statement stm, Connection con) {
        try {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
