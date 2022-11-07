package com.quiz.classes;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.quiz.controller.ConnectionDB;

public class Login {
	
	static Scanner scanner = new Scanner(System.in);
	//============================Login for Teacher==========================
		public static void LoginMenu()
		{
			try {
				Connection dbConnect = ConnectionDB.getConnection();
				System.out.println("=============Admin Login==============");
				System.out.println("Enter the Username--");
				String userID = scanner.next();
				System.out.println("Enter the Password--");
				String password = scanner.next();
				String query1 = "select * from Admin_info where user_id='"+ userID +"' and user_password = '"+password+"'";
				Statement st1 = dbConnect.createStatement();
				ResultSet rs1 = st1.executeQuery(query1);
				
				if(rs1.next())
				{
					Menu.adminMenu();
				}
				else
				{
					System.out.println("Incorrect User Name or Password...!!");
					System.out.println("");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	}



