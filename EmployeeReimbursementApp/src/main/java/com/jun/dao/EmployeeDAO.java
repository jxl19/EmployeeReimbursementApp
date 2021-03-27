package com.jun.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jun.model.Reimbursement;

public interface EmployeeDAO {
	public ArrayList<Reimbursement> getAllReimbursementRequests(int loginId, Connection con) throws SQLException;
}
