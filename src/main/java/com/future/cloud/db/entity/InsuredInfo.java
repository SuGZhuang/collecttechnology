package com.future.cloud.db.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class InsuredInfo {

    @NotNull(message = "被保人姓名不能为空")
    private String insuredName;

    private String insuredSex;

    @Size(min = 0, max = 3)
    private Integer age;

    private LocalDate birthday;

    @NotBlank(message = "被保人身份证号不能为空")
    private String idNum;

}
