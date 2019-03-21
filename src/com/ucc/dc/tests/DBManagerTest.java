package com.ucc.dc.tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.ucc.dc.dao.DBManager;
/**
 * Test to DB connection
 * 
 * by
 * Rohit Badgujar
 */
public class DBManagerTest {

	@Test
	public void testGetConnection() {
		DBManager dbManager = new DBManager();
		Connection connection = dbManager.getConnection();
		assertTrue(connection != null);
	}

}
