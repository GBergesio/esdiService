package esdi.Services.services;
import esdi.Services.dtos.BrandDTO;
import esdi.Services.models.products.Brand;
import java.util.List;

public interface BrandService {
        Brand saveBrand(Brand brand);

        BrandDTO getBrandDTO(Brand brand);

        List<BrandDTO> findAllDTO();
}


