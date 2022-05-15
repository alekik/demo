package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
//@Controller
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@GetMapping(value = "")
//	public String start() {
//		return "index";
//	}
//	@GetMapping(value = "/")
//	public String start1() {
//		return "index";
//	}
//	@GetMapping(value = "/hi")
//	public String start2() {
//		return "hi";
//	}
}
