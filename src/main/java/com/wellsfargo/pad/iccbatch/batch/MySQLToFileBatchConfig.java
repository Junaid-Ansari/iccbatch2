package com.wellsfargo.pad.iccbatch.batch;


import com.wellsfargo.pad.iccbatch.batch.processor.ODSProcessor;
import com.wellsfargo.pad.iccbatch.batch.reader.ODSReader;
import com.wellsfargo.pad.iccbatch.domain.People;
import com.wellsfargo.pad.iccbatch.job.JobCompletionNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Collections;


// tag::setup[]
    @Configuration
    @EnableBatchProcessing
    public class MySQLToFileBatchConfig {

        @Autowired
        public JobBuilderFactory jobBuilderFactory;

        @Autowired
        public StepBuilderFactory stepBuilderFactory;

        @Autowired
        ODSReader odsReader;
        @Autowired
        ODSProcessor odsProcessor;
        // end::setup[]

        // tag::readerwriterprocessor[]
        @Bean
        public FlatFileItemWriter<People> fileWriter() {
            FlatFileItemWriter<People> writer = new FlatFileItemWriter<>();
            writer.setResource(new FileSystemResource("C://Work/dev/batch/output/data.csv"));
            writer.setLineAggregator(getDelimitedLineAggregator());
            return writer;

        }
        private DelimitedLineAggregator<People> getDelimitedLineAggregator() {
            BeanWrapperFieldExtractor<People> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<People>();
            beanWrapperFieldExtractor.setNames(new String[]{"id", "firstName", "lastName", "email","mobileNumber"});

            DelimitedLineAggregator<People> aggregator = new DelimitedLineAggregator<People>();
            aggregator.setDelimiter(",");
            aggregator.setFieldExtractor(beanWrapperFieldExtractor);
            return aggregator;

        }

        @Bean
        public Job createJob() {
            return jobBuilderFactory.get("MyJob")
                    .incrementer(new RunIdIncrementer())
                    .flow(createStep()).end().build();
        }

        @Bean
        public Step createStep() {
            return stepBuilderFactory.get("MyStep")
                    .<People, People> chunk(1)
                    .reader(odsReader)
                    .processor(odsProcessor)
                    .writer(fileWriter())
                    .build();
        }        // end::jobstep[]
    }
