package com.jun.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.jun.model.Employee;

public interface LoginDAO {
	public Employee authenticateUser(String username, String password, Connection con) throws SQLException;
}
