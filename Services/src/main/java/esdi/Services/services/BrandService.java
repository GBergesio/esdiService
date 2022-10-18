package esdi.Services.services;
import esdi.Services.dtos.BrandDTO;
import esdi.Services.models.products.Brand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandService {
        Brand saveBrand(Brand brand);

        BrandDTO getBrandDTO(Brand brand);

        List<BrandDTO> findAllDTO();

        ResponseEntity<?> allBrands();

        ResponseEntity<?> findById(Long id);

        ResponseEntity<?> createBrand(BrandDTO brandDTO);

        ResponseEntity<?> renameBrand(Long id, String name);

        ResponseEntity<?> deleteBrand(Long id);
}