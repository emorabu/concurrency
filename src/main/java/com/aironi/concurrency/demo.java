package com.aironi.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class demo {
	public static void main(String[] args) {

		int[][] a = {
				{1,2,3},
				{3,5,7}
		};
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				if(a[i][j]==2) {
					
				}
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		for (Integer i : list) {
			
		}
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
//		new LinkedHashMap<>().acc
		
		String[] array1 = {"a","b","c"};
        List list1 = Arrays.asList(array1);
        System.out.println(list1.size()); //3
        
        int[] array2 = {1,2,3};
        List list2 = Arrays.asList(array2);
        System.out.println(list2.size()); // 1
        
        List<Object> list3 = new ArrayList<>();
        Collections.addAll(list3, array2);
        System.out.println(list3.size());
        
//       list1.stream().toArray();
      // Stream.of(array2).collect(Collectors.toList());
        List<Integer> list4 = Arrays.stream(array2).boxed().collect(Collectors.toList());
        System.out.println(list4.size()); // 3
	}
	static class C{final boolean a;

	public C(boolean a) {
		super();
		this.a = a;
	} } 
}
