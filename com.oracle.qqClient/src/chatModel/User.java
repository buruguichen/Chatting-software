package chatModel;

import java.io.Serializable;

public class User implements Serializable{
	private long accountNumber;
	private String nickname;
	private String password;
	private String sex;
	private String age;
	private String signature;
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public User() {
		super();
	}
	public User(String nickname, String password, String sex, String age, String signature) {
		super();

		this.nickname = nickname;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.signature = signature;
	}
	public User(long accountNumber, String password) {
		super();
		this.accountNumber = accountNumber;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [accountNumber=" + accountNumber + ", nickname=" + nickname + ", password=" + password + ", sex="
				+ sex + ", age=" + age + ", signature=" + signature + "]";
	}
	
}
