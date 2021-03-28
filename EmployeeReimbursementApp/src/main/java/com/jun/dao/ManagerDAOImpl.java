package com.jun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jun.model.Reimbursement;

public class ManagerDAOImpl implements ManagerDAO {

	@Override
	public ArrayList<Reimbursement> getAllPendingReimbursements(Connection con) throws SQLException {
		ArrayList<Reimbursement> rList = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement.reimbursement_requests";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getEmployeeReimbursement(int loginId, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
