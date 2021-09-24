package ma.beit.springBootAndJwt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ma.beit.springBootAndJwt.entity.User_;
import ma.beit.springBootAndJwt.service.UserService;
import ma.beit.springBootAndJwt.util.AuthenticationRequest;
import ma.beit.springBootAndJwt.util.AuthenticationResponse;
import ma.beit.springBootAndJwt.util.JwtUtil;



@RestController
public class MainController {
	
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;


	
	 public MainController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/")
	public ResponseEntity<List<User_>> listUser(Model model) {
		

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getAllUser());
		
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User_> userById(@PathVariable Long id){
		
		return ResponseEntity.ok().body(userService.getUserById(id));
	}
	
	@DeleteMapping(value ="user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	@PostMapping(value = "user")
	public ResponseEntity<User_> createUser(@RequestBody User_ user){
		
		
		
		return ResponseEntity.ok().body(userService.createUser(user));
		
	}
	
	

}
