package esdi.Services.services.implement;

import esdi.Services.dtos.BrandDTO;
import esdi.Services.mappers.BrandMapper;
import esdi.Services.models.products.Brand;
import esdi.Services.repositories.BrandRepository;
import esdi.Services.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public BrandDTO getBrandDTO(Brand brand) {
        return brandMapper.toDTO(brand);
    }

    @Override
    public List<BrandDTO> findAllDTO() {
        return brandMapper.toDTO(brandRepository.findAll());
    }
}
