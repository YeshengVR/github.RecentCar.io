package com.rentCar.Admin.View;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import com.rentCar.Admin.Control.AdminControl;
import com.rentCar.Common.Methods.Input;
import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.View.UserView;
import com.rentCar.User.entry.User;

public class AdminView {

	private static UserView uv = new UserView();
	private static AdminControl ac = new AdminControl();

	/**
	 * 管理员登录之后的界面
	 * 
	 * @param 传入登录的管理员账户
	 */
	public void AdminAfterloginView(User user) {
		System.out.println("====================================");
		System.out.println("尊敬的管理员，欢迎您的到来............");
		System.out.println("请输入你要执行的功能................");
		System.out.println("1:进去普通用户界面.................");
		System.out.println("2:开通新管理员....................");
		System.out.println("3:查看全部汽车....................");
		System.out.println("4:根据指定车牌号查看汽车.............");
		System.out.println("5:添加汽车.......................");
		System.out.println("6:修改汽车信息....................");
		System.out.println("7:删除汽车信息....................");
		System.out.println("8:查看全部用户租赁记录..............");
		System.out.println("9:查看指定用户租赁记录..............");
		System.out.println("10:查看指定汽车租赁记录.............");
		System.out.println("11:查看指定用户信息................");
		System.out.println("12:查看全部用户信息................");
		System.out.println("13:退出quit....................");
		int index = IF1to12(user);
		switch (index) {
		case 1:
			AdminViewAfterloginUser(user);
			break;
		case 2:
			Number i = 1;
			uv.UserRegisterView(i);
			break;
		case 3:
			selectAllCarView(user);
			break;
		case 4:
			selectByidNumberCarView(user);
			break;
		case 5:
			AddCarView(user);
			break;
		case 6:
			UpdateCarView(user);
			break;
		case 7:
			DeleteCarView(user);
			break;
		case 8:
			selectAllRecordView(user);
			break;
		case 9:
			selectByUserRecordView(user);
			break;
		case 10:
			selectByCarRecordView(user);
			break;
		case 11:
			selectUserView(user);
			break;
		case 12:
			selectAllUserView(user);
		case 13:
			uv.exitView(user);
			break;
		default:
			System.out.println("没有此功能");
		}
	}

	/**
	 * 管理员返回普通用户界面
	 * 
	 * @param user
	 *            传入登陆的用户
	 */
	public void AdminViewAfterloginUser(User user) {
		System.out.println("====================================");
		System.out.println("登陆成功！欢迎您的到来，勇士！尊敬的" + user.getUsername() + "！");
		System.out.println("请输入你要执行的功能");
		System.out.println("1:注册新账号..........");
		System.out.println("2:查看所有汽车.........");
		System.out.println("3:根据租价查看汽车......");
		System.out.println("4:根据品牌查看汽车......");
		System.out.println("5:根据类别查看汽车......");
		System.out.println("6:租车..............");
		System.out.println("7:还车..............");
		System.out.println("8:查询租车记录.........");
		System.out.println("9:返回管理员界面........");
		System.out.println("10:返回未登录界面........");
		int index = IF1to10(user);
		switch (index) {
		case 1:
			Number i = 0;
			uv.UserRegisterView(i);
			break;
		case 2:
			uv.ALLCarView(user);
			break;
		case 3:
			uv.CarByRentView(user);
			break;
		case 4:
			uv.CarByBrandView(user);
			break;
		case 5:
			uv.CarByCategoryView(user);
			break;
		case 6:
			uv.rentCarView(user);
			uv.IFGoBack(user);
			break;
		case 7:
			uv.returnCarView(user);
			break;
		case 8:
			uv.RecordView(user);
			break;
		case 9:
			AdminAfterloginView(user);
			break;
		case 10:
			uv.exitloginAfterView();
			break;
		default:
			System.out.println("没有此功能");
		}
	}

	/**
	 * 主界面的输入信息判断符号
	 * 
	 * @return 返回一个整数类型输入的数值，功能号位
	 */
	public int IF1to12(User user) {
		String line = Input.nextLine();
		uv.Quit(line, user);
		int index = 0;
		if (line.matches("^([1-9]|10|11|12|13)$")) {
			index = Integer.parseInt(line);
			return index;
		} else {
			System.out.println("只能输入1到12的功能号噢，后续功能敬请期待........");
			index = IF1to12(user);
		}
		return index;
	}

	public int IF1to10(User user) {
		String line = Input.nextLine();
		uv.Quit(line, user);
		int index = 0;
		if (line.matches("^([1-9]|10)$")) {
			index = Integer.parseInt(line);
			return index;
		} else {
			System.out.println("只能输入1到10的功能号噢，后续功能敬请期待........");
			index = IF1to10(user);
		}
		return index;
	}

