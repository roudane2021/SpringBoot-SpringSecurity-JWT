package ma.beit.springBootAndJwt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.beit.springBootAndJwt.dao.UserDao;
import ma.beit.springBootAndJwt.entity.User_;
import ma.beit.springBootAndJwt.service.UserService;



@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public List<User_> getAllUser() {
		
		return userDao.findAll();
	}

	@Override
	public User_ getUserById(Long id) {
		
		return userDao.findById(id).orElse(new User_(0L, "UNKNOW", "UNKNOW","123456",new ArrayList<>()));
	}

	@Override
	public User_ createUser(User_ user) {
		userDao.save(user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteById(id);
		
	}

}
