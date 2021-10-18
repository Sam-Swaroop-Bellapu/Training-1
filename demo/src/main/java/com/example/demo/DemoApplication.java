package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args)  {

//		BufferedReader br=new BufferedReader( new  InputStreamReader(System.in));
//		String name=br.readLine();
//		Scanner sc=new Scanner(System.in);
//		String name=sc.nextLine();
//		System.out.println("Addition");
//		System.out.println("Enter first number:");
//		Integer f=sc.nextInt();
//		System.out.println("Enter second number:");
//		Integer e=sc.nextInt();
//		Integer g=f+e;
//		System.out.println("The Sum is :"+g);




		SpringApplication.run(DemoApplication.class, args);
	}

}
