package gouwuche;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class ShoppingCart {
	
	private static HashMap<Product,Integer> products = new HashMap<Product,Integer>();
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("这里是购物车");
		Product p1 = new Product("誓约胜利之剑","阿瓦隆",999,1);
		Product p2 = new Product("乖离剑","巴比伦",785,1);
		Product p3 = new Product("长虹剑","虹猫牌",421,1);
		Product p4 = new Product("村雨","日本",847,1);
		Product p5 = new Product("七宗罪","dragon",189,1);


		
		 
		products.put(p1, p1.getNumber());
		products.put(p2, p2.getNumber());
		products.put(p3, p3.getNumber());
		products.put(p4, p4.getNumber());
		products.put(p5, p5.getNumber());
		 
		
		
		Iterator<Product> it = products.keySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println();
		System.out.println();
		add();
		System.out.println();
		System.out.println();
		delete();
		System.out.println();
		System.out.println();
		change();
		System.out.println();
		System.out.println();
		search();
	}
	public static void add() {
		Product p6 = new Product("七宗罪","dragon",189,1);
		Product p7 = new Product("阿卜杜拉阿巴斯","印度",92,1);
		
		products.put(p6,p6.getNumber());
		products.put(p7,p7.getNumber());
		
		Iterator<Product> it = products.keySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public static void delete() {
		
		System.out.println("请输入删除商品名称：");
		String mz = scan.next();

		Iterator<Product> it = products.keySet().iterator();
		while(it.hasNext()) {
			Product s = it.next();
			if(mz.equals(s.getName())) {
				products.remove(s);
				break;
			}
		}
		
		Iterator<Product> its = products.keySet().iterator();
		while(its.hasNext()) {
			System.out.println(its.next());
		}
	}
	
	public static void change() {
		System.out.println("请输入修改商品名称：");
		String mz = scan.next();
		Iterator<Product> it = products.keySet().iterator();
		while(it.hasNext()) {
			Product s = it.next();
			if(mz.equals(s.getName())) {
				System.out.println("请输入修改商品数量：");
				int sl = scan.nextInt();
				s.setNumber(sl);
				break;
			}
		}
		
		Iterator<Product> its = products.keySet().iterator();
		while(its.hasNext()) {
			System.out.println(its.next());
		}
	}
	
	public static void search() {
		System.out.println("请输入查找商品名称：");
		String mz = scan.next();
		Iterator<Product> it = products.keySet().iterator();
		while(it.hasNext()) {
			Product s = it.next();
			if(mz.equals(s.getName())) {
				System.out.println(s);
				break;
			}
		}
	}
}

