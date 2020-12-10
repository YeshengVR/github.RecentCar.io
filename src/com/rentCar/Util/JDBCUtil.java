package com.rentCar.Util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.entry.User;


public class JDBCUtil {
	private static String driver; // 驱动名
	private static String url;
	private static String user;
	private static String password;
	private Connection conn = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;
	// 加载驱动等事前准备工作
	static {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
			driver = bundle.getString("driver");
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 获取驱动连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 返回查询到的数据集合以ArrayList集合传出。
	 */
	public static <T> ArrayList<T> Select(ResultSet rs, Class<T> clazz) {
		ArrayList<T> arr = new ArrayList<T>();
		Field[] fields = clazz.getDeclaredFields();
		try {
			// 指向查询的结果集的每一行属性
			while (rs.next()) {
				// 创建一个对象接受属性信息
				T nowUser = clazz.newInstance();
				for (Field field : fields) {
					field.setAccessible(true);// 可读取私有属性
					// getObject()：获取当前行的指定列的值。field.getName()获取当前属性的指定列名称
					// 将每个字段的值传入nowUser对象中
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						String columnName = metaData.getColumnName(i);
						if (columnName.toLowerCase().equals(field.getName().toLowerCase())) {
							field.set(nowUser, rs.getObject(field.getName()));
						}
					}
				}
				// 在集合中添加nowUser对象。
				arr.add(nowUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return arr;
	}

	/**
	 * 根据需求查询汽车信息
	 * 
	 * @param sql
	 *            需求的sql语句
	 * @return 返回一个需要查询到的汽车信息
	 */
	public ArrayList<Car> selectCar(String sql) {
		ArrayList<Car> arr = new ArrayList<Car>();
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			arr = JDBCUtil.Select(rs, Car.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return arr;
	}

	/**
	 * 根据需求修改/添加信息记录
	 * 
	 * @param sql
	 *            需求的sql语句
	 * @return 返回一个是否修改成功的信息
	 */
	public boolean UpdateNothing(String sql) {
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return true;
	}

	/**
	 * 根据需求查询租赁记录
	 * 
	 * @param sql
	 *            查询筛选租赁记录的sql语句
	 * @return 返回一个需求的的租赁记录
	 */
	public ArrayList<Record> selectRecord(String sql) {
		ArrayList<Record> arr = new ArrayList<Record>();
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			arr = JDBCUtil.Select(rs, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return arr;
	}

	/**
	 * 查找用户信息的方法
	 * 
	 * @param sql
	 *            通过sql语句来指定获取谁的信息或者哪一批的信息
	 * @return 返回一个装载了指定查找的所有用户的集合
	 */
	public ArrayList<User> selectUser(String sql) {
		ArrayList<User> arr = new ArrayList<User>();
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			arr = JDBCUtil.Select(rs, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return arr;
	}
	// 关闭资源
	public static void close(ResultSet rs, Statement state, Connection conn) {
		// 先try再if的原因是如果一开始就if的话直接判断为false的话
		// 就会直接跳出语句，不执行接下来的步骤，所以一定要先try再用if判断是否为空，决定要不要关闭
		// 最后嵌套的关闭资源必须放在finally语句块里面，因为不管程序报不报错，这句话都会执行
		// 保证了第一个资源报错也不会影响之后的资源关闭。
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {

				}
			}
		}
	}

}
