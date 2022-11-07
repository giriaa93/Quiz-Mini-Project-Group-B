package com.quiz.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import com.quiz.classes.Validation;
import com.quiz.controller.ConnectionDB;
import com.quiz.exception.InvalidRollNoException;

public class Reports {
	
	static Scanner sc = new Scanner (System.in);
	static ConnectionDB connection = new ConnectionDB();

//==============================Method for getting result of perticular student====================================
	public static void getResultByRollNo() throws ClassNotFoundException, SQLException 
	{	

		System.out.println("Enter the roll number of Student");
		int rollno = sc.nextInt();
		
		try {
			
			Connection con = connection.getConnection();
			
			String query = "select * from student_info where roll_No='"+rollno+"'"; 
			PreparedStatement ps = con.prepareStatement(query);
			   
		    ResultSet rs = 	ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("====Result of Perticular Student====");
				System.out.println("| Roll Number    "+rs.getInt(1));
				System.out.println("| Student Name   "+rs.getString(2)+" "+rs.getString(3));
			    System.out.println("| Total Marks    "+rs.getInt(4)+" Out of 10");
			    System.out.println("| Grade          "+rs.getString(5));
			    System.out.println("Result           "+Validation.getPassOrFail(rs.getString(5)));
			    System.out.println("==========================================");
			    ps.close();
			    con.close();
			} else {
				throw new InvalidRollNoException("Invalid Roll No...!!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//=====================================Method for Descending order result===========================================
	public static void RankingResults() throws SQLException, ClassNotFoundException {
		Connection con = connection.getConnection();	  
		String sql = "select * from student_info order by total_marks desc " ;
              	
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		System.out.println("=====================Result By Rankings=====================");
		System.out.println("| Roll No |        Name         | Total Marks |");
		System.out.println("-----------------------------------------------");
		
		while (rs.next()) {
			
		System.out.println("|    "+rs.getInt(1)+"   |    "+rs.getString(2)+" "+rs.getString(3)+"   |      "+rs.getInt(4)+"      |");
			
		}
		System.out.println("=============================================================");
		}
	
	
	

}
