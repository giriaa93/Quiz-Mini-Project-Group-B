package com.quiz.classes;


import java.io.PrintWriter;
import java.util.Scanner;

public class MainQuiz {

public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		char ch = 0;

		do {
			System.out.println("***********Quiz Application************");
			System.out.println("=================================");
			System.out.println("press 0 - Exit from Application");
			System.out.println("press 1 - Student Section");
			System.out.println("press 2 - Teacher Section");
			System.out.println("=================================");
			System.out.println("Enter your Choice...");
			ch = scanner.next().charAt(0);
			
			switch(ch)
			{
			case '0':
				System.out.println("Succesfully Terminated...");
				break;
				
			case '1':
				Menu.getStudentMenu();
				break;
				
			case '2':
				Login.LoginMenu();
				break;
				
			}
		}while(ch != '0');
	}
}


