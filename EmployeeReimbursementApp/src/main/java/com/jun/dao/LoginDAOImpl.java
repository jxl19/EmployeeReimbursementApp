package com.jun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jun.model.Login;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public Login authenticateUser(String username, String password, Connection con) throws SQLException {
		Login login = null;
		
		String sql = "SELECT * FROM reimbursement.users JOIN reimbursement.employee_type ON reimbursement.users.login_id = reimbursement.employee_type.login_id JOIN reimbursement.login ON reimbursement.login.login_id = reimbursement.users.login_id WHERE username = ? AND pass = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			boolean isManager = rs.getBoolean("is_manager");
			int loginId = rs.getInt("login_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			login = new Login(isManager, loginId, firstName, lastName);
		}
		
		return login;
	}

}
