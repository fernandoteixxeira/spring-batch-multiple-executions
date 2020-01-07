package com.github.fernandotaa.springbatch.multipleexecutions.job.configuration;

import com.github.fernandotaa.springbatch.multipleexecutions.dto.DoctorDTO;
import com.github.fernandotaa.springbatch.multipleexecutions.enums.Jobs;
import com.github.fernandotaa.springbatch.multipleexecutions.processor.DoctorProcessor;
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
@Conditional(DoctorJobConfiguration.DoctorConditional.class)
public class DoctorJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public static class DoctorConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return Jobs.DOCTOR.name().equals(System.getProperty("jobName"));
        }
    }

    public DoctorJobConfiguration(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("doctorReader")
    @StepScope
    public ItemReader<DoctorDTO> doctorReader(DataSource dataSource) {
        return new JdbcPagingItemReaderBuilder<DoctorDTO>()
                .dataSource(dataSource)
                .name("doctorReader")
                .selectClause("ID, NAME")
                .fromClause("DOCTOR")
                .sortKeys(Map.of("ID", Order.ASCENDING))
                .pageSize(5)
                .rowMapper(DoctorDTO.ROW_MAPPER)
                .build();
    }

    @Bean("doctorWriter")
    @StepScope
    public ItemWriter<String> doctorWriter() {
        return new LoggingWriter("Doctor: ");
    }

    @Bean
    public Step doctorStep(
            @Qualifier("doctorReader") ItemReader<DoctorDTO> doctorReader,
            @Qualifier("doctorWriter") ItemWriter<String> doctorWriter,
            DoctorProcessor processor
    ) {
        return stepBuilderFactory.get("doctorStep")
                .<DoctorDTO, String>chunk(10)
                .reader(doctorReader)
                .processor(processor)
                .writer(doctorWriter)
                .build();
    }

    @SneakyThrows
    @Bean
    public Job importDoctorJob(Step doctorStep) {
        Job importPatientJob = jobBuilderFactory.get("importDoctorJob")
                .incrementer(new RunIdIncrementer())
                .flow(doctorStep)
                .end()
                .build();
        return importPatientJob;
    }
}

