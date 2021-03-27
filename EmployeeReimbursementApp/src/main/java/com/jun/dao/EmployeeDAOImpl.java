package com.jun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			rList.add(new Reimbursement(reqId, amount, pending, approved, reason));
		}
		System.out.println("inside dao: " + rList);
		return rList;
	}

}
