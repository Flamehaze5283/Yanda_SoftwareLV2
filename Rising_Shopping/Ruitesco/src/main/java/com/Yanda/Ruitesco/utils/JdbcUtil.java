package com.Yanda.Ruitesco.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;




public class JdbcUtil {
	
	private static DruidDataSource datasource = new DruidDataSource();
	
	static {
		Properties properties=new Properties();
		InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			properties.load(inputStream);
			datasource.setDriverClassName("com.mysql.jdbc.Driver");
		} catch (IOException e) {
			e.printStackTrace();
		}
		datasource.setUrl(properties.getProperty("db_url"));
		datasource.setUsername(properties.getProperty("db_user"));
		datasource.setPassword(properties.getProperty("db_passwd"));
		datasource.setMaxActive(20);
		datasource.setMinIdle(5);
	}

	private static Connection getConnection() {
		Connection con = null;
		try {
			con=datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private static void close(PreparedStatement pstmt,Connection con)
	{
		try {
			if(pstmt!= null )
			{
				pstmt.close();
			}
			if(con != null)
			{
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void close(ResultSet rs,PreparedStatement pstmt,Connection con)
	{
		try {
			if(rs!=null)
			{
				rs.close();
			}
			close(pstmt,con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int executeUpdate(String sql,Object... params)
	{
		int result = 0;
		Connection con =getConnection();
		PreparedStatement pstmt = null;

			try {
				pstmt = con.prepareStatement(sql);
				if(params != null)
				{
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				result =pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt,con);
			}
		return result;
	}
	
	public static <T> List<T> executeQuery(String sql,Class<T> clz,Object... params)
	{
		List<T> list= new ArrayList<>();
		Connection con =getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			if(params != null) {
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				try {
					T t = clz.newInstance();
					Field[] fields = clz.getDeclaredFields();
					for(Field field :fields){
						field.setAccessible(true);
						try {
							field.set(t, rs.getObject(field.getName()));
						}catch(Exception e) {}
					}
					list.add(t);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstmt,con);
		}
		
		if(list.size()==0){
			return null;
		}
		return list;
	}
		
	public static <T> T getById(String table,Class<T> clz,Object id)
	{
		//sql×¢Èë
		List<T> list =executeQuery("select * from "+table+ " where id=?",clz,id);
		if(list ==null){
			return null;
		}
		return list.get(0);
	}
	
	public static int executeQueryInt(String sql,Object... params)
	{
		int result=0;
		Connection con =getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				pstmt.setObject(i+1, params[i]);
			}
			rs=pstmt.executeQuery();
			rs.next();
			result=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			close(rs,pstmt,con);
		}
		return result;
	}
}
