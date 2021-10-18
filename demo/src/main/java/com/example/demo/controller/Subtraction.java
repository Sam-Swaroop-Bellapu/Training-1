package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController
public class Subtraction {
    @RequestMapping("/sub")
    public Integer subtraction(){
        Scanner sc=new Scanner(System.in);
//        Scanner sc=new Scanner(System.in);
//        String name=sc.nextLine();
        System.out.println("Subtraction");
        System.out.println("Enter first number:");
        Integer f=sc.nextInt();
        System.out.println("Enter second number:");
        Integer e=sc.nextInt();
        Integer g=f-e;
        System.out.println("The Sum is :"+g);
        return g;
    }

}
