package com.yiibai.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.yiibai.customer.dao.CustomerDAO;
import com.yiibai.customer.model.Customer;

//这个接口被实现了，但是没有直接通过new一个对象来调用这个类，
//而是通过用bean调用CustomerDAO接口来使用这个接口实现类

public class JdbcCustomerDAO implements CustomerDAO {
	
	private DataSource dataSource;//这里由Spring-Datasource.xml里的beans来设置，这里是操控数据库的关键所在
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Customer customer){
		
		String sql = "INSERT INTO CUSTOMER " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();//dataSource由上面的beans文件配置好了这里就能链接数据库
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getCustId());
			ps.setString(2, customer.getName());
			ps.setInt(3, customer.getAge());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public Customer findByCustomerId(int custId){
		
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
					rs.getInt("CUST_ID"),
					rs.getString("NAME"), 
					rs.getInt("Age")
				);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}