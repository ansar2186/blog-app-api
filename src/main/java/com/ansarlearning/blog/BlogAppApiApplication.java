package com.ansarlearning.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ansarlearning.blog.config.AppConstant;
import com.ansarlearning.blog.entity.UserRole;
import com.ansarlearning.blog.repository.RoleRepo;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder encoder;
	
	
	private static final Logger logger = LoggerFactory.getLogger(BlogAppApiApplication.class);


	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(BlogAppApiApplication.class, args);
		
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			UserRole role = new UserRole();
			role.setRoleId(AppConstant.ADMIN_USER);
			role.setRoleName("ADMIN_USER");

			UserRole role1 = new UserRole();
			role1.setRoleId(AppConstant.NORMAL_USER);
			role1.setRoleName("NORMAL_USER");

			List<UserRole> listRole = List.of(role, role1);

			List<UserRole> saveAllRole = this.roleRepo.saveAll(listRole);

			saveAllRole.forEach(r -> 
				logger.debug(r.getRoleName())
			);

		} catch (Exception e) {
			logger.debug(e.getMessage());

		}

	}

}
