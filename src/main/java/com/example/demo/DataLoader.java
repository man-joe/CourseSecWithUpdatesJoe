package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Executable;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  CourseRepository courseRepository;

  @Override
  public void run(String... strings) throws Exception{
    roleRepository.save(new Role("USER"));
    roleRepository.save(new Role("ADMIN"));
    roleRepository.save(new Role("TESTROLE"));

    Role adminRole = roleRepository.findByRole("ADMIN");
    Role userRole = roleRepository.findByRole("USER");
    Role testRole = roleRepository.findByRole("TESTROLE");

    User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true,
            "jim");
    user.setRoles(Arrays.asList(userRole, testRole));
    userRepository.save(user);

    Course course = new Course("CS101", "Prof. Stark", "Introduction to Java Programming", 3);
    course.setUser(user);
    courseRepository.save(course);

    user = new User("admin@admin.com", "password",
            "Admin",
            "User", true,
            "admin");
    user.setRoles(Arrays.asList(adminRole));
    userRepository.save(user);


  }
}
