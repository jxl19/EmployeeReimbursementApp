package com.jun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jun.model.Employee;
import com.jun.model.Reimbursement;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public ArrayList<Reimbursement> getAllReimbursementRequests(int loginId, Connection con) throws SQLException {
		ArrayList<Reimbursement> rList = new ArrayList<>();
//		SELECT * from reimbursement_requests WHERE login_id = 3 ORDER BY request_id DESC LIMIT 7;
		String sql = "SELECT * FROM reimbursement.reimbursement_requests WHERE login_id = ? ORDER BY pending DESC, request_id ASC LIMIT 7";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, loginId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			int reqId = rs.getInt("request_id");
			double amount = rs.getDouble("amount");
			boolean pending = rs.getBoolean("pending");
			boolean approved = rs.getBoolean("approved");
			String reason = rs.getString("reason");
			rList.add(new Reimbursement(reqId, loginId, amount, pending, approved, reason));
		}
		return rList;
	}

	@Override
	public Reimbursement createNewReimbursement(int loginId, double amount, String reason, Connection con)
			throws SQLException {
		Reimbursement reimbursement = null;
		String sql = "INSERT INTO reimbursement.reimbursement_requests (login_id, amount, reason) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, loginId);
		ps.setDouble(2, amount);
		ps.setString(3, reason);
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			int reqId = rs.getInt("request_id");
			double amounts = rs.getDouble("amount");
			boolean pending = rs.getBoolean("pending");
			boolean approved = rs.getBoolean("approved");
			String reasons = rs.getString("reason");
			reimbursement = new Reimbursement(reqId, loginId, amounts, pending, approved, reasons);
		}
		return reimbursement;
	}

	@Override
	public Employee getEmployeeInfo(int loginId, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		Employee employee = null;
		String sql = "SELECT * FROM reimbursement.users JOIN reimbursement.employee_type ON users.login_id = employee_type.login_id JOIN reimbursement.login ON reimbursement.login.login_id = users.login_id WHERE login.login_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, loginId);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			boolean isManager = rs.getBoolean("is_manager");
			int userId = rs.getInt("login_id");
			String fn = rs.getString("first_name");
			String ln = rs.getString("last_name");
			String hireDate = rs.getString("hire_date");
			String birthDate = rs.getString("birth_date");
			String em = rs.getString("email");
			employee = new Employee(isManager, userId, fn, ln, hireDate, birthDate, em);
		}
		System.out.println("employee inside dao " + employee);
		return employee;
	}

}
