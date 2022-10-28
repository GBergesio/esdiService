package esdi.Services.services;

import esdi.Services.dtos.DolarDTO;
import esdi.Services.models.Dolar;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface DolarService {

        Dolar saveDolar(Dolar dolar);

        ResponseEntity<?> dolarByCompany(Authentication authentication);
}
