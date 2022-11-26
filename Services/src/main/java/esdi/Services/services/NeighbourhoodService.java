package esdi.Services.services;

import esdi.Services.dtos.NeighborhoodDTO;
import esdi.Services.models.users.Neighborhood;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface NeighbourhoodService {


    ResponseEntity<?> allNeighbourhoodByCompany(Authentication authentication);
}
