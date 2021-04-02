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
	public ArrayList<Reimbursement> getAllReimbursements(Connection con) throws SQLException {
		ArrayList<Reimbursement> rList = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement.reimbursement_requests";
		PreparedStatement ps = con.prepareStatement(sql);
		
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
	public ArrayList<Reimbursement> getAllPendingReimbursements(Connection con) throws SQLException {
		ArrayList<Reimbursement> rList = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement.reimbursement_requests WHERE pending = true";
		PreparedStatement ps = con.prepareStatement(sql);
		
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
	public ArrayList<Employee> getAllEmployees(Connection con) throws SQLException {
		ArrayList<Employee> employee = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement.users JOIN reimbursement.employee_type ON users.login_id = employee_type.login_id JOIN reimbursement.login ON reimbursement.login.login_id = users.login_id WHERE is_manager = false";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			boolean isManager = rs.getBoolean("is_manager");
			int userId = rs.getInt("login_id");
			String fn = rs.getString("first_name");
			String ln = rs.getString("last_name");
			String hireDate = rs.getString("hire_date");
			String birthDate = rs.getString("birth_date");
			String em = rs.getString("email");
			employee.add(new Employee(isManager, userId, fn, ln, hireDate, birthDate, em));
		}
		return employee;
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

}



