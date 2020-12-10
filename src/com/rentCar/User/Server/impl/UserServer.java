package com.rentCar.User.Server.impl;

import java.util.ArrayList;

import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.Dao.impl.UserDao;
import com.rentCar.User.Server.IUserServer;
import com.rentCar.User.entry.User;

public class UserServer implements IUserServer {
	private UserDao userDao = new UserDao();

	/**
	 * 用户登录的方法
	 * 
	 * @return 返回登陆的用户
	 */
	@Override
	public User login(String username, String password) {

		User login = userDao.login(username, password);
		return login;
	}

	/**
	 * 用户注册的方法
	 * 
	 * @return 返回一个判断用户是否注册成功的true/false
	 */
	@Override
	public boolean register(User user) {
		boolean register = userDao.register(user);
		return register;
	}

	/**
	 * 判断用户名是否重复
	 * 
	 * @param username
	 *            传入用户注册的用户名
	 * @return 返回一个用户名是否可用的布尔值
	 */
	public boolean RegisterIFUsername(String username) {
		ArrayList<String> uqUserName = userDao.uqUserName();
		boolean flag = true;
		for (String string : uqUserName) {
			if (username.equals(string)) {
				flag = false;
				break;
			} else {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 用户查看所有汽车的方法，只显示可以使用的汽车，汽车是坏的的话就不显示
	 * 
	 * @return 返回一个查找到的全部的汽车的集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Car> selectCar() {
		ArrayList<Car> selectCar = userDao.selectCarsql();
		return selectCar;
	}

	/**
	 * 用户根据价格升序来查询汽车的方法
	 * 
	 * @return 返回一个根据需求所找出的汽车集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Car> selectCarByT_priceasc() {
		ArrayList<Car> arrayList = userDao.selectCarByTpascsql();
		return arrayList;
	}

	/**
	 * 用户根据价格降序来查询汽车的方法
	 * 
	 * @return 返回一个根据需求所找出的汽车集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Car> selectCarByT_pricedesc() {
		ArrayList<Car> arrayList = userDao.selectCarByTpdescsql();
		return arrayList;
	}

	/**
	 * 用户根据品牌来查询汽车的方法
	 * 
	 * @param brand
	 *            传递一个需求的品牌
	 * @return 返回一个根据需求所找出的汽车集合
	 */

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Car> selectCarByT_Brand(String brand) {
		ArrayList<String> selectCarByBrandWhereExist = selectCarByBrandWhereExist(brand);
		if (selectCarByBrandWhereExist == null)
			return null;
		ArrayList<Car> arrayList = userDao.selectCarByTbrandsql(brand);
		return arrayList;
	}

	/**
	 * 搜索对应的车品牌是否存在
	 * 
	 * @param brand
	 *            车品牌
	 * @return 返回一个集合
	 */
	public ArrayList<String> selectCarByBrandWhereExist(String brand) {
		ArrayList<String> uqexist = userDao.selectCarByBrandWhereExistsql();
		boolean flag = false;
		if (brand == null) {
			return uqexist;
		}
		for (String str : uqexist) {
			if (brand.equals(str)) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		if (!flag) {
			return null;
		}
		return uqexist;
	}

	/**
	 * 用户根据类别来查询汽车的方法
	 * 
	 * @param category
	 *            传递一个需求的sql语句
	 * @return 返回一个根据需求所找出的汽车集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Car> selectCarByT_Category(String category) {
		ArrayList<Car> arrayList = userDao.selectCarByT_Categorysql(category);
		return arrayList;
	}

	/**
	 * 用户租车的方法
	 * 
	 * @param user
	 *            传递一个要租车的用户信息
	 * @return 返回一个租车是否成功的布尔值
	 */
	@Override
	public boolean Rent_Car(User user, String carNumber) {
		Number rentselectid = userDao.Rselectid(user);
		Number rentselectcarid = userDao.Rentselectcarid(carNumber);
		if (rentselectcarid == null) {
			return false;
		}
		return userDao.rentCarsql(user, carNumber, rentselectid, rentselectcarid);
	}

	/**
	 * 用户还车的方法
	 * 
	 * @param user
	 *            传递一个需要还车的用户信息
	 * @return 返回一个还车是否成功的布尔值
	 */
	@Override
	public boolean Return_Car(User user, String carNumber) {
		Number rentselectid = userDao.Rselectid(user);
		Number rentselectcarid = userDao.Rentselectcarid(carNumber);
		if (rentselectcarid == null) {
			return false;
		}
		return userDao.returnsql(user, carNumber, rentselectid, rentselectcarid);
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
	public ArrayList<Record> selectRecord(User user) {
		Number rselectid = userDao.Rselectid(user);
		ArrayList<Record> selectRecord = userDao.selectrecord(rselectid);
		return selectRecord;
	}

	/**
	 * 查询是否还有这个身份证的方法
	 * @param idNumber	输入的身份证
	 * @return			如果数据库中有这个身份证则返回true，没有则返回false。
	 */
	public boolean selectIDNumber(String idNumber) {
		ArrayList<String> selectIFIDNumber = userDao.selectIFIDNumber();
		for (String string : selectIFIDNumber) {
			if (idNumber.equals(string)) {
				return true;
			}
		}
		return false;
	}

}
