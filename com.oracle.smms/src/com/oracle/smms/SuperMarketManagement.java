package com.oracle.smms;

import com.oraclesdp.common.SystemIn;

/**
 * 
 * @author sword
 *这是一个超市管理系统
 */
public class SuperMarketManagement {
	
	//这是预设的超市管理员账号和密码
	static String adminUsername = "Ruler";
	static String adminPassword = "2016117356";
	
	//这是在用户名或密码输入错误时判断输入次数的数
	static int inputTime = 0;
	
	public static void main(String[] args) {
		showWelcome();
	}
	/**
	 * 这是进入系统的开始界面
	 */
	public static void showWelcome() {
		System.out.println("**************************");
		System.out.println("*****迦勒底超市管理系统*****");
		System.out.println("**************************");
		System.out.println();
		System.out.println("        1.登录");
		System.out.println("        2.退出");
		System.out.println();
		choiceMenu();
	}
	/**
	 * 这是用户选择登录或退出的界面
	 */
	public static void choiceMenu() {
		System.out.println("请输入您的选择：");
		int yourChoiceMenuId = SystemIn.nextInt();
		if(yourChoiceMenuId == 1) {
			login();
		}
		else if(yourChoiceMenuId == 2) {
			System.exit(0);
		}
		else {
			System.out.println("您的输入有误，请重新输入！");
			choiceMenu();
		}
			
		
	}
	
	/**
	 * 这是用户选择登录后输入用户名与密码的界面
	 * 当三次用户名或密码输入不正确时，将强制退出系统
	 */
	public static void login() {
		
		System.out.println("请输入管理员账号：");
		String username = SystemIn.nextLine();
		System.out.println("请输入管理员密码：");
		String password = SystemIn.nextLine();
		
		if(username.equals(adminUsername) && password.equals(adminPassword)) {
			System.out.println("登录成功！");
			showMainMenu();
		}
		else {
			inputTime++;
			if(inputTime == 3) {
				System.out.println("用户名或密码错误！强制退出系统！");
				System.exit(0);
			}
			System.err.println("您输入的用户名或密码有误！"
					+ "您还可以尝试输入"+ (3-inputTime) + "次。");
			
			login();
		}
	}
	
	/**
	 * 展示超市管理系统的各项功能
	 */
	public static void showMainMenu() {
		System.out.println("333");
	}
}
