package com.labouardy.socialnetwork.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.labouardy.socialnetwork.dao.UserRepository;
import com.labouardy.socialnetwork.entity.User;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model, @RequestParam(value="page", required=false ,defaultValue="1") Integer page){
		Page<User> pageUsers=userRepository.findAll(new PageRequest(page-1, 12));
		model.addAttribute("users", pageUsers.getContent());
		model.addAttribute("pages", pageUsers.getTotalPages());
		model.addAttribute("currentPage", pageUsers.getNumber());
		return "users";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST,consumes="application/json", produces="text/plain")
	public ResponseEntity<String> signIn(@RequestBody User user){
		userRepository.save(user);
		return new ResponseEntity<String>("User has been created", HttpStatus.ACCEPTED);
	}
}
