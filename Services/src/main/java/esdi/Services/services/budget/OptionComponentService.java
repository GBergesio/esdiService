package esdi.Services.services.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.models.budgets.OptionComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OptionComponentService {

        ResponseEntity<?> allOptionsComponent();

        ResponseEntity<?> findById(Long id);
        ResponseEntity<?> allOptionsComponentByCompany(Authentication authentication);
        ResponseEntity<?> deleteOption(Authentication authentication, Long id);

        ResponseEntity<?> updateOption(Authentication authentication, Long id);
}
