package esdi.Services.services;

import esdi.Services.dtos.DolarDTO;
import esdi.Services.models.Dolar;

public interface DolarService {

        Dolar saveDolar(Dolar dolar);

        DolarDTO getDolarDTO(Dolar dolar);
}
