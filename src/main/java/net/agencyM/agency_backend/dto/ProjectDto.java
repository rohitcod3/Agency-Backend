package net.agencyM.agency_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long projectId;
    private Long clientId; // Reference to the associated client
    private String projectName;
    private String startDate;
    private String endDate;
    private String status;
    private String projectType;
}
