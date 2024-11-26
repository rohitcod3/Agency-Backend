package net.agencyM.agency_backend.service;

import net.agencyM.agency_backend.dto.ProjectDto;
import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);
    ProjectDto getProjectById(Long projectId);
    List<ProjectDto> getAllProjects();
    ProjectDto updateProject(Long projectId, ProjectDto projectDto);
    void deleteProject(Long projectId);
}
