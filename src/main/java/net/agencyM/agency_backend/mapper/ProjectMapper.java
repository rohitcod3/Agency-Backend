package net.agencyM.agency_backend.mapper;

import net.agencyM.agency_backend.dto.ProjectDto;
import net.agencyM.agency_backend.entity.Project;
import net.agencyM.agency_backend.entity.Client;


public class ProjectMapper {

    public static ProjectDto mapToProjectDto(Project project) {
        return new ProjectDto(
                project.getProjectId(),
                project.getClient().getClientId(),
                project.getProjectName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                project.getProjectType()
        );
    }

    public static Project mapToProject(ProjectDto projectDto, Client client) {
        Project project = new Project();
        project.setProjectId(projectDto.getProjectId());
        project.setClient(client);
        project.setProjectName(projectDto.getProjectName());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setStatus(projectDto.getStatus());
        project.setProjectType(projectDto.getProjectType());
        return project;
    }
}
