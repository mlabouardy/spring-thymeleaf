package com.labouardy.socialnetwork;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.labouardy.socialnetwork.dao.UserRepository;
import com.labouardy.socialnetwork.entity.User;

@SpringBootApplication
public class SocialNetworkApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SocialNetworkApplication.class, args);
		UserRepository userRepository=ctx.getBean(UserRepository.class);
		
		String[] names={"Mohamed", "Randy", "John", "Frank", "Youssef", "Paul", "David"};
		for(int i=0;i<25;i++){
			User user=new User();
			user.setUsername(names[random(0, names.length-1)]);
			user.setSex("Male");
			user.setPhone("+3347845458");
			userRepository.save(user);
		}
		
	}
	
	public static int random(int min ,int max){
		Random r = new Random();
		return r.nextInt(max-min) + min;
	}
}
