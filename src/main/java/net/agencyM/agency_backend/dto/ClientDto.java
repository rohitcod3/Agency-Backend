package net.agencyM.agency_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long clientId;
    private String clientName;
    private String companyName;
    private String clientEmail;
    private String address;
    private String phone;
    private String industry;
    private List<Long> projectIds; // IDs of associated projects
}
