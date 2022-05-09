package com.example.batchrest.listener;


import com.example.batchrest.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobListenerREST extends JobExecutionListenerSupport {
    private JdbcTemplate jdbcTemplate;
    private  static final Logger LOG= LoggerFactory.getLogger(JobListenerREST.class);

    public JobListenerREST(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("FINALIZÓ EL JOB!! Verifica los resultados:");
            jdbcTemplate
                    .query("SELECT id, userId, title,body FROM posts",
                            (rs,row) -> new Post(Long.valueOf(rs.getString(2)), Integer.valueOf(rs.getString(1)), rs.getString(3),rs.getString(4)))
                    .forEach(post -> LOG.info("Registro < " +post + " >"));
        }
    }
}
