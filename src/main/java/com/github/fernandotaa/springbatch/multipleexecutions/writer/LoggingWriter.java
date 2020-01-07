package com.github.fernandotaa.springbatch.multipleexecutions.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class LoggingWriter implements ItemWriter<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingWriter.class);

    private final String label;

    public LoggingWriter(String label) {
        this.label = label;
    }

    @Override
    public void write(List<? extends String> items) {
        items.stream().map(label::concat).forEach(LOGGER::info);
    }
}
