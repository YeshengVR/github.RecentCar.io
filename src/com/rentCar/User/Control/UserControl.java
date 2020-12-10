package com.rentCar.User.Control;

import java.util.ArrayList;

import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.Server.impl.UserServer;
import com.rentCar.User.entry.User;

//看门狗的角色，方法里面只需要调用Server的方法就好
public class UserControl {
	private UserServer us = new UserServer();

	/**
	 * 登陆
	 * 
	 * @param username
	 *            传递用户名
	 * @param password
	 *            传递密码
	 * @return 返回一个用户
	 */
	public User login(String username, String password) {
		User login = us.login(username, password);
		return login;
	}

	/**
	 * 判断注册用户名是否重复
	 * 
	 * @param username
	 *            用户名
	 * @return 返回一个判断值
	 */
	public boolean RegisterIFUsername(String username) {
		boolean registerIFUsername = us.RegisterIFUsername(username);
		return registerIFUsername;
	}

	public boolean register(User user) {
		boolean register = us.register(user);
		return register;
	}
	/**
	 * 用户查看所有汽车的方法，只显示可以使用的汽车，汽车是坏的的话就不显示
	 * @return	返回一个查找到的全部的汽车的集合
	 */
	public ArrayList<Car> selectCar(){
		ArrayList<Car> selectCar = us.selectCar();
		return selectCar;
	}

	/**
	 * 用户根据价格升序来查询汽车的方法
	 * @return		返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByT_priceasc(){
		ArrayList<Car> selectCar = us.selectCarByT_priceasc();
		return selectCar;
	}
	
	/**
	 * 用户根据价格降序来查询汽车的方法
	 * @return		返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByT_pricedesc(){
		ArrayList<Car> selectCar = us.selectCarByT_pricedesc();
		return selectCar;
	}
	
	/**
	 * 用户根据品牌来查询汽车的方法
	 * @param brand	传递一个需求的品牌
	 * @return		返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByT_Brand(String brand){
		ArrayList<Car> selectCarByT_Brand = us.selectCarByT_Brand(brand);
		return selectCarByT_Brand;
	}
	
	public ArrayList<String> selectCarByBrandWhereExists(String brand){
		ArrayList<String> selectCarByBrandWhereExist = us.selectCarByBrandWhereExist(brand);
		return selectCarByBrandWhereExist;
	}
	/**
	 * 用户根据类别来查询汽车的方法
	 * @param sql	传递一个需求的sql语句
	 * @return		返回一个根据需求所找出的汽车集合
	 */
	public ArrayList<Car> selectCarByT_Category(String category){
		ArrayList<Car> selectCarByT_Category = us.selectCarByT_Category(category);
		return selectCarByT_Category;
	}

	/**
	 * 用户租车的方法
	 * @param user  传递一个要租车的用户信息
	 * @return		返回一个租车是否成功的布尔值
	 */
	public boolean Rent_Car(User user,String carNumber){
		boolean rent_Car = us.Rent_Car(user, carNumber);
		return rent_Car;
	}

	
	/**
	 * 用户还车的方法
	 * @param user  传递一个需要还车的用户信息
	 * @return		返回一个还车是否成功的布尔值
	 */
	public boolean Return_Car(User user,String carNumber){
		boolean return_Car = us.Return_Car(user, carNumber);
		return return_Car;
	}

	/**
	 * 查看本用户的所有租赁记录
	 * @param user	传递本User对象的用户信息
	 * @return		返回一个本用户的所有租赁信息的集合
	 */
	public ArrayList<Record> selectRecord(User user){
		ArrayList<Record> selectRecord = us.selectRecord(user);
		return selectRecord;
	}

	/**
	 * 查询是否还有这个身份证的方法
	 * @param idNumber	输入的身份证
	 * @return			如果数据库中有这个身份证则返回true，没有则返回false。
	 */
	public boolean selectIDNumber(String idNumber){
		return us.selectIDNumber(idNumber);
	}
	
}
