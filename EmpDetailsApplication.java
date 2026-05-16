package com.jpa.main;

import java.rmi.server.Operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jpa.main.entity.Operations;

@SpringBootApplication
public class EmpDetailsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = SpringApplication.run(EmpDetailsApplication.class, args);
		Operations op1 = (Operations) container.getBean("operations");
		op1.AddUserWithMultipleParking();
}

}
