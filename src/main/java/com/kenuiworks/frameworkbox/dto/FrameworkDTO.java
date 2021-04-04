package com.kenuiworks.frameworkbox.dto;

import com.kenuiworks.frameworkbox.enums.SatisfactionLevel;
import com.kenuiworks.frameworkbox.model.Framework;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class FrameworkDTO {

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @Size(min = 1, max = 500)
    private String description;

    @Size(min = 1, max = 100)
    private String language;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SatisfactionLevel satisfactionLevel;

    @NotNull
    private Integer monthsOfExperience;

    public Framework toModel() {
        Framework framework = new Framework();
        framework.setName(name);
        framework.setDescription(description);
        framework.setLanguage(language);
        framework.setSatisfactionLevel(satisfactionLevel);
        framework.setMonthsOfExperience(monthsOfExperience);
        return framework;
    }
}
