package controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.UsersImpS;

@RestController
public class UsersC {
	
	UsersImpS sUsers = new UsersImpS();
	
	@PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> registerUser(@RequestBody Map<String, Object> user) {
		return sUsers.addUser((String)user.get("email"), (String)user.get("password"));
	}
	
	@GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> loginUser(@RequestBody Map<String, Object> user){
		return sUsers.existUserService((String)user.get("email"), (String)user.get("password"));
	}
	
	
	
	
	
}
