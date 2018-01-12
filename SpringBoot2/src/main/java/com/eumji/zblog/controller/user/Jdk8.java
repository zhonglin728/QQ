package com.eumji.zblog.controller.user;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.eumji.zblog.controller.user.vo.Student;

import net.sf.json.JSONArray;

public class Jdk8 {
	
	
	public static void main(String[] args) {
		//1.1、遍历转换为大写：
		List<String> l = new ArrayList<String>();
		l.add("ads");
		l.add("wk");
		l.add("ctb");
		l.add("sdf");
		l.add("ert");
		l.add("cvb");
		l.add("cvb");
		System.out.println("map使用");
		System.out.println("Mao转换大小写的");
		l.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);;
		l.stream().map(s->"Map改变了你的值"+s.toUpperCase()).forEach(System.out::println);
		System.out.println("foreach 循环输出");
		l.forEach(s->{
			System.out.println(s);
			
		});
		System.out.println("filter过滤字符串使用");
		List<String> collect2 = l.stream().filter(s->s.startsWith("c")&&!s.isEmpty())
				.collect(Collectors.toList());
		System.out.println(collect2);
		System.out.println("limit返回前几条数据");
		l.stream().limit(2).forEach(s-> System.out.println(s));
		System.out.println("sort排序");
		l.stream().sorted().forEach(System.out::println);
		System.out.println("distinct去重复");
		l.stream().distinct().forEach(System.out::println);
		System.out.println("Math匹配是否存在集合里面");
		System.out.println(l.stream().anyMatch(s->s.equals("ads")));
		
		
		
		
		//泛型方法
		getTask(l.toArray());
		System.out.println(getTask("字符串", 123.678));
		
		
		System.out.println(transferLongToDate("yyyy-MM-dd",1508466885L));
		
		
		
		//集合方法过滤条件
		List<Student> list = new ArrayList<>();
		list.add(new Student(3, "c", "城"));
		list.add(new Student(4, "d", "的"));
		list.add(new Student(1, "a", "啊"));
		list.add(new Student(2, "b", "吧"));
		List<Student> filter = list.stream().filter(s->s.getId()==4 || s.getName()=="c").collect(Collectors.toList());
		System.out.println( JSONArray.fromObject(filter));
		//集合方法 按照id 字段排序
		List<Student> sorted = list.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
		
		//list.sort((o1,o2)->o1.getId()>o2.getId()?1:(o1.getId));
		System.out.println( JSONArray.fromObject(sorted));
		Field[] declaredFields = com.eumji.zblog.controller.user.Jdk8Test.Student.class.getDeclaredFields();
		System.out.println(declaredFields.length);
		
	}
	
	public static <T,E> E getTask(T t,E e){
		return e;
	}
	public static  <T> void getTask(T ... strings) {
		for (T string : strings) {
			System.out.println("泛型T实例"+string);
		}
	}
	
	
	/**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

}
