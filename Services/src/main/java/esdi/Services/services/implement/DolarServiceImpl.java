package esdi.Services.services.implement;

import esdi.Services.dtos.DolarDTO;
import esdi.Services.models.Dolar;
import esdi.Services.repositories.DolarRepository;
import esdi.Services.services.DolarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DolarServiceImpl implements DolarService {

    @Autowired
    DolarRepository dolarRepository;


    @Override
    public Dolar saveDolar(Dolar dolar) {
        return dolarRepository.save(dolar);
    }

    @Override
    public DolarDTO getDolarDTO(Dolar dolar) {
        return null;
    }
}
