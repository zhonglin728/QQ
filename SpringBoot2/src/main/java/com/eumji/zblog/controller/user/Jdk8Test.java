package com.eumji.zblog.controller.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSONArray;
import com.eumji.zblog.controller.user.vo.Student;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Jdk8Test {
	
 static class Demo{
	    private String name;
	    private  Integer sex;
	    private  String age;

	    public Demo(String age,Integer sex) {
	        this.age =age;
	        this.sex =sex;
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getSex() {
			return sex;
		}

		public void setSex(Integer sex) {
			this.sex = sex;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Demo [name=" + name + ", sex=" + sex + ", age=" + age + "]";
		}
		
		
	
	 }
	
	public static void main(String[] args) {
        List<Student> list =new ArrayList<>();
       Student student1 = new Student();
       student1.setAge("12");student1.setSex(0);
       Student student2 = new Student();
       student2.setAge("13");student2.setSex(2);
       Student student3 = new Student();student3.setAge("11");student3.setSex(1);
       Student student4 = new Student();student4.setAge("18");student4.setSex(1);
       Student student5 = new Student();student5.setAge("18");student5.setSex(0);
       Student student6 = new Student();student6.setAge("18");student6.setSex(2);
       Student student7 = new Student();student7.setAge("18");student7.setSex(2);
       list.add(student1);list.add(student2);list.add(student3);list.add(student4);
        list.add(student5);list.add(student6);list.add(student7);
       List<Demo> demos = new ArrayList<Demo>();
        //Map原始数据
        System.out.println("原始数据 组装list<demo>*******************");
        demos = list.stream().map(student-> new Demo(student.getAge(),student.getSex())).collect(Collectors.toList());
        demos.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //Map 重新包装数据
        System.out.println("MAP多次封装*****************************！");
        list.stream().map(m->{
        	Student s = new Student();
        	if(m.getAge().equals("18")) {
        		s = new Student(m.getAge()+"Map字符串",m.getSex()+100);
        	}else {
        		s = new Student(m.getAge(),m.getSex());
        	}
        	return s;
        }).map(m->{
        	return new Student(m.getAge()+"再次修改",m.getSex()+1000);
        }).forEach(System.out::println);
        
        
        //filter  只取sex为0
        System.out.println("只取sex为0****************");
        List<Demo> demorm =demos.stream().filter(demo -> demo.getSex() == 0).distinct().collect(Collectors.toList());
        demorm.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //筛选年龄大于12岁
        System.out.println("筛选年龄大于12岁的*************");
        List<Demo> demoFilter =  demos.stream().filter(demo -> Integer.valueOf(demo.getAge()) > 12).collect(Collectors.toList());
        demoFilter.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //sorted 排序
        System.out.println("年龄排序******************");
        List<Demo> demoSort = demos.stream().sorted((s1, s2) -> s1.getAge().compareTo(s2.getAge())).collect(Collectors.toList());
        demoSort.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //倒序
        System.out.println("年龄倒序****************");
        ArrayList<Demo> demoArray = (ArrayList<Demo>) demos;
        Comparator<Demo> comparator = (h1, h2) -> h1.getAge().compareTo(h2.getAge());
        demos.sort(comparator.reversed());
        demos.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //多条件正序
        System.out.println("多条件排序正序****************");
        demoArray.sort(Comparator.comparing(Demo::getSex).thenComparing(Demo::getAge));
        demoArray.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //多条件倒序
        System.out.println("多条件排序倒序 sex  倒序****************");
        demoArray.sort(Comparator.comparing(Demo::getSex).reversed().thenComparing(Demo::getAge));
        demoArray.forEach(demo -> {
            System.out.println("年龄 "+demo.getAge() +"  性别 " +demo.getSex()+",");
        });
        //groupingBy 按照年龄分组
        Gson gson = new Gson();
         System.out.println("根据age分组结果为Map****************");
        Map<String, List<Demo>> map = demos.stream().collect(Collectors.groupingBy(Demo::getAge));
        System.out.println(gson.toJson(map));

        //Collectors.toCollection(ArrayList::new) 
        System.out.println("stream转换成toArray,ArrayList集合数据*******************");
        String[] strArray  = Stream.of("hello","world","tom").toArray(String[]::new); 
        System.out.println(gson.toJson(strArray));
        List<String> list2 = Stream.of("hello","world","tom").collect(Collectors.toCollection(ArrayList::new)); 
        System.out.println(gson.toJson(list2));
        
        //Stream.of 合并 list 
        System.out.println("stream of合并集合数据*******************");
        Stream.of(  
               list,demos
            ).flatMap((e) -> e.stream()).forEach(e->System.out.println(e));
        
        //Stream.concat 合并 list 
        System.out.println("stream.concat合并集合数据*******************");
        Stream.concat(list.stream(), demos.stream()).collect(Collectors.toList()).forEach(System.out::println);
        
        // anyMatch 任意一个人匹配就返回  true  
        boolean allMatch = list.stream().anyMatch((s)->s.getAge().equals("12"));  
        System.out.println(allMatch);
        
        //findFirst,Optional 的使用*******************
        System.out.println("findFirst,Optional 的使用*******************");
        Optional<Student> findFirst = list.stream().filter(m->m.getAge().equals("12")).findFirst();
        findFirst.ifPresent(System.out::println);
        if(findFirst.isPresent()) {
        	Student student = findFirst.get();
        	System.out.println(student);
        }
        
        
        
     // reduce 求聚合函数求性别的max和sum值    ->  【 (s->s.getSex()) 可以简写为  (Student::getSex)】
        int max = list.parallelStream().mapToInt(Student::getSex).reduce(0,Integer::max);
        System.out.println(max);
        int sum = list.parallelStream().map(s->s.getSex()).reduce(0,Integer::sum);
        System.out.println(sum);
       
        
        
        
    }
	 
	 

}
