package com.github.fernandotaa.springbatch.multipleexecutions.processor;


import com.github.fernandotaa.springbatch.multipleexecutions.dto.PatientDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class PatientProcessor implements ItemProcessor<PatientDTO, String> {
    @Override
    public String process(PatientDTO item) {
        return String.format("%d - %s", item.getId(), item.getName());
    }
}
