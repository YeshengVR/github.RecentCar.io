package com.rentCar.User.View;

import java.util.ArrayList;

import com.rentCar.Admin.View.AdminView;
import com.rentCar.Common.Methods.Input;
import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.Control.UserControl;
import com.rentCar.User.entry.User;

public class UserView {
	private static UserControl uc = new UserControl();
	private static AdminView av = new AdminView();

	public void UserBeforeView() {
		System.out.println("====================================");
		System.out.println("请输入你要执行的功能");
		System.out.println("1:登陆..............");
		System.out.println("2:注册..............");
		System.out.println("3:查看所有汽车.........");
		System.out.println("4:根据租价查看汽车......");
		System.out.println("5:根据品牌查看汽车......");
		System.out.println("6:根据类别查看汽车......");
		System.out.println("7:租车..............");
		System.out.println("8:还车..............");
		System.out.println("9:退出quit..........");
		int index = IF1to9(null);
		switch (index) {
		case 1: {
			UserLoginView();
			break;
		}
		case 2:
			Number i = 0;
			UserRegisterView(i);
			break;
		case 3:
			ALLCarView(null);
			break;
		case 4:
			CarByRentView(null);
			break;
		case 5:
			CarByBrandView(null);
			break;
		case 6:
			CarByCategoryView(null);
			break;
		case 7:
			System.out.println("请先登录再进行租车操作！");
			UserBeforeView();
			break;
		case 8:
			System.out.println("请先登录再进行还车操作！");
			UserBeforeView();
			break;
		case 9:
			exitView(null);
			break;
		default:
			System.out.println("没有此功能");
		}

	}

	public void UserLoginView() {
		System.out.println("====================================");
		User userLogin = UserLogin();
		if (userLogin != null) {
			System.out.println("Link Start!");
			if (userLogin.getType().intValue() == 1) {
				av.AdminAfterloginView(userLogin);
			} else {
				UserViewAfterlogin(userLogin);
			}
		} else {
			System.out.println("Login failure....请核对登陆用户名和密码");
			UserLoginView();
		}
	}

	public void UserViewAfterlogin(User user) {
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
		System.out.println("9:返回未登陆界面........");
		int index = IF1to9(user);
		switch (index) {
		case 1:
			Number i = 0;
			UserRegisterView(i);
			break;
		case 2:
			ALLCarView(user);
			break;
		case 3:
			CarByRentView(user);
			break;
		case 4:
			CarByBrandView(user);
			break;
		case 5:
			CarByCategoryView(user);
			break;
		case 6:
			rentCarView(user);
			IFGoBack(user);
			break;
		case 7:
			returnCarView(user);
			break;
		case 8:
			RecordView(user);
			break;
		case 9:
			exitloginAfterView();
			break;
		default:
			System.out.println("没有此功能");
		}
	}

	/**
	 * 判断输入的是否是1~9的功能号标记位
	 * 
	 * @return 返回一个输入的int型整数
	 */
	public int IF1to9(User user) {
		String line = Input.nextLine();
		Quit(line, user);
		int index = 0;
		if (line.matches("^[1-9]$")) {
			index = Integer.parseInt(line);
			return index;
		} else {
			System.out.println("只能输入1到9噢");
			index = IF1to9(user);
		}
		return index;
	}

	/**
	 * 判断输入的是0或1
	 * 
	 * @return 返回一个输入的数字
	 */
	public int IF0or1(User user) {
		String line = Input.nextLine();
		Quit(line, user);
		int index = 0;
		if (line.matches("^[0|1]$")) {
			index = Integer.parseInt(line);
			return index;
		} else {
			System.out.println("只能输入0或者1噢");
			index = IF0or1(user);
		}
		return index;
	}

	/**
	 * 判断输入的是1或2或3或4
	 * 
	 * @return 返回一个输入的数字
	 */
	public int IF1or2or3or4(User user) {
		String line = Input.nextLine();
		Quit(line, user);
		int index = 0;
		if (line.matches("^[1|2|3|4]$")) {
			index = Integer.parseInt(line);
			return index;
		} else {
			System.out.println("只能输入1或者2或者3或者4噢");
			index = IF1or2or3or4(user);
		}
		return index;
	}

	/**
	 * 登陆界面
	 * 
	 * @return 返回一个用户
	 */
	public User UserLogin() {
		System.out.println("请输入用户名：");
		String username = Input.nextLine();
		Quit(username, null);
		System.out.println("请输入密码：");
		String password = Input.nextLine();
		Quit(password, null);
		User login = uc.login(username, password);
		return login;
	}

