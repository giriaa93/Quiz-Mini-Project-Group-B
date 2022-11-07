package com.quiz.classes;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.quiz.controller.ConnectionDB;

public class GetQuestion {
	

	static ConnectionDB con = new ConnectionDB();
	static Statement st = null;
	static Scanner scanner = new Scanner(System.in);
	static int count = 0;
	
//==============================Getting the Student information==========================================
	
	public static void getStudentInfo()
	{
		try {
			
			System.out.println("-----------Enter student Information--------------");
			System.out.println("Enter Student Roll No");
			int rollNo = scanner.nextInt();
			// Checking quiz already given by student or not
			boolean access = Validation.checkRollNo(rollNo);
			if(access==false)
			{
				System.out.println("Test Already Attended..!! Please Exit from Application..");
			}
			else
			{
				System.out.println("Enter Student First Name");
				String firstName = scanner.next();
				System.out.println("Enter Student Last Name");
				String lastName = scanner.next();
				
				startTest(rollNo,firstName,lastName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//=============================Method for display questions one by one===================================
		public static void startTest(int rollNo, String firstName, String lastName) throws ClassNotFoundException, SQLException {
			
			try {
			Connection dbConnect = con.getConnection();
			st = dbConnect.createStatement();
			ResultSet rs = st.executeQuery("select * from question_info order by rand()");
				int i=1;
			while(rs.next())
			{	
				System.out.println("Q.No "+i+") "+rs.getString(2));
				System.out.println("-------------------------------------------------------------------------------------------");
				
				HashSet<String> hashset = new HashSet<String>();
				
				hashset.add(rs.getString(3));
				hashset.add(rs.getString(4));
				hashset.add(rs.getString(5));
				hashset.add(rs.getString(6));
				
				//System.out.println(hashset);
				
				ArrayList<String> arraylist = new ArrayList<String>();
				arraylist.add("A");
				arraylist.add("B");
				arraylist.add("C");
				arraylist.add("D");
							
				LinkedHashMap<String, String> linkhm = new LinkedHashMap<String,String>();
				
				Iterator<String> itr1 = hashset.iterator();	
				Iterator<String> itr2 = arraylist.iterator();
				
				while(itr1.hasNext())
				{
					while(itr2.hasNext())
					{
						linkhm.put(itr2.next(), itr1.next());
					}
				}
				//System.out.println(linkhm);
				Set<String> set = linkhm.keySet(); // s contain all the keys only.
				for (String j : set) {
					System.out.println(j+") "+linkhm.get(j));
				}	
				System.out.println("Enter your ans==>>");
				String ans = scanner.next();
				boolean result = ans.matches("[a-dA-D]+");
				//System.out.println("result==="+result);

				if (result == false)
				{
						System.out.println("Q.No " + rs.getInt(1) + ") " + rs.getString(2));
						System.out.println("------------------------------------------------------------------------------------------");
						for (String j : set) {
							System.out.println(j + ") " + linkhm.get(j));
						}
						System.out.println("Warning::  You have Only one chance to select Answer between (A-D)...!!");
						ans = scanner.next();
				}
				ans=ans.toUpperCase();
				//System.out.println("Ans key=="+ans);
				String answer = "";
				
				for(String j : set)
				{
					if(j.equals(ans))
					{
						//System.out.println("Matches....!!!");
						answer = linkhm.get(j);
						//System.out.println("Answer==="+answer);
					}
				}
				checkAnswer(linkhm,answer,rs.getInt(1)); // check the answer 
				i++;
				}
			SubmitQuiz.insertData(rollNo, firstName, lastName,count);  // Inserting data after quiz complete
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			finally {
				st.close();
			}
		}
		
	//======================================================Check Answer is correct or Not==========================================================================
		private static void checkAnswer(LinkedHashMap<String, String> linkhm, String answer, int questionNo) throws ClassNotFoundException, SQLException {

			Connection dbConnect = con.getConnection();
			st = dbConnect.createStatement();
			ResultSet rs = st.executeQuery("select correctAnswer from question_info where QuestionNo = '"+questionNo+"'");
			
			if(rs.next())
			{
				String correctAnswer = rs.getString(1);
				if(correctAnswer.equals(answer))
				{
					//System.out.println("Answer is correct...!!");
					count++;
				}
				else
				{
					//System.out.println("Not Correct...!!");
				}
			}		
		}
	}