	/**
	 * 查询所有车辆信息的界面
	 * 
	 * @param user
	 *            传递一个user账号
	 */
	public void selectAllCarView(User user) {
		System.out.println("====================================");
		ArrayList<Car> selectCar = ac.selectCar();
		printCar(selectCar);
		uv.IFGoBack(user);
	}

	/**
	 * 输出Car车集合
	 * 
	 * @param selectCar
	 *            Car的集合
	 */
	public void printCar(ArrayList<Car> selectCar) {
		for (Car car : selectCar) {
			System.out.println(car.toStringa());
		}
	}

	/**
	 * 根据车牌号查询指定汽车信息
	 * 
	 * @param user
	 *            传递一个汽车
	 */
	public void selectByidNumberCarView(User user) {
		System.out.println("====================================");
		System.out.println("欢迎进入查询指定汽车界面");
		System.out.println("请输入你要查询的车牌号");
		String nextLine = Input.nextLine();
		uv.Quit(nextLine, user);
		Car selectByidNumberCar = ac.selectByidNumberCar(nextLine);
		if (selectByidNumberCar == null) {
			System.out.println("没有此车噢");
		} else {
			System.out.println(selectByidNumberCar.toStringa());
		}
		uv.IFGoBack(user);
	}

	/**
	 * 添加汽车的界面
	 */
	public void AddCarView(User user) {
		System.out.println("====================================");
		System.out.println("欢迎进入添加汽车信息界面");
		System.out.println("请输入你要添加的汽车的车牌号信息");
		String CarNumber = IFCarNumber(user);
		System.out.println("请输入你要添加的品牌名");
		String Brands = IFBrand(user);
		Number brand = ac.UpdateBrand(Brands);
		System.out.println("请输入你要添加的型号");
		String model = IFmodel(user);
		System.out.println("请输入你要添加的颜色");
		String color = IFColor(user);
		System.out.println("请输入你要添加的汽车类型");
		String categorys = IFCategory(user);
		Number category = ac.UpdateCategory(categorys);
		System.out.println("请输入你要添加的汽车信息类似于：自动/手动1.0T,自动/手动1.6L");
		String comments = IFcomments(user);
		System.out.println("请输入车的价格,9位以内");
		Number price = IFPrice(user);
		System.out.println("请输入车一天的租金");
		Number rent = IFRent(user, price);
		Number status = 0;
		Number useable = 0;
		Car car = new Car(CarNumber, brand, model, color, category, comments, price, rent, status, useable);
		boolean addCar = ac.AddCar(car);
		if (addCar) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}
		uv.IFGoBack(user);
	}

	/**
	 * 判断车牌号是否符合规则
	 * 
	 * @param user
	 *            传入登录的用户，判断是否要登出用
	 * @return 返回一个车牌号信息
	 */
	public String IFCarNumber(User user) {
		String Car_Number = Input.nextLine();
		uv.Quit(Car_Number, user);
		if (Car_Number.matches("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$")) {
			if (ac.carNumbers(Car_Number)) {
				return Car_Number;
			} else {
				System.out.println("该车已经录入了信息中,不能再次录入");
				Car_Number = IFCarNumber(user);
			}
		} else {
			System.out.println("请重新输入符合规范的车牌号噢");
			Car_Number = IFCarNumber(user);
		}
		return Car_Number;
	}

	/**
	 * 判断信息
	 * 
	 * @param user
	 * @return
	 */
	public String IFcomments(User user) {
		String comments = Input.nextLine();
		uv.Quit(comments, user);
		if (comments.matches("[自|手][动]+[0-9].[0-9][T|L]")) {
			return comments;
		} else if (comments.equals("") || comments.matches("\\s+")) {
			return comments;
		} else {
			System.out.println("请重新录入符合规范的车的信息噢，类似于：自动/手动1.0T,自动/手动1.6L");
			comments = IFcomments(user);
		}
		return comments;
	}

	/**
	 * 判断价格
	 * 
	 * @param user
	 * @return
	 */
	public Number IFPrice(User user) {
		String prices = Input.nextLine();
		uv.Quit(prices, user);
		Number number = null;
		if (prices.matches("^[1-9][0-9]{1,8}+(.[0-9]{1,2})?$")) {
			try {
				number = NumberFormat.getInstance().parse(prices);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return number;
		} else {
			System.out.println("请输入9位以内的价格，可以有两位小数位");
			number = IFPrice(user);
		}
		return number;
	}

	/**
	 * 判断租金
	 * 
	 * @param user
	 * @return
	 */
	public Number IFRent(User user, Number price) {
		String prices = Input.nextLine();
		uv.Quit(prices, user);
		Number number = null;
		if (prices.matches("^[1-9][0-9]{1,6}+(.[0-9]{1,2})?$")) {
			try {
				number = NumberFormat.getInstance().parse(prices);
				if (number.intValue() > price.intValue()) {
					System.out.println("车辆每天的租价不能超过其车的本金，就算是你超豪华改造车也不能！！");
					number = IFPrice(user);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return number;
		} else {
			System.out.println("请输入7位以内的价格，可以有两位小数位");
			number = IFPrice(user);
		}
		return number;
	}

	/**
	 * 修改汽车时判断汽车车牌号是否符合规范
	 * 
	 * @param user
	 *            登录的用户
	 * @return 车牌号
	 */
	public String IFCarNumbers(User user) {
		String CarNumber = Input.nextLine();
		uv.Quit(CarNumber, user);
		if (CarNumber.matches("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$")) {
			return CarNumber;
		} else {
			System.out.println("请输入正确的车牌号形式");
			CarNumber = IFCarNumbers(user);
		}
		return CarNumber;
	}

	/**
	 * 修改汽車的界面
	 * 
	 * @param user
	 *            登录的用户
	 */
	public void UpdateCarView(User user) {
		System.out.println("====================================");
		System.out.println("欢迎进入修改汽车信息界面");
		System.out.println("请输入你要修改的汽车的车牌号信息");
		Car selectByidNumberCar = IFCarexist(user);
		if(selectByidNumberCar.getUseable().intValue()==1){
			System.out.println("该车已被租赁不能修改");
			uv.IFGoBack(user);
		}
		System.out.println("------------------这是原本的车辆信息------------------");
		System.out.println(selectByidNumberCar.toStringa());
		System.out.println("请输入你要修改的车牌号信息");
		String CarNumber = IFCarUpexist(user);
		System.out.println("请输入你要修改成的品牌名");
		String Brands = IFBrand(user);
		Number brand = ac.UpdateBrand(Brands);
		System.out.println("请输入你要修改成的型号");
		String model = IFmodel(user);
		System.out.println("请输入你要修改成的颜色");
		String color = IFColor(user);
		System.out.println("请输入你要修改成的汽车类型");
		String categorys = IFCategory(user);
		Number category = ac.UpdateCategory(categorys);
		System.out.println("请输入你要修改的汽车信息类似于：自动/手动1.0T,自动/手动1.6L");
		String comments = IFcomments(user);
		System.out.println("请输入更改后车的价格,9位以内");
		Number price = IFPrice(user);
		System.out.println("请输入更改后租车一天的租金");
		Number rent = IFRent(user, price);
		Number status = 0;
		System.out.println("请输入你要修改的汽车状态0为未损坏/已修复，1为已损坏/待修复");
		status = uv.IF0or1(user);
		Car car = null;
		car = new Car(selectByidNumberCar.getId(),CarNumber, brand, model, color, category, comments, price, rent, status,
				selectByidNumberCar.getUseable());
		boolean addCar = ac.UpdateCar(car);
		if (addCar) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
			System.out.println("车辆可能正在被使用，不能修改信息");
		}
		uv.IFGoBack(user);
	}

	
	public String IFCarUpexist(User user) {
		String CarNumber = IFCarNumbers(user);
		if(!ac.carNumbers(CarNumber)){
			System.out.println("已有此车牌信息，不能修改为这个车牌号");
			CarNumber = IFCarUpexist(user);
		}
		return CarNumber;
	}
	/**
	 * 判断车辆信息是否存在于数据库中
	 * 
	 * @param user
	 *            登录的用户
	 * @return 返回一个查询到的车辆信息
	 */
	public Car IFCarexist(User user) {
		String CarNumber = IFCarNumbers(user);
		Car selectByidNumberCar = ac.selectByidNumberCar(CarNumber);
		if (selectByidNumberCar == null) {
			System.out.println("没有这辆车的信息请重新输入");
			System.out.println("请重新输入车牌号信息");
			selectByidNumberCar = IFCarexist(user);
		} else {
			return selectByidNumberCar;
		}
		return selectByidNumberCar;
	}

	public String IFColor(User user) {
		String color = Input.nextLine();
		uv.Quit(color, user);
		if (!color.matches("^[\\u4e00-\\u9fa5]{1,6}$|^[\\dA-Za-z_]{1,20}$")) {
			System.out.println("只能输入汉字或者只输入字母和数字噢汉字最大输入6位，字母和数字组合最多输出20位");
			color = IFmodel(user);
		} else if (color.equals("") || color.matches("\\s+")) {
			return null;
		}
		return color;
	}

	public String IFmodel(User user) {
		String model = Input.nextLine();
		uv.Quit(model, user);
		if (!model.matches("^[\\u4e00-\\u9fa5]{1,13}$|^[\\dA-Za-z_]{1,40}$")) {
			System.out.println("只能输入汉字或者只输入字母和数字噢汉字最大输入13位，字母和数字组合最多输出40位");
			model = IFmodel(user);
		}
		return model;
	}

	public String IFBrand(User user) {
		String Brands = Input.nextLine();
		uv.Quit(Brands, user);
		if (!Brands.matches("^[\\u4e00-\\u9fa5]{1,13}$|^[\\dA-Za-z_]{1,50}$")) {
			System.out.println("只能输入汉字或者只输入字母和数字噢汉字最大输入16位，字母和数字组合最多输出50位");
			Brands = IFBrand(user);
		}
		return Brands;
	}

	public String IFCategory(User user) {
		String category = Input.nextLine();
		uv.Quit(category, user);
		if (!category.matches("^[\\u4e00-\\u9fa5]{1,13}$|^[\\dA-Za-z_]{1,40}$")) {
			System.out.println("只能输入汉字或者只输入字母和数字噢汉字最大输入13位，字母和数字组合最多输出40位");
			category = IFmodel(user);
		}
		return category;
	}

	/**
	 * 打印全部租赁记录
	 * 
	 * @param user
	 */
	public void selectAllRecordView(User user) {
		System.out.println("====================================");
		System.out.println("欢迎进入查看全部租赁记录界面");
		ArrayList<String> selectAllRecord = ac.selectAllRecord(user);
		for (String string : selectAllRecord) {
			System.out.println(string);
		}
		uv.IFGoBack(user);
	}

	/**
	 * 打印指定用户的全部租赁记录
	 * 
	 * @param user
	 *            登录的用户
	 */
	public void selectByUserRecordView(User user) {
		System.out.println("====================================");
		System.out.println("查询指定用户全部租赁记录界面");
		System.out.println("请输入用户名");
		String line = Input.nextLine();
		uv.Quit(line, user);
		ArrayList<Record> selectAllRecord = ac.selectByUserIdRecord(line);
		if (selectAllRecord == null || selectAllRecord.size() == 0) {
			System.out.println("没有找到此用户");

		} else {
			printRecord(selectAllRecord);
		}
		uv.IFGoBack(user);
	}

	/**
	 * 查询指定车辆的租赁记录页面
	 * 
	 * @param user
	 *            登录的用户
	 */
	public void selectByCarRecordView(User user) {
		System.out.println("====================================");
		System.out.println("查询指定车辆的租赁记录界面");
		System.out.println("请输入车牌号");
		String line = Input.nextLine();
		uv.Quit(line, user);
		ArrayList<Record> selectByIdNumberRecord = ac.selectByIdNumberRecord(line);
		if (selectByIdNumberRecord == null) {
			System.out.println("不好意思没有查询到这辆车");
		} else {
			printRecord(selectByIdNumberRecord);
		}
		uv.IFGoBack(user);
	}

	/**
	 * 打印租赁记录界面
	 * 
	 * @param selectAllRecord
	 *            要打印的租赁记录集合
	 */
	public void printRecord(ArrayList<Record> selectAllRecord) {
		for (Record record : selectAllRecord) {
			System.out.println(record.toStrings());
		}
	}

	/**
	 * 查询指定用户信息的界面
	 * 
	 * @param user
	 *            登录的用户
	 */
	public void selectUserView(User user) {
		System.out.println("====================================");
		System.out.println("查询指定用户信息界面");
		System.out.println("请输入你要查询的用户名");
		String line = Input.nextLine();
		uv.Quit(line, user);
		User selectByUserid = ac.selectByUserid(line);
		if (selectByUserid != null) {
			System.out.println(selectByUserid.toStringU());
		} else if (selectByUserid == null) {
			System.out.println("没有找到此用户信息，请核对要查询的人的用户名是否为：" + line);
		}
		uv.IFGoBack(user);
	}

	/**
	 * 查询全部用户的界面
	 * 
	 * @param user
	 *            登录的用户
	 */
	public void selectAllUserView(User user) {
		System.out.println("====================================");
		System.out.println("全部用户信息界面");
		ArrayList<User> selectAllUser = ac.selectAllUser();
		if (selectAllUser == null | selectAllUser.size() == 0) {
			System.out.println("不好意思当前没有用户");
		}
		for (User user2 : selectAllUser) {
			System.out.println(user2.toStringU());
		}
		uv.IFGoBack(user);
	}

	/**
	 * 删除车辆界面
	 * 
	 * @param user
	 */
	public void DeleteCarView(User user) {
		System.out.println("====================================");
		System.out.println("删除车辆界面");
		System.out.println("请输入你要删除的车辆车牌号信息");
		String id_Number = Input.nextLine();
		uv.Quit(id_Number, user);
		if (ac.deleteCar(id_Number)) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
			System.out.println("可能车辆正在被使用中无法删除或者没有此车");
		}
		uv.IFGoBack(user);
	}
}