	/**
	 * 注册界面 如果注册成功会自动将账号切换到新注册的用户中
	 */
	public void UserRegisterView(Number i) {
		System.out.println("====================================");
		System.out.println("--------欢迎进入用户注册页面---------");
		Boolean[] flags = { false, false, true };
		String username = uqUserName();
		String password = null;
		while (!flags[0]) {
			password = IFPassword(flags);
		}
		Number sex = IFSex();
		String id_Number = null;
		id_Number = IFId_Number();
		String tel = null;
		while (!flags[1]) {
			tel = IFTel(flags);
		}
		String addr = IFaddr();
		User user = new User(username, password, sex, id_Number, tel, addr, i);
		boolean register = uc.register(user);
		if (!register) {
			System.out.println("注冊失敗");
		} else {
			System.out.println("注冊成功");
		}
		IFGoBack(user);
	}

	public String IFaddr() {
		System.out.println("请输入你的地址");
		String addr = Input.nextLine();
		Quit(addr, null);
		if (!addr.matches("^[\\u4e00-\\u9fa5]{1,33}$")) {
			System.out.println("地址只能输入汉字噢，长度不能超过33个汉字噢");
			addr = IFaddr();
		}
		return addr;
	}

	/**
	 * 判断用户名是否重复
	 * 
	 * @param flags
	 *            传递一个参数表示是否符合规范要求
	 * @return 返回用户名UserName
	 */
	public String uqUserName() {
		System.out.println("请输入你的用户名");
		String username = Input.nextLine();
		Quit(username, null);
		boolean registerIFUsername = uc.RegisterIFUsername(username);
		if (username.matches("\\s+")) {
			System.out.println("用户名不能设置为空");
			username = uqUserName();
		} else if (username.equals("")) {
			System.out.println("用户名不能设置为空");
			username = uqUserName();
		} else if (!registerIFUsername) {
			System.out.println("该用户名已有人用了，请重新输入你的用户名");
			username = uqUserName();
		} else if (!username.matches("^[\\u4e00-\\u9fa5]{1,13}$|^[\\dA-Za-z_]{1,40}$")) {
			System.out.println("最多设置13个汉字或者40个由数字以及字母组成的字符串");
			username = uqUserName();
		} else {
			System.out.println("该用户名可用");
		}
		return username;
	}

	/**
	 * 判断密码是否正确
	 * 
	 * @param flag
	 *            判断两次密码是否相同
	 * @return 返回一个密码
	 */
	public synchronized String IFPassword(Boolean[] flag) {
		System.out.println("请输入你要设置的密码");
		String password = Input.nextLine();
		Quit(password, null);
		System.out.println("确认密码");
		String Tpassword = Input.nextLine();
		Quit(Tpassword, null);
		if (!password.equals(Tpassword)) {
			System.out.println("两次密码不一致！");
			flag[0] = false;
		} else if (password.matches("\\s+") || password.equals("")) {
			System.out.println("密码不能设置为空");
			flag[0] = false;
		} else if (!password.matches("^[A-Za-z0-9]{6,18}$")) {
			System.out.println("密码长度不能小于6位，超过18位");
			flag[0] = false;
		} else {
			flag[0] = true;
		}
		return password;
	}

	/**
	 * 判断电话是否正确书写
	 * 
	 * @param flags
	 *            判断电话
	 * @return 返回一个电话
	 */
	public String IFTel(Boolean[] flags) {
		System.out.println("请输入你的电话");
		String tels = Input.nextLine();
		Quit(tels, null);
		if (tels.matches("^[1-9][0-9]{10}$")) {
			flags[1] = true;
		} else {
			System.out.println("请正确填写电话号码");
			flags[1] = false;
		}
		return tels;
	}

	/**
	 * 判断身份证是否符合规范
	 * 
	 * @param flag
	 *            判断的值
	 * @return 返回一个身份证号码
	 */
	public synchronized String IFId_Number() {
		System.out.println("请输入你的身份证");
		String id_number = Input.nextLine();
		boolean selectIDNumber = uc.selectIDNumber(id_number);
		Quit(id_number, null);
		if (!id_number.matches("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)")) {
			System.out.println("身份证不符合规范");
			id_number = IFId_Number();
		}else if(selectIDNumber){
			System.out.println("请输入自己的身份证号码！不要妄想输入他人的身份证号码");
			id_number = IFId_Number();
		} else {
			return id_number;
		}
		return id_number;
	}

