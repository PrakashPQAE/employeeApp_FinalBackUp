package com.example.employeeApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
 

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	  @Bean
	  CommandLineRunner initDatabase(EmployeeRespository repository) {

	    return args -> {
	      log.info("Preloading " + ((CrudRepository<Employee, Long>) repository).save(new Employee("Bilbo Baggins", "Support Engineer","Bilbo","Baggins","bilbo@gmail.com","abc123","9009009009")));
	      log.info("Preloading " + ((CrudRepository<Employee, Long>) repository).save(new Employee("Frodo Baggins", "IT Engineer", "Frodo", "Baggins", "Frodo@gmail.com", "123abc", "9098987897")));
	      log.info("Preloading " + ((CrudRepository<Employee, Long>) repository).save(new Employee("Robert Tim", "QA", "Robert", "Tim", "Robert@gmail.com","1234323", "98987675457")));
			/*
			 * log.info("Preloading " + ((CrudRepository<Employee, Long>)
			 * repository).save(new Employee("James Crue", "Dev"))); log.info("Preloading "
			 * + ((CrudRepository<Employee, Long>) repository).save(new Employee("Tim Brue",
			 * "BA"))); log.info("Preloading " + ((CrudRepository<Employee, Long>)
			 * repository).save(new Employee("Alex Mella", "Manager")));
			 */
	    };
	  }

}
