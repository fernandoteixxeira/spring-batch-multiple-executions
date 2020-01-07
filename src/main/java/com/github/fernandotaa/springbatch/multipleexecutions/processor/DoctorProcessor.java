package com.github.fernandotaa.springbatch.multipleexecutions.processor;


import com.github.fernandotaa.springbatch.multipleexecutions.dto.DoctorDTO;
import com.github.fernandotaa.springbatch.multipleexecutions.dto.PatientDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class DoctorProcessor implements ItemProcessor<DoctorDTO, String> {
    @Override
    public String process(DoctorDTO item) {
        return String.format("%d - %s", item.getId(), item.getName());
    }
}
