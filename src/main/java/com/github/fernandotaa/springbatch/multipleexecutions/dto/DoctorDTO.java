package com.github.fernandotaa.springbatch.multipleexecutions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

@Getter
@AllArgsConstructor
public class DoctorDTO {

    public static final RowMapper<DoctorDTO> ROW_MAPPER =
            (resultSet, rowNumber) -> new DoctorDTO(resultSet.getInt("ID"), resultSet.getString("NAME"));

    private Integer id;
    private String name;
}
