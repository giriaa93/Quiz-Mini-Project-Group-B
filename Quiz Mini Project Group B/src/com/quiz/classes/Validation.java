package com.quiz.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.quiz.controller.ConnectionDB;

public class Validation {
	
static ConnectionDB con = new ConnectionDB();
	
	public static boolean checkRollNo(int rollNo) throws ClassNotFoundException, SQLException
	{
		Connection dbConnect = con.getConnection();
		Statement st = dbConnect.createStatement();
		
		String sql = "select * from student_info where roll_no='"+rollNo+"'";
		ResultSet rs = st.executeQuery(sql);
		if(rs.next())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
//=======================Result pass or fail method=======================
	public static String getPassOrFail(String grade)
	{
		if(grade.equals("A") || grade.equals("B") || grade.equals("C"))
			{
				return "PASS";
			}
			else
			{
				return "FAIL";
			}
	}

}
