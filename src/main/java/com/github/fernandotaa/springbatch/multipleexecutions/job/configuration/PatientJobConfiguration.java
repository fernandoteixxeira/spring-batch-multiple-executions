package com.github.fernandotaa.springbatch.multipleexecutions.job.configuration;

import com.github.fernandotaa.springbatch.multipleexecutions.dto.PatientDTO;
import com.github.fernandotaa.springbatch.multipleexecutions.enums.Jobs;
import com.github.fernandotaa.springbatch.multipleexecutions.processor.PatientProcessor;
import com.github.fernandotaa.springbatch.multipleexecutions.writer.LoggingWriter;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@Conditional(PatientJobConfiguration.PatientConditional.class)
public class PatientJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public static class PatientConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return Jobs.PATIENT.name().equals(System.getProperty("jobName"));
        }
    }

    public PatientJobConfiguration(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("patientReader")
    @StepScope
    public ItemReader<PatientDTO> patientReader(DataSource dataSource) {
        return new JdbcPagingItemReaderBuilder<PatientDTO>()
                .dataSource(dataSource)
                .name("patientReader")
                .selectClause("ID, NAME")
                .fromClause("PATIENT")
                .sortKeys(Map.of("ID", Order.ASCENDING))
                .pageSize(5)
                .rowMapper(PatientDTO.ROW_MAPPER)
                .build();
    }

    @Bean("patientWriter")
    @StepScope
    public ItemWriter<String> patientWriter() {
        return new LoggingWriter("Patient: ");
    }

    @Bean
    public Step patientStep(
            @Qualifier("patientReader") ItemReader<PatientDTO> patientReader,
            @Qualifier("patientWriter") ItemWriter<String> patientWriter,
            PatientProcessor processor
    ) {
        return stepBuilderFactory.get("patientStep")
                .<PatientDTO, String>chunk(10)
                .reader(patientReader)
                .processor(processor)
                .writer(patientWriter)
                .build();
    }

    @SneakyThrows
    @Bean
    public Job importPatientJob(Step patientStep) {
        var importPatientJob = jobBuilderFactory.get("importPatientJob")
                .incrementer(new RunIdIncrementer())
                .flow(patientStep)
                .end()
                .build();
        return importPatientJob;
    }
}

