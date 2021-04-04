package com.kenuiworks.frameworkbox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthOfExperienceDTO {


    @NotNull
    private Integer monthsOfExperience;
}
