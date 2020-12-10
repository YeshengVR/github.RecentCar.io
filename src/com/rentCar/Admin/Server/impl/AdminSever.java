package com.rentCar.Admin.Server.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import com.rentCar.Admin.Dao.impl.AdminDao;
import com.rentCar.Admin.Server.IAdminSever;
import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.Dao.impl.UserDao;
import com.rentCar.User.entry.User;

public class AdminSever implements IAdminSever {

	private AdminDao adminDao = new AdminDao();
	private UserDao userDao = new UserDao();

	/**
	 * 管理员查看所有汽车的方法，显示全部汽车
	 * 
	 * @return 返回一个查找到的全部的汽车的集合
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Car> selectCar() {
		ArrayList<Car> selectCar = adminDao.selectcar();
		return selectCar;
	}

	
	/**
	 * 根据车牌号查找指定汽车的方法
	 * 
	 * @param idNumber
	 *            车牌号信息
	 * @return 返回一个指定汽车的对象
	 */
	public Car selectByidNumberCar(String idNumber) {
		ArrayList<Car> selectCar = adminDao.selectByidNumbercar(idNumber);
		Car car =null;
		if(selectCar == null||selectCar.size()==0){
			return null;
		}else{
			car = selectCar.get(0);
		}
		return car;
	}

	

	/**
	 * 通过车牌号判断库里是否有这个车的属性，有则返回false，没有则返回true
	 * @param carNumber		车牌号
	 * @return				返回一个是否有这个属性的布尔值
	 */
	public boolean carNumber(String carNumber) {
		ArrayList<String> uqexist = adminDao.CarNumber();
		for (String string : uqexist) {
			if (string.equals(carNumber)) {
				return false;
			}
		}
		return true;
	}

	

	/**
	 * 添加汽车的方法
	 * 
	 * @param car
	 *            传递一个封装好的汽车对象
	 * @return 判断是否添加成功
	 */
	public boolean AddCar(Car car) {

		boolean updateNothing = adminDao.Addcar(car);
		return updateNothing;
	}

	

	/**
	 * 判断品牌是否存在于数据库中，不在则新建一个数据，返回这个品牌的id，在则直接返回id
	 * @param Brand		品牌属性	
	 * @return			返回一个品牌的id
	 */
	public Number UpdateBrand(String Brand) {
		if (selectBrand(Brand)) {
			adminDao.InsertBrand(Brand);
		}
		ArrayList<String> uqexist = adminDao.selectidBrand(Brand);
		String string = null;
		if(uqexist.size()!=0){
			string = uqexist.get(0);
		}else{
			return null;
		}
		Number number = null;
		try {
			number = NumberFormat.getInstance().parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return number;
	}

	

	

	/**
	 * 判断类别是否存在于数据库中，不在则新建一个数据，返回这个类别的id，在则直接返回id
	 * @param category	车的类别属性
	 * @return			返回一个类别的id属性
	 */
	public Number UpdateCategory(String category) {
		if (selectCategory(category)) {
			adminDao.InsertCategory(category);
		}
		ArrayList<String> uqexist = adminDao.selectidCategory(category);
		String string = null;
		if(uqexist.size()!=0){
			string = uqexist.get(0);
		}else{
			return null;
		}
		Number number = null;
		try {
			number = NumberFormat.getInstance().parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return number;
	}

	

	

	/**
	 * 判断是否找到了对应的类别找到了返回false，没找到返回true
	 * @param category	类别名
	 * @return			返回是否找到了类别
	 */
	public boolean selectCategory(String category) {
		ArrayList<String> uqexist = adminDao.selectIFcategory();
		for (String string : uqexist) {
			if(string == null){
				return true;
			}else if (category.equals(string)) {
				return false;
			}
		}
		return true;
	}

	

	/**
	 * 
	 * @param Brand
	 * @return
	 */
	public boolean selectBrand(String Brand) {
		ArrayList<String> uqexist = adminDao.selectIFBrand();
		for (String string : uqexist) {
			if (Brand.equals(string)) {
				return false;
			}
		}
		return true;
	}

	

	/**
	 * 修改汽车信息的方法
	 * 
	 * @param car
	 *            如果车辆已被租赁，则不可修改，需要修改可用不可用。
	 * @return 判断是否修改成功
	 */
	public boolean UpdateCar(Car car) {
		if (car.getUseable().intValue() == 1) {
			return false;
		}
		boolean updateNothing = adminDao.Updatecar(car);
		return updateNothing;
	}

	

	/**
	 * 查看所有用户全部租赁记录
	 * 
	 * @return 全部用户的租赁记录集合
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> selectAllRecord(User user) {
		ArrayList<Record> selectRecord = adminDao.selectAllidRecord();
		ArrayList<String> selectAllrecord = adminDao.selectAllrecord(user,selectRecord);
		return selectAllrecord;
	}

	

	
	/**
	 * 查看指定用户租赁记录
	 * 
	 * @return 指定用户的全部租赁记录
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Record> selectByUserIdRecord(String username) {
		Number rselectid = null;
		if(adminDao.Rselectid(username)==null){
			return null;
		}else{
			rselectid = adminDao.Rselectid(username);
		}
		ArrayList<Record> selectRecord = adminDao.selectByUseridrecord(rselectid);
		return selectRecord;
	}


	

	/**
	 * 查看指定汽车的租赁记录
	 * 
	 * @return 指定汽车的全部租赁记录
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Record> selectByIdNumberRecord(String idNumber) {
		Number rentselectcarid = userDao.Rentselectcarid(idNumber);
		if(rentselectcarid == null){
			return null;
		}
		ArrayList<Record> selectRecord = adminDao.selectCaridrecord(rentselectcarid);
		return selectRecord;
	}


	

	/**
	 * 查询指定用户信息
	 * 
	 * @return 返回一个装载了一个用户的集合
	 */
	public User selectByusernameTUser(String username) {
		ArrayList<User> selectUser = adminDao.selectByusernameUser(username);
		User user = null;
		if ((selectUser.size() == 0)) {
			return null;
		} else{
			user = selectUser.get(0);
		}
		return user;
	}


	

	/**
	 * 查询全部用户信息
	 * 
	 * @return 返回一个全部用户的集合
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<User> selectAllTUser() {
		ArrayList<User> selectUser = adminDao.selectallUser();
		if (selectUser.size() == 0) {
			return null;
		}
		return selectUser;
	}


	

	/**
	 * 删除汽车的方法			true 为删除成功，false为删除失败
	 * @param CarNumber		车牌号
	 * @return				返回一个是否删车成功的布尔值
	 */
	public boolean deleteCar(String CarNumber){
		Car car = selectByidNumberCar(CarNumber);
		if(car == null){
			return false;
		}
		if (car.getUseable().intValue() == 1) {
			return false;
		}
		boolean carNumber2 = carNumber(CarNumber);
		if(!carNumber2){
			boolean deleteCar = adminDao.deleteCar(CarNumber);
			return deleteCar;
		}else{
			return false;
		}
	}
}
