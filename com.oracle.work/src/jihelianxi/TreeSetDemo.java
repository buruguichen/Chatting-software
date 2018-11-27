package jihelianxi;

import java.util.Iterator;
import java.util.TreeSet;

import gouwuche.Product;

public class TreeSetDemo {
	private static TreeSet<Person> pe = new TreeSet<>();
	public static void main(String[] args) {
		Person p1 = new Person("君莫笑",29,"男","杭州");
		Person p2 = new Person("周防尊",25,"男","东京");
		Person p3 = new Person("吴邪",34,"男","长沙");
		Person p4 = new Person("卫宫切嗣",42,"男","京都");
		Person p5 = new Person("古河渚",17,"女","九州");
		
		
		pe.add(p1);
		pe.add(p2);
		pe.add(p3);
		pe.add(p4);
		pe.add(p5);
		
		System.out.println("增加：");
		for(Person x: pe) {
			System.out.println(x);
		}
		
		System.out.println("删除：");
		String mz = "君莫笑";
		Iterator<Person> it = pe.iterator();
		while(it.hasNext()) {
			Person s = it.next();
			if(mz.equals(s.getName())) {
				pe.remove(s);
				break;
			}
		}
		Iterator<Person> its = pe.iterator();
		while(its.hasNext()) {
			System.out.println(its.next());
		}
		
		System.out.println("修改：");
		String mz1 = "吴邪";
		Iterator<Person> it1 = pe.iterator();
		while(it1.hasNext()) {
			Person s = it1.next();
			if(mz1.equals(s.getName())) {
				int newAge = 19;
				s.setAge(newAge);
				break;
			}
		}
		for(Person x: pe) {
			System.out.println(x);
		}

	}
}
