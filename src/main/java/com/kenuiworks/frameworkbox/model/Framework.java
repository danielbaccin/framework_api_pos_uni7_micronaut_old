package com.kenuiworks.frameworkbox.model;

import com.kenuiworks.frameworkbox.dto.FrameworkDTO;
import com.kenuiworks.frameworkbox.enums.SatisfactionLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Framework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SatisfactionLevel satisfactionLevel;

    @Column(nullable = false)
    private Integer monthsOfExperience;

    public FrameworkDTO toDto() {
        FrameworkDTO dto = new FrameworkDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setLanguage(language);
        dto.setSatisfactionLevel(satisfactionLevel);
        dto.setMonthsOfExperience(monthsOfExperience);
        return dto;
    }
}
