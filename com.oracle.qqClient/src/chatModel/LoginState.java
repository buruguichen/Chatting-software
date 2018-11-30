package chatModel;

import java.io.Serializable;

public class LoginState implements Serializable{
	private String RememberState;
	private String AutoLoginState;
	private User remberedUser;
	
	public String getRememberState() {
		return RememberState;
	}
	public void setRememberState(String rememberState) {
		RememberState = rememberState;
	}
	public String getAutoLoginState() {
		return AutoLoginState;
	}
	public void setAutoLoginState(String autoLoginState) {
		AutoLoginState = autoLoginState;
	}
	public User getRemberedUser() {
		return remberedUser;
	}
	public void setRemberedUser(User remberedUser) {
		this.remberedUser = remberedUser;
	}
	
		
}
