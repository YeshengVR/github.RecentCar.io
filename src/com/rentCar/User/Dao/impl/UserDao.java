package com.rentCar.User.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.Dao.IUser;
import com.rentCar.User.entry.User;
import com.rentCar.Util.DataUtil;
import com.rentCar.Util.JDBCUtil;

public class UserDao implements IUser {

	private Connection conn = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;

	/**
	 * 用户的登录方法
	 * 
	 * @param id
	 *            用户的id账号
	 * @param password
	 *            用户的账号密码
	 * @return 返回登陆的对象
	 */
	@Override
	public User login(String username, String password) {
		String sql = "Select * from T_User where " + "username = ?";
		User user = null;
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			state.setString(1, username);
			rs = state.executeQuery();
			ArrayList<User> select = JDBCUtil.Select(rs, User.class);
			if (select.size() != 0) {
				user = select.get(0);
				if (DataUtil.login(user.getPassword(), password)) {
					return user;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("登陆失败");
		}
		return user;
	}

	/**
	 * 判断用户名是否重复
	 * 
	 * @param flags
	 *            传递一个参数表示是否符合规范要求
	 * @return 返回用户名UserName
	 */
	public ArrayList<String> uqUserName(String sql) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			ArrayList<User> select = JDBCUtil.Select(rs, User.class);
			for (User user : select) {
				arr.add(user.getUsername());
			}
		} catch (SQLException e) {
			System.out.println("⚠警告用户名不唯一");
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return arr;
	}

	/**
	 * 取出第一个字段的集合
	 * 
	 * @param sql
	 *            判断的字段
	 * @return 返回一个字段的集合
	 */
	public <T> ArrayList<String> uqexist(String sql) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			while (rs.next()) {
				arr.add(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("⚠警告没有找到元素");
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return arr;
	}

	/**
	 * 用户注册的方法
	 * 
	 * @return 返回一个判断用户是否注册成功的true/false
	 */
	@Override
	public boolean register(User user) {
		String sql = "insert into T_User(id,username,password,sex,id_number,tel,addr,type) values(T_USER_ID_SEQ.nextval"
				+ ",?,?,?,?,?,?,?)";
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			state.setString(1, user.getUsername());
			state.setString(2, user.getPassword());
			state.setInt(3, user.getSex().intValue());
			state.setString(4, user.getId_number());
			state.setString(5, user.getTel());
			state.setString(6, user.getAddr());
			state.setInt(7, user.getType().intValue());
			state.executeUpdate();
		} catch (Exception e) {
			System.out.println("⚠警告未注册成功");
			return false;
		} finally {
			JDBCUtil.close(null, state, conn);
		}
		return true;
	}

	/**
	 * 用户查看所有汽车的方法，只显示可以使用的汽车，汽车是坏的的话就不显示
	 * 
	 * @return 返回一个查找到的全部的汽车的集合
	 */

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Car> selectCar(String sql) {
		ArrayList<Car> arr = new ArrayList<Car>();
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			arr = JDBCUtil.Select(rs, Car.class);
		} catch (Exception e) {
			System.out.println("⚠警告未查询到车");
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return arr;
	}

