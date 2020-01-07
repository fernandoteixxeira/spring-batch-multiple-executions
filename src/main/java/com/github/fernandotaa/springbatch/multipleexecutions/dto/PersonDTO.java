package com.github.fernandotaa.springbatch.multipleexecutions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    public static final RowMapper<PersonDTO> ROW_MAPPER =
            (resultSet, rowNumber) -> new PersonDTO(resultSet.getLong("ID"), resultSet.getString("NAME"), null);

    private Long id;
    private String name;
    private String document;
}
