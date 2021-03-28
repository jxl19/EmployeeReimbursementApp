package com.jun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jun.model.Reimbursement;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public ArrayList<Reimbursement> getAllReimbursementRequests(int loginId, Connection con) throws SQLException {
		ArrayList<Reimbursement> rList = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement.reimbursement_requests WHERE login_id = ?";
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
		System.out.println("login: " + loginId);
		System.out.println(amount);
		System.out.println(reason);
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

}
