package com.rentCar.User.entry;

/**
 * 普通用户信息
 * 
 * @author HPK
 *
 */
public class User {
	private Number id; // 用户id 11位
	private String username; // 用户名 40个字节
	private String password; // 密码 20个字节
	private Number sex; // 性别 1字节 0：为男生 1：为女生
	private String id_number; // 身份证18个字节
	private String tel; // 电话 11个字节
	private String addr; // 地址 100个字节
	private Number type; // 用户类型 1个字节 锁定0为：普通用户，不需要其他人设置。

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Number getSex() {
		return sex;
	}

	public void setSex(Number sex) {
		this.sex = sex;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Number getType() {
		return type;
	}

	public String toStringU() {
				return "用户名:" + username + "  \t密码:" + password + "    \t\t性别:" + (sex.intValue()==0?"男":"女") + "  \t\t身份证:" + (id_number == null ? "无" : id_number) + "  \t\t电话:"
						+ (tel == null ? "无" : tel) + "\t地址:" + (addr == null ? "无" : addr) + "\t用户类型:" + (type.intValue() == 0?"普通用户":"管理员");
	}

	// 不需要setType的方法以防其他用户改变管理员用户类型。
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", id_number=" + id_number + ", tel=" + tel + ", addr=" + addr + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_number == null) ? 0 : id_number.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_number == null) {
			if (other.id_number != null)
				return false;
		} else if (!id_number.equals(other.id_number))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@SuppressWarnings("static-access")
	public User(Number id, String username, String password, Number sex, String id_number, String tel, String addr,
			Number type) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.id_number = id_number;
		this.tel = tel;
		this.addr = addr;
		this.type = type;
	}

	public User(String username, String password, Number sex, String id_number, String tel, String addr, Number type) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.id_number = id_number;
		this.tel = tel;
		this.addr = addr;
		this.type = type;
	}

	public User() {
	}

}
