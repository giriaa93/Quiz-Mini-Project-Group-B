package com.quiz.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.quiz.controller.ConnectionDB;

public class SubmitQuiz {
	
public static void insertData(int rollNo, String firstName, String lastName, int count) throws ClassNotFoundException, SQLException {
		
		ConnectionDB con = new ConnectionDB();
		Connection dbConnect = con.getConnection();
		String grade = getGrade(count);
		String sql = "INSERT INTO `quiz`.`student_info` (`roll_no`, `first_name`, `last_name`, `total_marks`, `grade`) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = dbConnect.prepareStatement(sql);
		pstmt.setInt(1, rollNo);
		pstmt.setString(2, firstName);
		pstmt.setString(3, lastName);
		pstmt.setInt(4, count);
		pstmt.setString(5, grade);
		int  i = pstmt.executeUpdate();
		
		if(i==1)
		{
			System.out.println("Test Submitted Successfully...!!");
		}
		else
		{
			System.out.println("Test not Submitted..!!");
		}
	}
//===========================================Get Grade====================================

	private static String getGrade(int count) {
	
		if(count>=8 && count<=10)
		{
			return "A";
		}
		else if(count>=6 && count<=7)
		{
			return "B";
		}
		else if(count==5)
		{
			return "C";
		}
		else
		{
			return "D";
		}
	}

}
