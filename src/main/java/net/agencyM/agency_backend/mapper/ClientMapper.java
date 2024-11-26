package net.agencyM.agency_backend.mapper;

import net.agencyM.agency_backend.dto.ClientDto;
import net.agencyM.agency_backend.entity.Client;
import java.util.ArrayList;
import net.agencyM.agency_backend.entity.Project;

import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDto mapToClientDto(Client client) {
        return new ClientDto(
                client.getClientId(),
                client.getClientName(),
                client.getCompanyName(),
                client.getClientEmail(),
                client.getAddress(),
                client.getPhone(),
                client.getIndustry(),
                client.getProjects() == null
                        ? new ArrayList<>()
                        : client.getProjects().stream()
                        .map(Project::getProjectId)
                        .collect(Collectors.toList())
        );
    }

    public static Client mapToClient(ClientDto clientDto) {
        Client client = new Client();
        client.setClientId(clientDto.getClientId());
        client.setClientName(clientDto.getClientName());
        client.setCompanyName(clientDto.getCompanyName());
        client.setClientEmail(clientDto.getClientEmail());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setIndustry(clientDto.getIndustry());
        return client;
    }
}
