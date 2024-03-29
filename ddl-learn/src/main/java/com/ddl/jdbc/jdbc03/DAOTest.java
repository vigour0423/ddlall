package com.ddl.jdbc.jdbc03;

import com.ddl.jdbc.Student;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class DAOTest {

	DAO dao = new DAO();
	
	@Test
	public void testUpdate() {
		String sql = "INSERT INTO customers(name, " +
				"email, birth) VALUES(?,?,?)";
		dao.update(sql, "XiaoMing", "xiaoming@atguigu.com", 
				new Date(new java.util.Date().getTime()));
	}

	@Test
	public void testGet() {
		String sql = "SELECT flow_id flowId, type, exam_card examCard, " +
				"id_card idCard, student_name studentName, location, " +
				"grade FROM examstudent WHERE flow_id = ?";
		Student student = dao.get(Student.class, sql, 5);
		
		System.out.println(student); 
	}

	@Test
	public void testGetForList() {
		String sql = "SELECT flow_id flowId, type, exam_card examCard, " +
				"id_card idCard, student_name studentName, location, " +
				"grade FROM examstudent";
		
		List<Student> students = dao.getForList(Student.class, sql);
		System.out.println(students); 
	}

	@Test
	public void testGetForValue() {
		String sql = "SELECT exam_card FROM examstudent " +
				"WHERE flow_id = ?";
		String examCard = dao.getForValue(sql, 5);
		System.out.println(examCard); 
		
		sql = "SELECT max(grade) FROM examstudent";
		int grade = dao.getForValue(sql);
		System.out.println(grade); 
	}

}
