package ma.beit.springBootAndJwt.service;

import java.util.List;

import ma.beit.springBootAndJwt.entity.User_;



public interface UserService {

   List<User_> getAllUser();
   User_ getUserById(Long id);
   User_ createUser(User_ user);
   void deleteUser(Long id);

}
