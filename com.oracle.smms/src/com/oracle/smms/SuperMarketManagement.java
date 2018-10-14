package com.oracle.smms;

import com.oraclesdp.common.SystemIn;

/**
 * 
 * @author sword
 *这是一个超市管理系统
 */
public class SuperMarketManagement {
	//这是存储商品数据的四个数组
	static int[] commodityNumber = new int[50];
	static String[] commodityName = new String[50];
	static float[] commodityPrice = new float[50];
	static int[] commodityAmount = new int[50];
	
	//这是新添加商品时的加入位置
	static int pos = 4;
	
	//仓库存储商品的容量
	static final int commodityMax = commodityNumber.length;
	
	//这是预设的超市管理员账号和密码
	static String adminUsername = "Ruler";
	static String adminPassword = "2016117356";
	
	//这是在用户名或密码输入错误时判断输入次数的数
	static int inputTime = 0;
	
	public static void main(String[] args) {
		
//		这是预设的七个商品信息
		commodityNumber[1] = 1;		commodityName[1] = "saber";
		commodityPrice[1] = 9.9f;	commodityAmount[1] = 1;
		commodityNumber[2] = 2;		commodityName[2] = "lancer";
		commodityPrice[2] = 3.6f;	commodityAmount[2] = 78;
		commodityNumber[3] = 7;		commodityName[3] = "berserker";
		commodityPrice[3] = 8.7f;	commodityAmount[3] = 8;
		commodityNumber[0] = 5;		commodityName[0] = "caster";
		commodityPrice[0] = 4.3f;	commodityAmount[0] = 5;
		
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
			System.out.println();
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
		System.out.println("1.商品管理");
		System.out.println("2.退出");
		System.out.println();
		System.out.println("请输入您的选择：");
		int ChoiceMainMenuId = SystemIn.nextInt();
		if(ChoiceMainMenuId == 1) {
			commodityMenu();
		}
		else if(ChoiceMainMenuId == 2) {
			System.exit(0);
		}
		else {
			System.out.println("您的输入有误，请重新输入！");
			showMainMenu();
		}
	}
	
	/*
	 * 商品管理的子菜单项
	 */
	public static void commodityMenu() {
		System.out.println("1.添加商品");
		System.out.println("2.删除商品");
		System.out.println("3.修改商品");
		System.out.println("4.查看商品");
		System.out.println("5.查找商品");
		System.out.println("6.返回上页");
		System.out.println("7.退出");
		
		choiceCommodityMenu();
	}
	
