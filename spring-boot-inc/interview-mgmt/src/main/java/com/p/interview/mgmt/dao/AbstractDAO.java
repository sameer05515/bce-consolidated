/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.p.interview.mgmt.util.ConnectionManager;

/**
 * 
 * @author PREMENDRA
 */
public abstract class AbstractDAO {

	protected Connection getConnection() {
		Connection con = ConnectionManager.getInstance().getConnection();
		return con;
	}

	protected void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
		}
	}

}
