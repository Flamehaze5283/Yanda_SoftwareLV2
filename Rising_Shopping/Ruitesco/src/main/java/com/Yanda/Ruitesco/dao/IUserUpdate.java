package com.Yanda.Ruitesco.dao;

import java.sql.SQLException;

public interface IUserUpdate {
	public boolean UpdatePassword(String passwordNew, String userName) throws SQLException;	//ĞŞ¸ÄÃÜÂë
}
