package esdi.Services.services.implement;

import esdi.Services.mappers.NbhMapper;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Neighborhood;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.NeighborhoodRepository;
import esdi.Services.repositories.StaffRepository;
import esdi.Services.services.NeighbourhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NeighbourhoodServiceImpl implements NeighbourhoodService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    NeighborhoodRepository neighborhoodRepository;

    @Autowired
    NbhMapper nbhMapper;

    @Override
    public ResponseEntity<?> allNeighbourhoodByCompany(Authentication authentication) {

        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            if(company != null){
                List<Neighborhood> neighborhoodList = neighborhoodRepository.findAllByCompany(company);

                return new ResponseEntity<>(nbhMapper.toDTO(neighborhoodList), HttpStatus.OK);
            }
            if(staff != null){
                Company companyStaff = staff.getCompany();
                List<Neighborhood> neighborhoodList = neighborhoodRepository.findAllByCompany(companyStaff);

                return new ResponseEntity<>(nbhMapper.toDTO(neighborhoodList), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }
}
