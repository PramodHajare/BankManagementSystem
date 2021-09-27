package com.bank;

import java.util.Scanner;

import com.dao.UserDao;
import com.model.User;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ch = 0;
		do {
			System.out.println("----------------------------------------------------");
			System.out.println("---1. New User");
			System.out.println("---2. Existing User");
			System.out.println("---3. Exit");
			System.out.print("Enter your Choice=");
			ch = sc.nextInt();
			switch (ch) {
			case 1: {
				System.out.println("----------------------------------------------------");
				System.out.println("---1. Create Account");
				System.out.println("---2. Exit");
				System.out.print("Enter your Choice=");
				ch = sc.nextInt();
				switch (ch) {
				case 1: {
					System.out.print("Enter Your Name:");
					User u = new User();
					u.setUseracname(sc.next());
					System.out.print("Enter Your Account Type:");
					u.setUseractype(sc.next());
					System.out.print("Enter Your Opening Balance:");
					u.setUseracbalance(sc.nextDouble());
					int a[] = new UserDao().createAccount(u);
					System.out.println("Your account created with " + a[0]);
					System.out.println("Your A/c Pin=" + a[1]);
				}
				}
				break;
			}
			case 2: {
				System.out.println("----------------------------------------------------");
				System.out.println("---1. Deposite");
				System.out.println("---2. Withdrawl");
				System.out.println("---3. Check Balance");
				System.out.println("---4. Account Details");
				System.out.println("---5. Change Pin");
				System.out.println("---6. Exit");
				System.out.print("Enter your Choice=");
				ch = sc.nextInt();
				switch (ch) {
				case 1: {
					System.out.println("-------------------------------------------------");
					System.out.print("Enter your a/c number=");
					int acno = sc.nextInt();
					System.out.print("Enter your a/c Pin=");
					int acpin = sc.nextInt();
					System.out.print("Enter your amount=");
					double balance = sc.nextDouble();
					if (new UserDao().updateBalance(acno, acpin,
							balance + new UserDao().checkBalance(acno, acpin)) > 0) {
						System.out.println(balance + " deposited successfully....");
					}

					break;
				}
				case 2: {
					System.out.println("-------------------------------------------------");
					System.out.print("Enter your a/c number=");
					int acno = sc.nextInt();
					System.out.print("Enter your a/c Pin=");
					int acpin = sc.nextInt();
					System.out.print("Enter your amount=");
					double balance = sc.nextDouble();
					if (balance > new UserDao().checkBalance(acno, acpin)
							&& (new UserDao().checkBalance(acno, acpin) > 0)) {
						System.out.println("Insufficient Balance!!!!");
					} else {
						if (new UserDao().updateBalance(acno, acpin,
								new UserDao().checkBalance(acno, acpin) - balance) > 0) {
							System.out.println(balance + " Withdrawl successfully....");
						} else {
							System.out.println("Invalid A/c or Pin Number");
						}
					}
					break;
				}
				case 3: {
					System.out.print("Enter your a/c number=");
					int acno = sc.nextInt();
					System.out.print("Enter your a/c Pin=");
					int acpin = sc.nextInt();
					double d = new UserDao().checkBalance(acno, acpin);
					if (d == 0) {
						System.out.println("Invalid a/c number or pin");
					} else {
						System.out.println("Your Current Balance is " + d);
					}
					break;
				}
				case 4: {
					System.out.print("Enter your a/c number=");
					int acno = sc.nextInt();
					System.out.print("Enter your a/c Pin=");
					int acpin = sc.nextInt();
					System.out.println("Ac/Number\tName\tType\tBalance");
					System.out.println(new UserDao().accountDetails(acno, acpin));
					break;
				}
				case 5: {
					System.out.print("Enter your a/c number=");
					int acno = sc.nextInt();
					System.out.print("Enter your old a/c Pin=");
					int oldpin = sc.nextInt();
					System.out.print("Enter your new a/c Pin=");
					int newpin = sc.nextInt();
					if(new UserDao().changePin(acno, oldpin, newpin)>0)
					{
						System.out.println("You have changed your pin successfully");
					}
					else
					{
						System.out.println("Invalid a/c and pin nuber");
					}
					
					break;
				}
				}
			}
			}
		} while (ch <= 2);
	}
}
