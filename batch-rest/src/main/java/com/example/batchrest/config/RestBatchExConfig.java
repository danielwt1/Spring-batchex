package com.example.batchrest.config;
import com.example.batchrest.dto.PostDTO;
import com.example.batchrest.listener.JobListenerREST;
import com.example.batchrest.model.Post;
import com.example.batchrest.processor.PostProcessor;
import com.example.batchrest.reader.RestPostReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class RestBatchExConfig {
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private static final String PROPERTY_REST_API_URL = "rest.api.url";

    public RestBatchExConfig(JobBuilderFactory jobBuilderFactory ){
        this.jobBuilderFactory=jobBuilderFactory;
    }
    @Bean
    public PostProcessor processor(){
        return new PostProcessor();
    }
    @Bean
    public ItemReader <PostDTO>itemReader(Environment environment,
                                          RestTemplate restTemplate){
        return new RestPostReader(environment.getRequiredProperty(PROPERTY_REST_API_URL),restTemplate);
    }
    @Bean
    public JdbcBatchItemWriter<Post> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Post>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO posts (id, userId, title,body) VALUES (:id, :userId, :tittle, :body)")
                .dataSource(dataSource)
                .build();
    }
    @Bean
    public Job importPostJob(JobListenerREST listener, Step step1) {
        return jobBuilderFactory.get("importPostJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
    @Bean
    public Step step1(ItemReader<PostDTO>reader,JdbcBatchItemWriter<Post> writer) {
        return  stepBuilderFactory.get("step1")
                .<PostDTO, Post> chunk(10)
                .reader(reader)
                .processor(processor())
                .writer(writer)
                .build();
    }


}
