package com.cart.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


import com.cart.model.Iteam;
import com.cart.payload.IteamInput;
import com.cart.repository.IteamRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguratio {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private IteamRepository iteamRepository;
	
	
	@Bean
	public FlatFileItemReader<IteamInput> reader() {

		FlatFileItemReader<IteamInput> fileItemReader = new FlatFileItemReader<>();

		fileItemReader.setResource(new ClassPathResource("Iteams.csv"));
		
		fileItemReader.setLineMapper(getLineMapper());
		
		fileItemReader.setStrict(true);
		
		fileItemReader.setLinesToSkip(1);
		
		return fileItemReader;
	}
	private LineMapper<IteamInput> getLineMapper() {

		DefaultLineMapper<IteamInput> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames( new String [] {"iteam_id", "selling_price", "iteam_name", "units"});
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3});
		

		BeanWrapperFieldSetMapper<IteamInput> fileFieldSetMapper = new BeanWrapperFieldSetMapper<>();

		fileFieldSetMapper.setTargetType(IteamInput.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fileFieldSetMapper);

		return lineMapper;
	}
	
	
	@Bean
	public IteamProcessor iteamProcessor() {
		return new IteamProcessor();
	}

	@Bean
	public RepositoryItemWriter<Iteam> writer() {
		RepositoryItemWriter<Iteam> itemWriter = new RepositoryItemWriter<>();
		
		itemWriter.setRepository(iteamRepository);
		itemWriter.setMethodName("save");
		return itemWriter;

	}
	
	@Bean
	public Job importJob() {
		return this.jobBuilderFactory.
				get("ITEAMS-IMPORT-JOB")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();

	}

	@Bean
	public Step step1() {

		return this.stepBuilderFactory.
				get("step1").<IteamInput, Iteam>chunk(10)
				.reader(reader())
				.writer(writer())
				.processor(iteamProcessor())
				.build();
	}
	
	
	
	
	
	
	
	

}
