package jihelianxi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListDemo {
	private static ArrayList<String> zero = new ArrayList<>();
	public static void main(String[] args) {
		zero.add("saber");
		zero.add("lancer");
		zero.add("archer");
		zero.add("rider");
		zero.add("caster");
		zero.add("assassion");
		zero.add("berserker");
		
		System.out.println("增加：");
		for(int i=0; i<zero.size(); i++) {
			System.out.println(zero.get(i));
		}
		
		System.out.println("减少：");
		zero.remove("berserker");
		for(String x: zero) {
			System.out.println(x);
		}
		
		System.out.println("更改：");
		zero.set(0,"ruler");
		Iterator it = zero.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println("查找：");
		System.out.println(zero.get(3));
		
		System.out.println("排序：");
		Collections.sort(zero);
		for(String x: zero) {
			System.out.println(x);
		}
	}
}
