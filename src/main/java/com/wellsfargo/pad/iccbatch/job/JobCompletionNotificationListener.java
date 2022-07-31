package com.wellsfargo.pad.iccbatch.job;

import com.wellsfargo.pad.iccbatch.domain.Person;
import com.wellsfargo.pad.iccbatch.repo.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EnableMongoRepositories
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	PersonRepo personRepo;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<Person> itemList = new ArrayList<Person>();
			itemList = personRepo.findAll();
			log.info("!!!Searching Mongo ");
			System.out.println("Found these records: " + personRepo.count());
			itemList.forEach(item -> System.out.println(item));

//			jdbcTemplate.query("SELECT first_name, last_name, email, mobileNumber FROM people",
//					(rs, row) -> new Person(
//							rs.getString(1),
//							rs.getString(2),
//							rs.getString(3),
//							rs.getString(4))
//			).forEach(person -> log.info("Found <" + person + "> in the database."));
		}
	}
}
