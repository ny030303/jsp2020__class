package movieMVC2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class map1 {
	public static void main(String[] args) {
		// 배열 -> index 0 1 2 "철수"
		// {name : james, age : 10}
		Map<String, Integer> list = new HashMap<>();
		ArrayList<Integer> numList = new ArrayList<Integer>();
		
		numList.add(10);
		numList.add(20);
		System.out.println(numList.get(0));
		
		list.put("철수", 10);
		list.put("영희", 20);
//		System.out.println(list.get("철수"), + list.get("영희"));
	}
}