	/**
	 * 判断性别编号是否符合规范
	 * 
	 * @param flag
	 *            判断符号
	 * @return 返回一个性别编号
	 */
	public synchronized int IFSex() {
		System.out.println("请输入你的性别0:为男生，1为女生");
		int sex = IF0or1(null);
		return sex;
	}

	/**
	 * 用户查看全部可租车辆的界面,并选择是否租车
	 */
	public void ALLCarView(User user) {
		System.out.println("====================================");
		System.out.println("-----------现在租车！更享优惠~~~-----------");
		ArrayList<Car> carlist = uc.selectCar();
		print(carlist);
		IFGoRentView(user);
		IFGoBack(user);
	}

	/**
	 * 判断是否要返回主键面
	 * 
	 * @param user
	 *            返回登录的用户没有则跳转到未登陆界面
	 */
	@SuppressWarnings("static-access")
	public void IFGoBack(User user) {
		System.out.println("====================================");
		System.out.println("返回菜单请输入back");
		String ifback = Input.nextLine();
		Quit(ifback, user);
		if (ifback.equals("back")) {
			if (user != null) {
				if (user.getType().intValue() == 1) {
					av.AdminAfterloginView(user);
				} else {
					UserViewAfterlogin(user);
				}
			} else {
				UserBeforeView();
			}
		} else {
			System.out.println("你这是在做什么？我都搞不懂你在干什么！！叫你输入指定的字符串了！！！");
			System.out.println("那你就在这等着吧，反正我是机器，也无事可干😒😒😒😒");
			int index = 0;
			while (index <= 3) {
				index++;
				try {
					Thread.currentThread().sleep(3000);
					if (index == 1) {
						System.out.println("好了好了再给你一次机会请正确输入字符串");
					} else if (index == 2) {
						System.out.println("都给你一次机会了你怎么不听话！！(╯▔皿▔)╯😡💢");
					} else if (index == 3) {
						System.out.println("气死掉了........");
					}
					String back = Input.nextLine();
					Quit(back, user);
					if (back.equals("back")) {
						if (user != null) {
							if (user.getType().intValue() == 1) {
								av.AdminAfterloginView(user);
							} else {
								UserViewAfterlogin(user);
							}
						} else {
							UserBeforeView();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("好了不管怎么样我还是把你传送回主界面吧");
			if (user != null) {
				if (user.getType().intValue() == 1) {
					av.AdminAfterloginView(user);
				} else {
					UserViewAfterlogin(user);
				}
			} else {
				UserBeforeView();
			}
		}
	}

	/**
	 * 判断是否要进入租车界面
	 * 
	 * @param user
	 */
	@SuppressWarnings("unused")
	public void IFGoRentView(User user) {
		System.out.println("====================================");
		if (user != null) {
			System.out.println("是否要进行租车？是请输入y,否请输入x");
			String flag = Input.nextLine();
			Quit(flag, user);
			if (flag.toLowerCase().equals("y")) {
				rentCarView(user);
			} else if (flag.toLowerCase().equals("x")) {
				if (user != null) {
					if (user.getType().intValue() == 1) {
						av.AdminAfterloginView(user);
					} else {
						UserViewAfterlogin(user);
					}
				} else {
					UserBeforeView();
				}
			} else {
				System.out.println("乱输我也拿你没办法，不过你最好考虑清楚，因为这只有一个选择，租或者不租，也就是y或x");
				IFGoRentView(user);
			}
		}
	}

	/**
	 * 输出方法
	 * 
	 * @param carlist
	 *            传递查询到的汽车集合
	 */
	public void print(ArrayList<Car> carlist) {
		for (Car car : carlist) {
			System.out.println(car.toStrings());
		}
	}

	/**
	 * 按照价格排序的租车界面
	 */
	public void CarByRentView(User user) {
		System.out.println("====================================");
		System.out.println("请输入0或1选择按价格升序排序和降序排序");
		int index = IF0or1(user);
		switch (index) {
		case 0:
			System.out.println("按照一天的租金价格升序排序");
			ArrayList<Car> selectCarByT_priceasc = uc.selectCarByT_priceasc();
			print(selectCarByT_priceasc);
			break;
		case 1:
			System.out.println("按照一天的租金价格降序排序");
			ArrayList<Car> selectCarByT_pricedesc = uc.selectCarByT_pricedesc();
			print(selectCarByT_pricedesc);
			break;
		}
		IFGoRentView(user);
		IFGoBack(user);
	}

	/**
	 * 根据品牌来查询租的车
	 * 
	 * @param user
	 *            有登录就返回登陆用户，没有就不用管
	 */
	public void CarByBrandView(User user) {
		System.out.println("====================================");
		System.out.println("-----------品牌车辆查询-------------");
		System.out.println("请输入你要查询的品牌");
		String brand = Input.nextLine();
		Quit(brand, user);
		ArrayList<Car> selectCarByT_Brand = uc.selectCarByT_Brand(brand);
		if (selectCarByT_Brand == null) {
			System.out.println("不好意思没有这个品牌噢，现在只有");
			ArrayList<String> selectCarByBrandWhereExists = uc.selectCarByBrandWhereExists(null);
			for (String string : selectCarByBrandWhereExists) {
				System.out.println(string + "  \t");
			}
		} else {
			print(selectCarByT_Brand);
		}
		IFGoRentView(user);
		IFGoBack(user);
	}

	/**
	 * 根据类别来查询租的车
	 * 
	 * @param user
	 *            有登录就返回登陆用户，没有就不用管
	 */
	public void CarByCategoryView(User user) {
		System.out.println("====================================");
		System.out.println("-----------类型车辆查询-------------");
		System.out.println("请输入你要查询的类型1:紧凑型，2：舒适型，3：SUV，4：精英型");
		String category = null;
		int index = IF1or2or3or4(user);
		switch (index) {
		case 1:
			category = "紧凑型";
			break;
		case 2:
			category = "舒适型";
			break;
		case 3:
			category = "SUV";
			break;
		case 4:
			category = "精英型";
			break;
		default:
			System.out.println("没有这种类型噢");
			break;
		}
		ArrayList<Car> selectCarByT_Category = uc.selectCarByT_Category(category);
		print(selectCarByT_Category);
		IFGoRentView(user);
		IFGoBack(user);
	}

	/**
	 * 租车界面
	 */
	public void rentCarView(User user) {
		System.out.println("====================================");
		System.out.println("请输入/扫描你需要租的车牌号/车");
		String CarNumber = Input.nextLine();
		Quit(CarNumber, user);
		boolean rent_Car = uc.Rent_Car(user, CarNumber);
		if (rent_Car) {
			System.out.println("租车成功");
		} else {
			System.out.println("租车失败");
		}
	}

	/**
	 * 还车界面
	 */
	public void returnCarView(User user) {
		System.out.println("====================================");
		System.out.println("请输入/扫描你需要还的车牌号/车");
		String CarNumber = Input.nextLine();
		Quit(CarNumber, user);
		boolean return_Car = uc.Return_Car(user, CarNumber);
		if (return_Car) {
			System.out.println("还车成功");
		} else {
			System.out.println("还车失败");
			System.out.println("请确认是否租赁了此车，车牌号是否为："+CarNumber);
		}
		IFGoBack(user);
	}

	public void RecordView(User user) {
		System.out.println("====================================");
		ArrayList<Record> selectRecord = uc.selectRecord(user);
		for (Record record : selectRecord) {
			System.out.println(record.toStrings());
		}
		IFGoBack(user);
	}

	/**
	 * 返回到未登录界面
	 */
	public void exitloginAfterView() {
		System.out.println("====================================");
		UserBeforeView();
	}

	/**
	 * 退出界面
	 */
	public void exitView(User user) {
		System.out.println("====================================");
		System.out.println("你真的要狠心关闭我吗？😭😭😭");
		System.out.println("输入y确认关闭；输入x取消关闭");
		String flag = Input.nextLine();
		if (flag.equalsIgnoreCase("y")) {
			System.exit(0);
		} else {
			System.out.println("嘻嘻，就知道你心疼我，才不舍得关闭我哩😘😘😘");
			if (user != null) {
				if (user.getType().intValue() == 1) {
					av.AdminAfterloginView(user);
				} else {
					UserViewAfterlogin(user);
				}
			} else {
				UserBeforeView();
			}
		}
	}

	/**
	 * 判断输入的字符是否为quit如果是则跳转到退出界面
	 * 
	 * @param s
	 *            从控制台输入的数据
	 */
	public void Quit(String s, User user) {
		if (s.equals("quit")) {
			exitView(user);
		}
	}
}
