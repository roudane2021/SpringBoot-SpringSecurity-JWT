package ma.beit.springBootAndJwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.beit.springBootAndJwt.entity.User_;

public interface UserDao extends JpaRepository<User_,Long> {
	
	
	User_  findByName(String name);

	

}