	/**
	 * 用户租车的方法
	 * 
	 * @param user
	 *            传递一个要租车的用户信息
	 * @param sql
	 *            传递一个要使用的车的id信息
	 * @return 返回一个租车是否成功的布尔值
	 */
	@Override
	public boolean RCarUpcar(User user, String carNumber, int useable) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update T_car set useable = " + useable + " where CAR_NUMBER = ?";
			state = conn.prepareStatement(sql);
			state.setString(1, carNumber);
			state.executeUpdate();
		} catch (SQLException e) {
			System.out.println("⚠警告车表未修改成功");
			return false;
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return true;
	}

	public boolean rentCarUpRecord(User user, String carNumber, String sql) {
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			state.executeUpdate();
		} catch (SQLException e) {
			System.out.println("⚠警告租赁表未修改成功");
			return false;
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return true;
	}

	/**
	 * 在数据库进行处理了事务，但是还没结束事务的话，java这边进行操作数据库时会卡死
	 * 
	 * 
	 * @param carNumber
	 *            传递车牌号
	 * @return 返回一个车的数据库id
	 */
	public Number Rentselectcarid(String carNumber) {
		Number index = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from T_car where CAR_NUMBER = ?";
			state = conn.prepareStatement(sql);
			state.setString(1, carNumber);
			rs = state.executeQuery();
			ArrayList<Car> Cars = JDBCUtil.Select(rs, Car.class);
			if (Cars.size() != 0) {
				Car car = Cars.get(0);
				index = car.getId();
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println("⚠警告！未查询到车牌号");
		}
		return index;
	}

	/**
	 * @param user
	 *            传递用户
	 * @return 返回一个用户的数据库id
	 */
	public Number Rselectid(User user) {
		Number index = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from T_user where username = ?";
			state = conn.prepareStatement(sql);
			state.setString(1, user.getUsername());
			rs = state.executeQuery();
			ArrayList<User> users = JDBCUtil.Select(rs, User.class);
			User user3 = users.get(0);
			index = user3.getId();
		} catch (SQLException e) {
			System.out.println("⚠警告未查询到Username！");
		}
		return index;
	}

	/**
	 * 查看本用户的所有租赁记录
	 * 
	 * @param user
	 *            传递本User对象的用户信息
	 * @return 返回一个本用户的所有租赁信息的集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Record> selectRecord(String sql) {
		ArrayList<Record> arr = new ArrayList<Record>();
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			arr = JDBCUtil.Select(rs, Record.class);
		} catch (Exception e) {
			System.out.println("⚠警告未查询到");
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return arr;
	}

	/**
	 * 查找全部用户名数据
	 * 
	 * @return 返回一个全部用户民的集合
	 */
	public ArrayList<String> uqUserName() {
		String sql = "select username from T_User";
		ArrayList<String> uqUserName = uqUserName(sql);
		return uqUserName;
	}

	/**
	 * 查询汽车的sql调用方法
	 * 
	 * @return
	 */
	public ArrayList<Car> selectCarsql() {
		String sql = "select T_car.car_number car_number,T_brand.Name brand_name,T_car.model,T_car.color,t_Category.Name category_name,T_car.t_comments T_Comments,T_car.price Price,T_car.rent Rent "
				+ "from T_car, t_Category, T_brand "
				+ "where T_car.brand_id = T_brand.id and T_car.Category_Id = t_Category.Id and status = 0 and useable = 0";
		ArrayList<Car> selectCar = selectCar(sql);
		return selectCar;
	}

	/**
	 * 用户根据价格升序来查询汽车的方法 sql
	 * 
	 * @return 返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByTpascsql() {
		String sql = "select T_car.car_number car_number,T_brand.Name brand_name,T_car.model,T_car.color,t_Category.Name category_name,T_car.t_comments T_Comments,T_car.price Price,T_car.rent Rent from T_car, t_Category, T_brand where T_car.brand_id = T_brand.id and T_car.Category_Id = t_Category.Id and status = 0 and useable = 0 order by Rent asc";
		ArrayList<Car> arrayList = selectCar(sql);
		return arrayList;
	}

	/**
	 * 用户根据价格降序来查询汽车的方法
	 * 
	 * @return 返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByTpdescsql() {
		String sql = "select T_car.car_number car_number,T_brand.Name brand_name,T_car.model,T_car.color,t_Category.Name category_name,T_car.t_comments T_Comments,T_car.price Price,T_car.rent Rent from T_car, t_Category, T_brand where T_car.brand_id = T_brand.id and T_car.Category_Id = t_Category.Id and status = 0 and useable = 0 order by Rent desc";
		ArrayList<Car> arrayList = selectCar(sql);
		return arrayList;
	}

	/**
	 * 用户根据品牌来查询汽车的方法
	 * 
	 * @param brand
	 *            传递一个需求的品牌
	 * @return 返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByTbrandsql(String brand) {
		String sql = "select T_car.car_number car_number,T_brand.Name brand_name,T_car.model,T_car.color,t_Category.Name category_name,T_car.t_comments T_Comments,T_car.price Price,T_car.rent Rent from T_car, t_Category, T_brand where T_car.brand_id = T_brand.id and T_car.Category_Id = t_Category.Id and status = 0 and useable = 0 and T_brand.Name = '"
				+ brand + "'";
		ArrayList<Car> arrayList = selectCar(sql);
		return arrayList;
	}

	/**
	 * 搜索对应的车品牌是否存在
	 * 
	 * @param brand
	 *            车品牌
	 * @return 返回一个集合
	 */
	public ArrayList<String> selectCarByBrandWhereExistsql() {
		String sql0 = "select name from T_brand";
		ArrayList<String> uqexist = uqexist(sql0);
		return uqexist;
	}

	/**
	 * 用户根据类别来查询汽车的方法
	 * 
	 * @param sql
	 *            传递一个需求的sql语句
	 * @return 返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByT_Categorysql(String category) {
		String sql = "select T_car.car_number car_number,T_brand.Name brand_name,T_car.model,T_car.color,t_Category.Name category_name,T_car.t_comments T_Comments,T_car.price Price,T_car.rent Rent from T_car, t_Category, T_brand where T_car.brand_id = T_brand.id and T_car.Category_Id = t_Category.Id and status = 0 and useable = 0 and t_Category.Name = '"
				+ category + "'";
		ArrayList<Car> arrayList = selectCar(sql);
		return arrayList;
	}

	/**
	 * 装载查车的sql语句
	 * 
	 * @param user
	 *            登录的用户
	 * @param carNumber
	 *            要租车牌号
	 * @param rentselectid
	 *            要租的用户所对应的id
	 * @param rentselectcarid
	 *            要租的汽车所对应的id
	 * @return 是否成功的布尔值
	 */
	public boolean rentCarsql(User user, String carNumber, Number rentselectid, Number rentselectcarid) {
		String sql = "insert into T_record (id,user_id,car_id,start_date) values(T_RECORD_ID_SEQ.nextval,"
				+ rentselectid + "," + rentselectcarid + ",sysdate)";
		boolean selectUes = selectCarUseable(carNumber);
		if(!selectUes){
			return false;
		}
		boolean rentCarUpcar = RCarUpcar(user, carNumber, 1);
		boolean rentCarUprecord = rentCarUpRecord(user, carNumber, sql);
		if (rentCarUpcar && rentCarUprecord) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 如果车辆被使用则不能租赁
	 * @param carNumber	车牌号
	 * @return			true为：没被使用，false为已被使用。
	 */
	public boolean selectCarUseable(String carNumber) {
		String sql = "select useable from T_car where CAR_NUMBER = '" + carNumber+"'";
		ArrayList<String> uqexist = uqexist(sql);
		if (uqexist == null || uqexist.size() == 0) {
			return false;
		}
		String string = uqexist.get(0);
		if (string == null) {
			return false;
		}
		if (string.equals("1")) {
			return false;
		}
		return true;
	}

	/**
	 * 装载查车的sql语句
	 * 
	 * @param user
	 *            登录的用户
	 * @param carNumber
	 *            要租车牌号
	 * @param rentselectid
	 *            要租的用户所对应的id
	 * @param rentselectcarid
	 *            要租的汽车所对应的id
	 * @return 是否成功的布尔值
	 */
	public boolean returnsql(User user, String carNumber, Number rentselectid, Number rentselectcarid) {
		String sql = "update T_record set return_date = sysdate,payment = (select((select Rent from T_car where id = "
				+ rentselectcarid + ") *(select ceil(sysdate - START_DATE) from T_record where user_id = "
				+ rentselectid + " and car_id = " + rentselectcarid
				+ " and return_date is null)) from dual) where User_id = " + rentselectid + " and car_id = "
				+ rentselectcarid + " and return_date is null";
		if (!IFUserHavrecord(user, rentselectid)) {
			return false;
		}
		if (!IFCarHavrecord(rentselectcarid)) {
			return false;
		}
		boolean rentCarUpcar = RCarUpcar(user, carNumber, 0);
		boolean rentCarUprecord = rentCarUpRecord(user, carNumber, sql);
		if (rentCarUpcar && rentCarUprecord) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否该用户有未还租赁记录
	 * 
	 * @return true返回有，false返回没有
	 */
	@SuppressWarnings("unused")
	public boolean IFUserHavrecord(User user, Number rentselectid) {
		String sql2 = "select * from t_record where user_id = " + rentselectid + " and return_date is null";
		ArrayList<Record> selectRecord = selectRecord(sql2);
		if (selectRecord == null) {
			return false;
		}
		if (selectRecord.size() == 0) {
			return false;
		}
		if (selectRecord != null || selectRecord.size() > 0 || selectRecord.get(0) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询汽车id的对应租赁记录表是否存在于租赁表中
	 * 
	 * @param rentselectid
	 *            车的id
	 * @return true表示：存在，false表示不存在
	 */
	@SuppressWarnings("unused")
	public boolean IFCarHavrecord(Number rentselectid) {
		String sql2 = "select * from t_record where Car_id = " + rentselectid + " and return_date is null";
		ArrayList<Record> selectRecord = selectRecord(sql2);
		if (selectRecord == null) {
			return false;
		}
		if (selectRecord.size() == 0) {
			return false;
		}
		if (selectRecord != null || selectRecord.size() > 0 || selectRecord.get(0) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询对应的用户的id的租赁记录
	 * 
	 * @param rselectid
	 *            用户的id
	 * @return 返回租赁记录集合
	 */
	public ArrayList<Record> selectrecord(Number rselectid) {
		String sql = "select T_User.Username User_name,T_Car.Car_Number Car_name,start_date,return_date,payment from T_record,T_User,T_Car where T_Car.Id = T_record.Car_Id and T_user.Id = T_record.User_Id and user_id = "
				+ rselectid;
		ArrayList<Record> selectRecord = selectRecord(sql);
		return selectRecord;
	}

	/**
	 * 查询身份证的方法
	 * 
	 * @return 返回一个身份证的集合
	 */
	public ArrayList<String> selectIFIDNumber() {
		String sql = "select id_Number from T_user";
		ArrayList<String> uqexist = uqexist(sql);
		return uqexist;
	}
}
