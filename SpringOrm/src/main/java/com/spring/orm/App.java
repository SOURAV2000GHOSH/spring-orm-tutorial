package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Starting Application............");
		AbstractApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		StudentDao dao= context.getBean("studentDao",StudentDao.class);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.println("press 1 for add new student :");
				System.out.println("press 2 for display all student: ");
				System.out.println("press 3 for get details of single student: ");
				System.out.println("press 4 for delete student :");
				System.out.println("press 5 for update student :");
				System.out.println("press 6 for exit");
				int select = Integer.parseInt(sc.readLine());
				switch(select) {
				case 1:
					System.out.println("enter student id: ");
					int id=Integer.parseInt(sc.readLine());
					System.out.println("enter student name: ");
					String name= sc.readLine();
					System.out.println("enter student city: ");
					String city = sc.readLine();
					Student student=new Student(id,name,city);
					dao.insert(student);
					break;
				
					case 2:
						List<Student> students= dao.getAllStudent();
						for(Student student2:students) {
							System.out.println(student2);
						}
						break;
					
					case 3:
						System.out.println("enter student id you want to show: ");
						int sid=sc.read();
						Student student2=dao.getStudent(sid);
						if(null!=student2) {
							System.out.println(student2);
						}else {
							System.out.println("entered student id is not present in our record !");
						}
						break;
					
					case 4:
						System.out.println("enter student id: ");
						int sid1=Integer.parseInt(sc.readLine());
						dao.deleteStudent(sid1);
						break;
						
					case 5:
						System.out.println("enter student id: ");
						int id1=Integer.parseInt(sc.readLine());
						System.out.println("enter student name: ");
						String name1= sc.readLine();
						System.out.println("enter student city: ");
						String city1 = sc.readLine();
						Student student3= new Student(id1, name1, city1);
						dao.updateStudent(student3);
						break;
					case 6:
						context.close();
						System.exit(0);
				default:
					System.out.println("your entered number is wrong ! please try again !");;
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				context.close();
			}

		}

	}
}
