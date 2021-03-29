package com.jun.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jun.model.Reimbursement;
import com.jun.model.UpdateRequests;

public interface ManagerDAO {
	public ArrayList<Reimbursement> getAllPendingReimbursements(Connection con) throws SQLException;
	public ArrayList<Reimbursement> getAllCompletedReimbursements(Connection con) throws SQLException;
	public ArrayList<Reimbursement> getEmployeeReimbursement(int loginId, Connection con) throws SQLException;
	public UpdateRequests reviewReimbursement(boolean approved, int rId, Connection con) throws SQLException;
}
