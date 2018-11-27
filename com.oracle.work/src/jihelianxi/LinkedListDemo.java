package jihelianxi;

import java.util.*;

public class LinkedListDemo {
	
	private static LinkedList<String> link = new LinkedList<>();
	
	public static void main(String[] args) {
		link.add("将进酒");
		link.add("杯莫停");
		link.add("与君歌一曲");
		link.add("请君为我倾耳听");
		
		System.out.println("增加：");
		for(int i=0; i<link.size(); i++) {
			System.out.println(link.get(i));
		}
		
		System.out.println("减少：");
		link.remove(2);
		for(String x: link) {
			System.out.println(x);
		}
		
		System.out.println("更改：");
		link.set(2,"乘风破浪会有时");
		Iterator it = link.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println("查找第二元素：");
		System.out.println(link.get(1));
		System.out.println("查找首元素：");
		System.out.println(link.getFirst());
		System.out.println("查找尾元素：");
		System.out.println(link.getLast());
		
		System.out.println("排序：");
		Collections.sort(link);
		for(String x: link) {
			System.out.println(x);
		}
	}
}