	public static void choiceCommodityMenu() {
		System.out.println("请输入您的选择：");
		int ChoiceCommodityMenuId = SystemIn.nextInt();
		System.out.println();
		switch(ChoiceCommodityMenuId){
			case 1:
			{
				addCommodity();
				break;
			}
			case 2:
			{
				deleteCommodity();
				break;
			}
			case 3:
			{
				updateCommodity();
				break;
			}
			case 4:
			{
				showCommodity();
				break;
			}
			case 5:
			{
				searchCommodity();
				break;
			}
			case 6:
			{
				showMainMenu();
			}
			case 7:
			{
				System.exit(0);
			}
			default:
			{
				System.out.println("您的输入有误，请重新输入！");
				choiceCommodityMenu();
				break;
			}
		}
	}
	
	
//	 这是添加商品的方法
	public static void addCommodity() {
		if(pos == commodityMax) {
			System.out.println("仓库存储商品已达容量上限，无法添加商品！");
		}
		else {
			inputNumber();
			inputName();
			System.out.println("请输入添加商品的价格：");
			float jg = SystemIn.nextFloat();
			commodityPrice[pos] = jg;
			System.out.println("请输入添加商品的数量：");
			int sl = SystemIn.nextInt();
			commodityAmount[pos] = sl;
			
			System.out.println("商品编号     商品名称     "
					+ "商品价格     商品数量");
			System.out.println(commodityNumber[pos]+"        "+commodityName[pos]+"        "
							+commodityPrice[pos]+"        "+commodityAmount[pos]);
			
			//将添加商品的位置后移一位
				pos ++;
		}		
		commodityChoice();
	}
	
	
//	  这是删除商品的方法
	public static void deleteCommodity() {
		if(pos == 0) {
			System.out.println("仓库中没有商品存放，无法删除！");
			commodityChoice();
		}
		else {
			System.out.println("请输入删除商品的编号：");
			int getBh = SystemIn.nextInt();
			
			for(int i=0;i<pos;i++) {
				if(commodityNumber[i] == getBh) {
					for(int j=i+1;j<pos;j++) {
						commodityNumber[j-1] = commodityNumber[j];
						commodityName[j-1] = commodityName[j];
						commodityPrice[j-1] = commodityPrice[j];
						commodityAmount[j-1] = commodityAmount[j];
					}	
					pos = pos - 1;
					System.out.println("已成功删除该商品。");
					break;
				}
				else if(commodityNumber[i] != getBh && i == pos-1) {
					System.out.println("未检索到所删除商品的编号，请重新输入！");
					deleteCommodity();
				}
			}
			commodityChoice();
		}
	}
	
//	这是更改商品的方法
	public static void updateCommodity() {
		if(pos == 0) {
			System.out.println("仓库中没有商品存放，无法更改！");
			commodityChoice();
		}
		else {
			System.out.println("请输入更改商品的编号：");
			int getBh = SystemIn.nextInt();
			
			for(int i=0;i<pos;i++) {
				if(commodityNumber[i] == getBh) {
					System.out.println("要更改的商品信息为：");
					System.out.println(commodityNumber[i]+"        "+commodityName[i]+"        "
							+commodityPrice[i]+"        "+commodityAmount[i]);	
					
					choiceUpdateCommodity(i);
					break;
				}
				else if(commodityNumber[i] != getBh && i == pos-1) {
					System.out.println("未检索到所要更改商品的编号，请重新输入！");
					updateCommodity();
				}
			}
			
		}
		
	}
	
//	这是展示商品的方法
	public static void showCommodity() {
		if(pos == 0) {
			System.out.println("仓库中没有商品存放");
		}
		else {
			System.out.println("商品编号     商品名称     "
					+ "商品价格     商品数量");
			for(int i=0;i<pos;i++) {
				System.out.println(commodityNumber[i]+"        "+commodityName[i]+"        "
								+commodityPrice[i]+"        "+commodityAmount[i]);
			}
		}
		commodityChoice();
	}
	
//  这是查找商品的方法
	public static void searchCommodity() {
		
		if(pos == 0) {
			System.out.println("仓库中没有商品存放，无法查找！");
			System.out.println();
			commodityMenu();
		}
		else {
			System.out.println("1.全名查找");
			System.out.println("2.价格区间查找");
			System.out.println("3.模糊查找");
			System.out.println("4.返回商品管理菜单");
			System.out.println("5.退出");
			System.out.println("请输入您的选择：");
			int bh = SystemIn.nextInt();
			if(bh == 1) {
				nameSearch();
			}
			else if(bh == 2) {
				priceRangeSearch();
			}
			else if(bh == 3) {
				fuzzySearch();
			}
			else if(bh == 4) {
				System.out.println();
				commodityMenu();
			}
			else if(bh == 5) {
				System.exit(0);;
			}
			else {
				System.out.println("您的输入有误。请重新输入！");
				searchCommodity();
			}
			
		}
	}

//  这是添加商品时写入商品编号并判断写入的商品编号是否重复的方法
	public static void inputNumber() {
		System.out.println("请输入添加商品的编号：");
		int bh = SystemIn.nextInt();
		if(pos == 0) {
			commodityNumber[pos] = bh;
		}
		else {
			for(int i=0;i<pos;i++) {
				if(commodityNumber[i] == bh) {
					System.out.println("您输入的编号与已存储的商品编号重复！请重新输入！");
					inputNumber();
				}
				else if(commodityNumber[i] != bh && i == pos-1) {
					commodityNumber[pos] = bh;
				}
			}
		}	
	}

//	这是添加商品时写入商品名称并判断写入的商品名称是否重复的方法
	public static void inputName() {
		System.out.println("请输入添加商品的名称：");
		String mc = SystemIn.nextLine();
		if(pos == 0) {
			commodityName[pos] = mc;
		}
		else {
			for(int i=0;i<pos;i++) {
				if(commodityName[i].equals(mc)) {
					System.out.println("您输入的名称与已存储的商品名称重复！请重新输入！");
					inputName();
				}
				else if(i == pos-1) {
					commodityName[pos] = mc;
				}
			}
		}	
	}
	
//	这是更改商品详细属性的功能
	public static void choiceUpdateCommodity(int i) {
		System.out.println("请选择更改商品的详细属性：");
		System.out.println("1.编号    2.名称    3.价格    4.数量    5.全部更改    6.返回商品管理菜单");
		System.out.println("请输入您的选择：");
		int ChoiceUpdate = SystemIn.nextInt();
		if(ChoiceUpdate == 1) {
			changeNumber(i);
			System.out.println("已成功更改该商品。");
			repeatChoiceUpdate(i);
		}
		else if(ChoiceUpdate == 2) {
			changeName(i);
			System.out.println("已成功更改该商品。");
			repeatChoiceUpdate(i);
		}
		else if(ChoiceUpdate == 3) {
			System.out.println("请输入新的价格：");
			float newJg = SystemIn.nextFloat();
			commodityPrice[i] = newJg;
			System.out.println("已成功更改该商品。");
			repeatChoiceUpdate(i);
		}
		else if(ChoiceUpdate == 4) {
			System.out.println("请输入新的数量：");
			int newSl = SystemIn.nextInt();
			commodityAmount[i] = newSl;
			System.out.println("已成功更改该商品。");
			repeatChoiceUpdate(i);
		}
		else if(ChoiceUpdate == 5) {
			changeNumber(i);
			changeName(i);
			System.out.println("请输入新的价格：");
			float newJg = SystemIn.nextFloat();
			commodityPrice[i] = newJg;
			System.out.println("请输入新的数量：");
			int newSl = SystemIn.nextInt();
			commodityAmount[i] = newSl;
			System.out.println("已成功更改该商品。");
			repeatChoiceUpdate(i);
		}
		else if(ChoiceUpdate == 6) {
			System.out.println();
			commodityMenu();
		}
		else {
			System.out.println("您的输入有误。请重新输入！");
			choiceUpdateCommodity(i);
		}
	}

//	这是更改商品编号时写入编号并判断编号是否重复的方法
	public static void changeNumber(int i) {
		System.out.println("请输入新的编号：");
		int newBh = SystemIn.nextInt();
	 
			for(int q=0;q<pos;q++) {
				if(commodityNumber[q] == newBh) {
					System.out.println("您输入的编号与已存储的商品编号重复！请重新输入！");
					changeNumber(i);
					break;//???
				}
				else if(commodityNumber[q] != newBh && q == pos-1) {
					commodityNumber[i] = newBh;
				}
			}	
	}
	
//	这是更改商品名称时写入名称并判断名称是否重复的方法
	public static void changeName(int i) {
		System.out.println("请输入新的名称：");
		String newMc = SystemIn.nextLine();

			for(int q=0;q<pos;q++) {
				if(commodityName[q].equals(newMc)) {
					System.out.println("您输入的名称与已存储的商品名称重复！请重新输入！");
					changeName(i);
					break;
				}
				else if(q == pos-1) {
					commodityName[i] = newMc;
				}
			}	
	}
	
//	这是查找商品功能里的全名查找功能
	public static void nameSearch() {
		System.out.println();
		System.out.println("请输入要查找的商品名：");
		String spm = SystemIn.nextLine();
		for(int i=0;i<pos;i++) {
			if(commodityName[i].equals(spm)) {
				System.out.println("查询到的商品信息为：");
				System.out.println();
				System.out.println("商品编号     商品名称     "
						+ "商品价格     商品数量");
				System.out.println(commodityNumber[i]+"        "+commodityName[i]+"        "
									+commodityPrice[i]+"        "+commodityAmount[i]);
				break;
			}
			else if(i == pos-1) 
				System.out.println("未查询到该商品。");
		}
		repeatChoiceSearch(1);
	}
	
//	这是查找商品功能里的价格区间查找功能
	public static void priceRangeSearch() {
		System.out.println();
		System.out.println("请输入要查找的商品价格区间，价格最小值为：");
		float jgMin = SystemIn.nextFloat();
		System.out.println("价格最大值为：");
		float jgMax = SystemIn.nextFloat();
		System.out.println("查询到的商品信息为：");
		System.out.println();
		System.out.println("商品编号     商品名称     "
				+ "商品价格     商品数量");
		for(int i=0;i<pos;i++) {
			if(commodityPrice[i]<jgMax && commodityPrice[i]>jgMin) {
				System.out.println(commodityNumber[i]+"        "+commodityName[i]+"        "
									+commodityPrice[i]+"        "+commodityAmount[i]);
			}
			else if(i == pos-1) 
				System.out.println("未查询到在该价格区间的商品。");
		}
		repeatChoiceSearch(2);
	}
	
//	这是查找商品功能里的模糊查找功能
	public static void fuzzySearch() {
		System.out.println("请输入字符：");
		String mhm = SystemIn.nextLine();
		System.out.println("查询到的商品信息为：");
		for(int i=0;i<pos;i++) {
			if(i<pos) {
				String[] mzzu = commodityName[i].split("");
				
				for(int j=0;j<mzzu.length;j++) {
					if(mzzu[j].equals(mhm)) {
						System.out.println(commodityNumber[i]+"        "+commodityName[i]+"        "
											+commodityPrice[i]+"        "+commodityAmount[i]);
						break;
					}
				}
			}
			
//			else if(i == pos-1) 
//				System.out.println("未查询到字符匹配的商品。");
		}
		repeatChoiceSearch(3);
	}
	
//	这是更改完商品属性后选择是否继续更改的功能
	public static void repeatChoiceUpdate(int i) {
		System.out.println();
		System.out.println("1.继续更改");
		System.out.println("2.返回商品管理菜单");
		System.out.println("请选择：");
		int ChoiceUpdate = SystemIn.nextInt();
		if(ChoiceUpdate == 1) {
			System.out.println();
			choiceUpdateCommodity(i);
		}
		else if(ChoiceUpdate == 2) {
			System.out.println();
			commodityMenu();
		}
		else {
			System.out.println("您的输入有误。请重新输入！");
			repeatChoiceUpdate(i);
		}
	}

//	这是每次查询商品后，选择继续查询还是返回商品管理菜单或退出的功能
	public static void repeatChoiceSearch(int a) {
		System.out.println();
		System.out.println("1.继续查找");
		System.out.println("2.返回商品查询菜单");
		System.out.println("3.返回商品管理菜单");
		System.out.println("请选择：");
		int ChoiceSearch = SystemIn.nextInt();
		if(ChoiceSearch == 1) {
			System.out.println();
			if(a == 1) {
				nameSearch();
			}
			if(a == 2) {
				priceRangeSearch();
			}
			if(a == 3) {
				fuzzySearch();
			}
		}
		else if(ChoiceSearch == 2) {
			System.out.println();
			searchCommodity();
		}
		else if(ChoiceSearch == 3) {
			System.out.println();
			commodityMenu();
		}
		else {
			System.out.println("您的输入有误。请重新输入！");
			repeatChoiceSearch(a);
		}
	}
	
//	这是每次对商品操作后，选择返回商品管理菜单还是退出的功能
	public static void commodityChoice() {
		System.out.println();
		System.out.println("1.返回商品管理菜单");
		System.out.println("2.退出");
		System.out.println("请选择：");
		int choice = SystemIn.nextInt();
		if(choice == 1) {
			System.out.println();
			commodityMenu();
		}
		else if(choice == 2) {
			System.exit(0);
		}
		else {
			System.out.println("您的输入有误。请重新输入！");
			commodityChoice();
		}
	}
}
