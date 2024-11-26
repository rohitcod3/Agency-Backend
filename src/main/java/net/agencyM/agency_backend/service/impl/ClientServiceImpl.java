package net.agencyM.agency_backend.service.impl;

import lombok.AllArgsConstructor;
import net.agencyM.agency_backend.dto.ClientDto;
import net.agencyM.agency_backend.entity.Client;
import net.agencyM.agency_backend.exception.ResourceNotFoundException;
import net.agencyM.agency_backend.mapper.ClientMapper;
import net.agencyM.agency_backend.repository.ClientRepository;
import net.agencyM.agency_backend.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDto createClient(ClientDto clientDto) {

        if (clientRepository.existsByClientEmail(clientDto.getClientEmail())) {
            throw new IllegalArgumentException("Client email already exists: " + clientDto.getClientEmail());
        }


        Client client = ClientMapper.mapToClient(clientDto);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.mapToClientDto(savedClient);
    }


    @Override
    public ClientDto getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));
        return ClientMapper.mapToClientDto(client);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientMapper::mapToClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto updateClient(Long clientId, ClientDto clientDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));

        client.setClientName(clientDto.getClientName());
        client.setCompanyName(clientDto.getCompanyName());
        client.setClientEmail(clientDto.getClientEmail());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setIndustry(clientDto.getIndustry());

        Client updatedClient = clientRepository.save(client);
        return ClientMapper.mapToClientDto(updatedClient);
    }

    @Override
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));

        if (!client.getProjects().isEmpty()) {
            throw new IllegalStateException("Cannot delete client with associated projects.");
        }

        clientRepository.delete(client);
    }
}
