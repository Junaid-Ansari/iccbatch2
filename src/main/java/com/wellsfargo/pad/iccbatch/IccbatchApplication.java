package com.wellsfargo.pad.iccbatch;

import com.wellsfargo.pad.iccbatch.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoAuditing
@EnableMongoRepositories
@SpringBootApplication
public class IccbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(IccbatchApplication.class, args);
	}

}
