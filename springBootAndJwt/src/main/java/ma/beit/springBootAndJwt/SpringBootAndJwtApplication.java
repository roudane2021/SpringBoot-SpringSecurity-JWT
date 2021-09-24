package ma.beit.springBootAndJwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.beit.springBootAndJwt.dao.UserDao;
import ma.beit.springBootAndJwt.entity.User_;
import ma.beit.springBootAndJwt.filter.RequestResponseLoggingFilter;

@SpringBootApplication
public class SpringBootAndJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndJwtApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
	
		FilterRegistrationBean<RequestResponseLoggingFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new RequestResponseLoggingFilter());
		filter.addUrlPatterns("/user/*");
		
		return filter;
		
	}
	
	@Bean
	@Autowired
	public CommandLineRunner run(UserDao userDao,BCryptPasswordEncoder b) {
		
		return (String[] args)->{
			//userDao.save(new User_(1L, "mohsin", "m", b.encode("123456")));
		};
	}

}
