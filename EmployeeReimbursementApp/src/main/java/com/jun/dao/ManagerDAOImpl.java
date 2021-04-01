package com.jun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jun.model.Employee;
import com.jun.model.Reimbursement;
import com.jun.model.UpdateRequests;

public class ManagerDAOImpl implements ManagerDAO {

	@Override
	public ArrayList<Reimbursement> getAllPendingReimbursements(Connection con) throws SQLException {
		ArrayList<Reimbursement> rList = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement.reimbursement_requests WHERE pending = true";
		PreparedStatement ps = con.prepareStatement(sql);
		
//		int rId, int userId, double amount, boolean pending, boolean approved, String reason
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int rId = rs.getInt("request_id");
			int userId = rs.getInt("login_id");
			double amount = rs.getDouble("amount");
			boolean pending = rs.getBoolean("pending");
			boolean approved = rs.getBoolean("approved");
			String reason = rs.getString("reason");
			
			rList.add(new Reimbursement(rId, userId, amount, pending, approved, reason));
		}
		
		return rList;
	}

	@Override
	public ArrayList<Reimbursement> getAllCompletedReimbursements(Connection con) throws SQLException {
		ArrayList<Reimbursement> rList = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement.reimbursement_requests WHERE pending = false";
		PreparedStatement ps = con.prepareStatement(sql);
		
//		int rId, int userId, double amount, boolean pending, boolean approved, String reason
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int rId = rs.getInt("request_id");
			int userId = rs.getInt("login_id");
			double amount = rs.getDouble("amount");
			boolean pending = rs.getBoolean("pending");
			boolean approved = rs.getBoolean("approved");
			String reason = rs.getString("reason");
			
			rList.add(new Reimbursement(rId, userId, amount, pending, approved, reason));
		}
		
		return rList;
	}

	@Override
	public ArrayList<Reimbursement> getEmployeeReimbursement(int loginId, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateRequests reviewReimbursement(boolean approved, int rId, Connection con) throws SQLException {
		UpdateRequests ur = null;
		String sql = "UPDATE reimbursement.reimbursement_requests SET pending = false, approved = ? WHERE request_id = ?";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setBoolean(1, approved);
		ps.setInt(2, rId);
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			boolean approve = rs.getBoolean("approved");
			int id = rs.getInt("request_id");
			ur = new UpdateRequests(id, approve);
		}
		return ur;
	}

	@Override
	public Employee updateEmployee(String loginId, String firstName, String lastName ,String email, String password, Connection con)
			throws SQLException {
		con.setAutoCommit(false);
		Employee employee = null;
		int id = Integer.parseInt(loginId);
		if(firstName != "null") { 
			System.out.println("in here fn " + firstName);
			String fnsql = "UPDATE reimbursement.users SET first_name = ? WHERE login_id = ?";
			PreparedStatement fnps = con.prepareStatement(fnsql);
			fnps.setString(1, firstName);
			fnps.setInt(2, id);
			fnps.executeUpdate();
		}
		
		if(lastName != "null") { 
			String lnsql = "UPDATE reimbursement.users SET last_name = ? WHERE login_id = ?";
			PreparedStatement lnps = con.prepareStatement(lnsql);
			lnps.setString(1, lastName);
			lnps.setInt(2, id);
			lnps.executeUpdate();
		}
		
		if(email != "null") { 
			String emsql = "UPDATE reimbursement.users SET email = ? WHERE login_id = ?";
			PreparedStatement emps = con.prepareStatement(emsql);
			emps.setString(1, email);
			emps.setInt(2, id);
			emps.executeUpdate();
		}
		
		if(password != "null") { 
			String pwsql = "UPDATE reimbursement.login SET pass = ? WHERE login_id = ?";
			PreparedStatement pwps = con.prepareStatement(pwsql);
			pwps.setString(1, password);
			pwps.setInt(2, id);	
			pwps.executeUpdate();
			System.out.println("updated pw also");
		}
		con.commit();
		String sql = "SELECT * FROM reimbursement.users JOIN reimbursement.employee_type ON users.login_id = employee_type.login_id JOIN reimbursement.login ON reimbursement.login.login_id = users.login_id WHERE login.login_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			boolean isManager = rs.getBoolean("is_manager");
			int userId = rs.getInt("login_id");
			String fn = rs.getString("first_name");
			String ln = rs.getString("last_name");
			String hireDate = rs.getString("hire_date");
			String birthDate = rs.getString("birth_date");
			String em = rs.getString("email");
			employee = new Employee(isManager, userId, fn, ln, hireDate, birthDate, em);
		}
		System.out.println("employee: " + employee);
		return employee;
	}

}



