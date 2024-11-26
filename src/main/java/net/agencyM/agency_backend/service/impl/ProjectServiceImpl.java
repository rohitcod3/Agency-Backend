package net.agencyM.agency_backend.service.impl;

import lombok.AllArgsConstructor;
import net.agencyM.agency_backend.dto.ProjectDto;
import net.agencyM.agency_backend.entity.Client;
import net.agencyM.agency_backend.entity.Project;
import net.agencyM.agency_backend.exception.ResourceNotFoundException;
import net.agencyM.agency_backend.mapper.ProjectMapper;
import net.agencyM.agency_backend.repository.ClientRepository;
import net.agencyM.agency_backend.repository.ProjectRepository;
import net.agencyM.agency_backend.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        // Validate the client exists
        Client client = clientRepository.findById(projectDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + projectDto.getClientId()));

        // Map ProjectDto to Project entity
        Project project = ProjectMapper.mapToProject(projectDto, client);

        // Save the project in the repository
        Project savedProject = projectRepository.save(project);

        // Convert saved project to ProjectDto and return
        return ProjectMapper.mapToProjectDto(savedProject);
    }

    @Override
    public ProjectDto getProjectById(Long projectId) {
        // Fetch the project by ID
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        // Map project to ProjectDto and return
        return ProjectMapper.mapToProjectDto(project);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        // Fetch all projects and map them to ProjectDto
        return projectRepository.findAll().stream()
                .map(ProjectMapper::mapToProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto updateProject(Long projectId, ProjectDto projectDto) {
        // Fetch the existing project by ID
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        // Validate the client exists for the project
        Client client = clientRepository.findById(projectDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + projectDto.getClientId()));

        // Update the project details
        existingProject.setClient(client);
        existingProject.setProjectName(projectDto.getProjectName());
        existingProject.setStartDate(projectDto.getStartDate());
        existingProject.setEndDate(projectDto.getEndDate());
        existingProject.setStatus(projectDto.getStatus());
        existingProject.setProjectType(projectDto.getProjectType());

        // Save the updated project
        Project updatedProject = projectRepository.save(existingProject);

        // Convert updated project to ProjectDto and return
        return ProjectMapper.mapToProjectDto(updatedProject);
    }

    @Override
    public void deleteProject(Long projectId) {
        // Fetch the project by ID
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        // Delete the project
        projectRepository.delete(project);
    }
}
