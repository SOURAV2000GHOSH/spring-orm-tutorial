package com.spring.orm.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orm.entities.Student;

public class StudentDao {
	private HibernateTemplate hibernateTemplate;

// when we make insert, update & delete operation that time we use @Transactional but at select time no need to use
	// inserting student object
	@Transactional
	public Integer insert(Student student) {
		Student student2=this.hibernateTemplate.get(Student.class, student.getStudentId());
		if(null!=student2) {
			System.out.println("student is already present !");
			return -1;
		}
		Integer save = (Integer) this.hibernateTemplate.save(student);
		System.out.println("inserting new student is successfully completed !");
		return save;
	}

	// getting Student object
//	@Transactional
	public Student getStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		return student;
	}
	//getting all student details
	public List<Student> getAllStudent(){
		return this.hibernateTemplate.loadAll(Student.class);
	}
	
	
	//delete student details
	@Transactional
	public void deleteStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		if(null!=student) {
			this.hibernateTemplate.delete(student);
			System.out.println("deletion of studentNo of "+studentId+" is sucessfully completed !");
		}else {
			System.out.println("student is not present in our record");
		}
	}
	
	//update student
	@Transactional
	public void updateStudent(Student student) {
		Student student2=this.hibernateTemplate.get(Student.class, student.getStudentId());
		if(null==student2) {
			System.out.println("student is not present in our record !");
			return;
		}
		this.hibernateTemplate.update(student);
		System.out.println("updation is successfully happened !");
	}
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

}
