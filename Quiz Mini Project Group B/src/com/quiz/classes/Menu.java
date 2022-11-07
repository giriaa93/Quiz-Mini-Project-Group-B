package com.quiz.classes;


import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.reports.Reports;

public class Menu {
	
	static Scanner scanner = new Scanner(System.in);
	
	//=================================Student Menu======================================================
		public static void getStudentMenu() {
			char choice;
			do {
				System.out.println("=========Student Menu=================");
				System.out.println(" 0 - Exit                           ||");
				System.out.println(" 1 - Start Quiz                     ||");
				System.out.println("=====================================");
				System.out.println("Enter Your Choice");
				choice = scanner.next().charAt(0);
				
				switch(choice) {
				case '0':
					break;
				case '1' :
					GetQuestion.getStudentInfo();
					break;
				}
			} while (choice!='0');
		}
	//==============================================Admin Menu Method=========================================================	
		public static void adminMenu() throws ClassNotFoundException, SQLException
		{
			char choice;
			do {
				System.out.println("============Admin Menu==============");
				System.out.println(" 0 - Exit                           ");
				System.out.println(" 1 - Result of Perticular Student");
				System.out.println(" 2 - Ranking list");
				System.out.println(" 3 - Question and Answer Of perticular Student");
				System.out.println("=====================================");
				System.out.println("Enter Your Choice");
				choice = scanner.next().charAt(0);
				
				switch(choice) {
				
				case '0' :
					break;
					
				case '1' :
					Reports.getResultByRollNo();
					break;
					
				case '2' :
					Reports.RankingResults();
					break;				
				}
			} while (choice!='0');
		}
		

}
