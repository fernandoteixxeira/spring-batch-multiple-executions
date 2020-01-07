package com.github.fernandotaa.springbatch.multipleexecutions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

@Getter
@AllArgsConstructor
public class PatientDTO {

    public static final RowMapper<PatientDTO> ROW_MAPPER =
            (resultSet, rowNumber) -> new PatientDTO(resultSet.getInt("ID"), resultSet.getString("NAME"));

    private Integer id;
    private String name;
}
