package esdi.Services.services;
import esdi.Services.dtos.BrandDTO;
import esdi.Services.models.products.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BrandService {
        Brand saveBrand(Brand brand);

        BrandDTO getBrandDTO(Brand brand);

        List<BrandDTO> findAllDTO();

        ResponseEntity<?> allBrands();

        ResponseEntity<?> findById(Long id);
        ResponseEntity<?> allBrandsByCompany(Authentication authentication);

        ResponseEntity<?> createBrand(BrandDTO brandDTO,Authentication authentication );

        ResponseEntity<?> renameBrand(Long id, String name,Authentication authentication);

        ResponseEntity<?> deleteBrand(Long id,Authentication authentication);
}