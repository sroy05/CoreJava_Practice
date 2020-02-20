package com.stream.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeDemo {
	
	public static void main(String [] args) {
		
		List<Integer> strList=new ArrayList<Integer>();
		Employee[] arrayOfEmployee= {
				new Employee(1,"Xyz",120), new Employee(2,"ABC",2000),new Employee(3,"BCD",300)
		};
		
		List<Employee> empList=Arrays.asList(arrayOfEmployee);

	       Collections.sort(empList,(p1,p2)->{  
	           return p1.getSalary()-(p2.getSalary());  
	           });
	       
	    Stream<Employee> empStream=empList.stream();
	    empStream.forEach(e -> System.out.println(e.getName() + "--"+e.getSalary()));
	    System.out.println("**Sort by Name**");
	    empList.sort(Comparator.comparing(e -> e.getName()));
	    Stream<Employee> empStream1=empList.stream();
	    empStream1.forEach(e -> System.out.println(e.getName() + "--"+e.getSalary()));
		empList.forEach(e->strList.add(e.getSalary()));
//		Stream.of(arrayOfEmployee).forEach(employee ->System.out.println(employee.getName()));;
//		strList.forEach(System.out::println);
		
		List<String> stringList = new ArrayList<String>();

		stringList.add("One flew over the cuckoo's nest");
		stringList.add("To kill a muckingbird");
		stringList.add("Gone with the wind");

		Stream<String> stream = stringList.stream();

		stream.flatMap((value) -> {
		    String[] split = value.split(" ");
		    return (Stream<String>) Arrays.asList(split).stream();
		})
		.forEach((value) -> System.out.println(value))
		;
		String [] ar= {"Accenture","India","PVT","LTD"};
		Stream<String> arrString=Arrays.stream(ar);
		
		List<String> result=arrString.sorted().collect(Collectors.toList());
		result.forEach(System.out::println);
		
	}

}
