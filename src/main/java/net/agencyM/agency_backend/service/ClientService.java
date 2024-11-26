package net.agencyM.agency_backend.service;

import net.agencyM.agency_backend.dto.ClientDto;
import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto clientDto);
    ClientDto getClientById(Long clientId);
    List<ClientDto> getAllClients();
    ClientDto updateClient(Long clientId, ClientDto clientDto);
    void deleteClient(Long clientId);
}
