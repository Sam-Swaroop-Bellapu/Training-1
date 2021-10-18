package com.example.demo.controller;

//public class HelloController {
//
//}
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@RestController
public class HelloController
{
    @RequestMapping("/")
    public Integer hello() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Scanner sc=new Scanner(System.in);
//        String name=sc.nextLine();
        System.out.println("Addition");
        System.out.println("Enter first number:");
        Integer f=sc.nextInt();
        System.out.println("Enter second number:");
        Integer e=sc.nextInt();
        Integer g=f+e;
        System.out.println("The Sum is :"+g);

        return g;
    }
}
