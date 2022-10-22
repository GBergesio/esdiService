package esdi.Services.services.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.models.budgets.OptionComponent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OptionComponentService {

        OptionComponent saveOptionComponent(OptionComponent optionComponent);

        OptionComponentDTO saveOptionComponentDTO(OptionComponent optionComponent);

        List<OptionComponentDTO> findAllDTO();

        ResponseEntity<?> allOptionsComponent();

        ResponseEntity<?> findById(Long id);

        ResponseEntity<?> createOptionComponent(OptionRequest optionRequest, Long idBudget, Long idPoS);
    //
    //    ResponseEntity<?> updateOptionBudget(Long id);
    //
        ResponseEntity<?> deleteOption(Long id);
}
