package ma.beit.springBootAndJwt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.beit.springBootAndJwt.dao.UserDao;
import ma.beit.springBootAndJwt.entity.User_;

@Service(value = "u1")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User_ user = userDao.findByName(username);
		if(user==null) {
			throw new RuntimeException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(
			      r->{
			    	  authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
			      });
		UserDetails userSpringSecurity = new User(user.getName(), user.getPassword(),authorities);
		return userSpringSecurity;
	}

}
