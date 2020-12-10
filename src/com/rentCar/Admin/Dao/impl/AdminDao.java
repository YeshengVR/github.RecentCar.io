package com.rentCar.Admin.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rentCar.Admin.Dao.IAdminDao;
import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.Dao.impl.UserDao;
import com.rentCar.User.entry.User;
import com.rentCar.Util.JDBCUtil;

public class AdminDao implements IAdminDao {
	private Connection conn = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;

	private static UserDao userDao = new UserDao();

	/**
	 * 根据需求查询汽车信息
	 * 
	 * @param sql
	 *            需求的sql语句
	 * @return 返回一个需要查询到的汽车信息
	 */
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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

	/**
	 * 根据用户名查找对应的id
	 * 
	 * @param username
	 *            传递指定用户名
	 * @return 返回一个用户的数据库id
	 */
	public Number Rselectid(String username) {
		Number index = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from T_user where username = ?";
			state = conn.prepareStatement(sql);
			state.setString(1, username);
			rs = state.executeQuery();
			ArrayList<User> users = JDBCUtil.Select(rs, User.class);
			if (users != null && users.size() > 0) {
				User user3 = users.get(0);
				index = user3.getId();
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("⚠警告！");
			e.printStackTrace();
		}
		return index;
	}

	/**
	 * 根据车牌号删除汽车的方法
	 * 
	 * @param CarNumber
	 *            车牌号
	 * @return 返回一个是否删除成功的布尔值
	 */
	public boolean deleteCar(String CarNumber) {
		String sql = "delete T_car where car_Number = ?";
		System.out.println(CarNumber);
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			state.setString(1, CarNumber);
			state.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.close(rs, state, conn);
		}
		return true;
	}

	/**
	 * 通过id判断这辆车是否存在于T_car表中
	 * 
	 * @param carid
	 *            车辆的id
	 * @return 这个id对应的车存在的数据库中的话返回true，不存在的话返回false
	 */
	public boolean selectCarBycarid(Number carid) {
		String sql = "select * from T_car where id = " + carid + "";
		ArrayList<Car> selectCar = selectCar(sql);
		if (selectCar != null && selectCar.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询全部租赁信息
	 * @param selectRecord	查询数据库中的全部租赁
	 * 信息
	 * @return				根据车辆是否被删除判断要传输的数据是输出车牌号还是车的id号返回一个String类型的集合
	 */
	public ArrayList<String> selectAllrecord(User user,ArrayList<Record> selectRecord) {
		String sql2 = null;
		ArrayList<String> sR = new ArrayList<String>();
		String stringd = null;
		for (Record record : selectRecord) {
			if(selectCarBycarid(record.getCar_id())){
				sql2 = "select T_User.Username User_name,T_Car.Car_Number Car_name,start_date,return_date,payment from T_record,T_User,T_Car where T_Car.Id = T_record.Car_Id and T_user.Id = T_record.User_Id and T_record.id = "+record.getId();
				ArrayList<Record> selectRecord2 = selectRecord(sql2);
				Record record2 = selectRecord2.get(0);
				stringd = record2.toStrings();
			}else{
				sql2 = "select distinct T_User.Username User_name,T_record.Car_id Car_id,start_date,return_date,payment from T_record,T_User,T_Car where T_user.Id = T_record.User_Id and T_record.id = "+record.getId();
				ArrayList<Record> selectRecord2 = selectRecord(sql2);
				Record record2 = selectRecord2.get(0);
				stringd = record2.toStringd();
			}
			sR.add(stringd);
		}
		return sR;
	}

	/**
	 * 查询车辆信息
	 * 
	 * @param car
	 *            传入要更新的的车辆信息
	 * @return
	 */
	public boolean Updatecar(Car car) {
		String sql = "update T_car set car_number = ?, brand_id = ?,model = ?,color = ?, category_id = ?, t_comments = ?,price = ?,rent = ?,status = ?, useable = ? where id = ?";
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			state.setString(1, car.getCar_number());
			state.setInt(2, car.getBrand_id().intValue());
			state.setString(3, car.getModel());
			state.setString(4, car.getColor());
			state.setInt(5, car.getCategory_id().intValue());
			state.setString(6, car.getT_Comments());
			state.setInt(7, car.getPrice().intValue());
			state.setInt(8, car.getRent().intValue());
			state.setInt(9, car.getStatus().intValue());
			state.setInt(10, car.getUseable().intValue());
			state.setInt(11, car.getId().intValue());
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
	 * 判断品牌名是否存在集合中的步骤一
	 * 
	 * @return 返回一个数据
	 */
	public ArrayList<String> selectIFBrand() {
		String sql = "select name from T_brand";
		ArrayList<String> uqexist = userDao.uqexist(sql);
		return uqexist;
	}

	/**
	 * 判断类别名是否存在集合中的步骤一
	 * 
	 * @return 返回一个数据
	 */
	public ArrayList<String> selectIFcategory() {
		String sql = "select name from T_Category";
		ArrayList<String> uqexist = userDao.uqexist(sql);
		return uqexist;
	}

	/**
	 * 添加类别表中元素
	 * 
	 * @param category
	 *            类别
	 */
	public void InsertCategory(String category) {
		String sql0 = "insert into T_Category values(t_Brand_Id_Seq.Nextval,'" + category + "')";
		UpdateNothing(sql0);
	}

	/**
	 * 查询类别对应的id
	 * 
	 * @param category
	 * @return
	 */
	public ArrayList<String> selectidCategory(String category) {
		String sql = "select id from T_Category where name = '" + category + "'";
		ArrayList<String> uqexist = userDao.uqexist(sql);
		return uqexist;
	}

	/**
	 * 添加品牌表中元素
	 * 
	 * @param Brand
	 *            类别
	 */
	public void InsertBrand(String Brand) {
		String sql0 = "insert into T_brand values(t_Brand_Id_Seq.Nextval,'" + Brand + "')";
		UpdateNothing(sql0);
	}

	/**
	 * 查询品牌对应的id
	 * 
	 * @param Brand
	 *            品牌名
	 * @return
	 */
	public ArrayList<String> selectidBrand(String Brand) {
		String sql = "select id from T_brand where name = '" + Brand + "'";
		ArrayList<String> uqexist = userDao.uqexist(sql);
		return uqexist;
	}

	/**
	 * 添加车辆的方法
	 * 
	 * @param car
	 *            包装好的车辆信息
	 * @return 返回一个是否添加成功的布尔值
	 */
	public boolean Addcar(Car car) {
		String sql = "insert into T_car values(t_Car_Id_Seq.Nextval,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = JDBCUtil.getConnection();
			state = conn.prepareStatement(sql);
			state.setString(1, car.getCar_number());
			state.setInt(2, car.getBrand_id().intValue());
			state.setString(3, car.getModel());
			state.setString(4, car.getColor());
			state.setInt(5, car.getCategory_id().intValue());
			state.setString(6, car.getT_Comments());
			state.setInt(7, car.getPrice().intValue());
			state.setInt(8, car.getRent().intValue());
			state.setInt(9, car.getStatus().intValue());
			state.setInt(10, car.getUseable().intValue());
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
	 * 搜索全部的车牌号在车表中
	 * 
	 * @return
	 */
	public ArrayList<String> CarNumber() {
		String sql = "select car_Number from T_car";
		ArrayList<String> uqexist = userDao.uqexist(sql);
		return uqexist;
	}

	/**
	 * 通过车牌号查找车辆的集合
	 * 
	 * @param idNumber
	 *            车辆的车牌号
	 * @return
	 */
	public ArrayList<Car> selectByidNumbercar(String idNumber) {
		String sql = "Select T_car.id,T_car.car_number car_number,T_brand.Name brand_name,T_car.model, T_car.color,t_Category.Name category_name, T_car.t_comments T_Comments,T_car.price Price,T_car.rent Rent,status,useable "
				+ "from T_car, T_Brand, t_Category "
				+ "where T_car.Brand_Id = T_Brand.Id and T_car.Category_Id = T_category.Id and Car_Number = '"
				+ idNumber + "'";
		ArrayList<Car> selectCar = selectCar(sql);
		return selectCar;
	}

	/**
	 * 查车的方法
	 * 
	 * @return 车辆的集合
	 */
	public ArrayList<Car> selectcar() {
		String sql = "select T_car.car_number car_number,T_brand.Name brand_name,T_car.model,T_car.color,t_Category.Name category_name,T_car.t_comments T_Comments,T_car.price Price,T_car.rent Rent,status Status,useable Useable "
				+ "from T_car, t_Category, T_brand "
				+ "where T_car.brand_id = T_brand.id and T_car.Category_Id = t_Category.Id and status = 0 and useable = 0";
		ArrayList<Car> selectCar = selectCar(sql);
		return selectCar;
	}

	/**
	 * 通过id查找对饮的租赁记录含Car_id的方法
	 * 
	 * @return
	 */
	public ArrayList<Record> selectAllidRecord() {
		String sql = "select T_record.id id,T_User.Username User_name,T_record.Car_id Car_id,start_date,return_date,payment from T_record inner join T_User on T_User.Id = T_record.User_Id ";
		ArrayList<Record> selectRecord = selectRecord(sql);
		return selectRecord;
	}

	/**
	 * 通过用户名id查找对应的租赁记录
	 * 
	 * @param rselectid
	 *            用户民id
	 * @return
	 */
	public ArrayList<Record> selectByUseridrecord(Number rselectid) {
		String sql = "select T_User.Username User_name,T_Car.Car_Number Car_name,start_date,return_date,payment from T_record,T_User,T_Car where T_Car.Id = T_record.Car_Id and T_user.Id = T_record.User_Id and user_id = "
				+ rselectid;
		ArrayList<Record> selectRecord = selectRecord(sql);
		return selectRecord;
	}

	/**
	 * 通过车辆id来查找租赁记录
	 * 
	 * @param rentselectcarid
	 *            车辆的id
	 * @return 车辆的租赁记录
	 */
	public ArrayList<Record> selectCaridrecord(Number rentselectcarid) {
		String sql = "select T_User.Username User_name,T_Car.Car_Number Car_name,start_date,return_date,payment from T_record,T_User,T_Car where T_Car.Id = T_record.Car_Id and T_user.Id = T_record.User_Id and car_id = "
				+ rentselectcarid;
		ArrayList<Record> selectRecord = selectRecord(sql);
		return selectRecord;
	}

	/**
	 * 通过指定的username来查找指定用户信息
	 * 
	 * @param username
	 *            通过指定用户id
	 * @return
	 */
	public ArrayList<User> selectByusernameUser(String username) {
		String sql = "select username,password,sex,id_Number,tel,addr,type from T_user where username = '" + username
				+ "'";
		ArrayList<User> selectUser = selectUser(sql);
		return selectUser;
	}

	/**
	 * 查询所有用户信息
	 * 
	 * @return 返回所有用户信息的集合
	 */
	public ArrayList<User> selectallUser() {
		String sql = "select username,password,sex,id_Number,tel,addr,type from T_user";
		ArrayList<User> selectUser = selectUser(sql);
		return selectUser;
	}
}
