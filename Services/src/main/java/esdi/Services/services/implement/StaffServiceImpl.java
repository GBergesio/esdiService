package esdi.Services.services.implement;

import esdi.Services.dtos.request.StaffRequest;
import esdi.Services.enums.CompanyPlan;
import esdi.Services.enums.UserType;
import esdi.Services.mappers.StaffMapper;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.StaffRepository;
import esdi.Services.services.ClientService;
import esdi.Services.services.CompanyService;
import esdi.Services.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    StaffMapper staffMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public ResponseEntity<?> getAllStaffs() {
        return new ResponseEntity<>(staffMapper.toDTO(staffRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllStaffsByCompany(Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        List<Staff> staffList = staffRepository.findAllByCompany(company);
        List<Staff> staffAvailable = staffList.stream().filter(staff -> staff.getDeleted().equals(false)).collect(Collectors.toList());

        return new ResponseEntity<>(staffMapper.toDTO(staffAvailable), HttpStatus.OK);
    }

    @Override
    public Staff getCurrentStaff(Authentication authentication) {
        return staffRepository.findByEmail(authentication.getName());
    }

    @Override
    public ResponseEntity<?> getStaffById(Long id) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null)
            return new ResponseEntity<>("Miembro de staff no encontrado", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(staffMapper.toDTO(staff), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getStaffByDni(String dni) {
        Staff staff = staffRepository.findByDni(dni);
        if (staff == null)
            return new ResponseEntity<>("Miembro de staff no encontrado", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(staffMapper.toDTO(staff), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getStaffByUser(String user) {
        Staff staff = staffRepository.findByUser(user);
        if (staff == null)
            return new ResponseEntity<>("Miembro de staff no encontrado", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(staffMapper.toDTO(staff), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createStaff(StaffRequest staffRequest, Long id) {

        Company company = companyRepository.findById(id).orElse(null);

        if (staffRepository.findByEmail(staffRequest.getEmail().toLowerCase()) != null)
            return new ResponseEntity<>("EMAIL en uso", HttpStatus.BAD_REQUEST);

        if (staffRequest.getDni() == null || staffRequest.getDni().isEmpty() || staffRequest.getDni().isBlank())
            return new ResponseEntity<>("DNI requerido", HttpStatus.BAD_REQUEST);

        if (staffRepository.findByDni(staffRequest.getDni()) != null)
            return new ResponseEntity<>("DNI en uso", HttpStatus.BAD_REQUEST);

        if (staffRequest.getFirstName() == null || staffRequest.getFirstName().isEmpty() || staffRequest.getFirstName().isBlank())
            return new ResponseEntity<>("Nombre requerido", HttpStatus.BAD_REQUEST);

        if (staffRequest.getLastName() == null || staffRequest.getLastName().isEmpty() || staffRequest.getLastName().isBlank())
            return new ResponseEntity<>("Apellido requerido", HttpStatus.BAD_REQUEST);

        if (staffRequest.getUser() == null || staffRequest.getUser().isEmpty() || staffRequest.getUser().isBlank())
            return new ResponseEntity<>("Usuario requerido", HttpStatus.BAD_REQUEST);

        if (staffRepository.findByUser(staffRequest.getUser().toLowerCase()) != null)
            return new ResponseEntity<>("Usuario en uso", HttpStatus.BAD_REQUEST);

        if (staffRequest.getPassword() == null || staffRequest.getPassword().isEmpty() || staffRequest.getPassword().isBlank())
            return new ResponseEntity<>("Contraseña requerida", HttpStatus.BAD_REQUEST);

        if (company == null){
            return new ResponseEntity<>("Compañia requerida", HttpStatus.BAD_REQUEST);
        }


        Staff newStaff = new Staff();
        newStaff.setDni(staffRequest.getDni());
        newStaff.setEmail(staffRequest.getEmail().toLowerCase());
        newStaff.setFirstName(staffRequest.getFirstName());
        newStaff.setLastName(staffRequest.getLastName());
        newStaff.setUser(staffRequest.getUser().toLowerCase());
        newStaff.setPassword(staffRequest.getPassword());
        newStaff.setUserType(staffRequest.getUserType());
        newStaff.setCompany(company);
        newStaff.setDeleted(false);

        staffRepository.save(newStaff);
        return new ResponseEntity<>(staffMapper.toDTO(newStaff), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateStaff(Long id, StaffRequest staffRequest) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null)
            return new ResponseEntity<>("Miembro de staff no encontrado", HttpStatus.BAD_REQUEST);

        if (staffRequest.getDni() != null) {
            if (staffRepository.findByDni(staffRequest.getDni()) != null)
                return new ResponseEntity<>("DNI en uso", HttpStatus.BAD_REQUEST);
            if (!staff.getDni().equals(staffRequest.getDni()))
                staff.setDni(staffRequest.getDni());
        }

        if (staffRequest.getFirstName() != null)
            if (!staff.getFirstName().equals(staffRequest.getFirstName()))
                staff.setFirstName(staffRequest.getFirstName());

        if (staffRequest.getLastName() != null)
            if (!staff.getLastName().equals(staffRequest.getLastName()))
                staff.setLastName(staffRequest.getLastName());

        if (staffRequest.getEmail() != null) {
            if (staffRepository.findByEmail(staffRequest.getEmail().toLowerCase()) != null)
                return new ResponseEntity<>("EMAIL en uso", HttpStatus.BAD_REQUEST);
            if (!staff.getEmail().equals(staffRequest.getEmail().toLowerCase()))
                staff.setEmail(staffRequest.getEmail().toLowerCase());
        }

        if (staffRequest.getUser() != null)
            if (!staff.getUser().equals(staffRequest.getUser().toLowerCase()))
                staff.setUser(staffRequest.getUser().toLowerCase());

        if (staffRequest.getPassword() != null)
            if (!staff.getPassword().equals(staffRequest.getPassword()))
                staff.setPassword(staffRequest.getPassword());

        if (staffRequest.getUserType() != null)
            if (!staff.getUserType().equals(staffRequest.getUserType()))
                staff.setUserType(staffRequest.getUserType());

        staffRepository.save(staff);
        return new ResponseEntity<>(staffMapper.toDTO(staff), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id).orElse(null);

        if (staff == null)
            return new ResponseEntity<>("Miembro de staff no encontrado", HttpStatus.BAD_REQUEST);

        staff.setDeleted(true);
        staffRepository.save(staff);
        return new ResponseEntity<>("Staff desactivado exitosamente", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createStaffByCompany(Authentication authentication, StaffRequest staffRequest ) {
        Company company = companyRepository.findByUser(authentication.getName());
        List<Staff> staffList = staffRepository.findAllByCompany(company);
        List<Staff> admins = staffList.stream().filter(staff -> staff.getUserType().equals(UserType.ADMIN)).collect(Collectors.toList());
        List<Staff> technicians = staffList.stream().filter(staff -> staff.getUserType().equals(UserType.TECHNICIAN)).collect(Collectors.toList());

        if(company.getPlan() == CompanyPlan.ONE){
            if (admins.size() == 0 && staffRequest.getUserType().equals(UserType.ADMIN)) {
                return new ResponseEntity<>("No puedes crear administradores, mejora tu plan", HttpStatus.BAD_REQUEST);
            }
            if (technicians.size() == 1 && staffRequest.getUserType().equals(UserType.TECHNICIAN)) {
                return new ResponseEntity<>("Ya no se pueden crear tecnicos, mejora tu plan", HttpStatus.BAD_REQUEST);
            }
        }

        if(company.getPlan() == CompanyPlan.TWO){
            if (admins.size() == 2 && staffRequest.getUserType().equals(UserType.ADMIN)) {
                return new ResponseEntity<>("Ya no se pueden crear admins, mejora tu plan", HttpStatus.BAD_REQUEST);
            }
            if (technicians.size() == 2 && staffRequest.getUserType().equals(UserType.TECHNICIAN)) {
                return new ResponseEntity<>("Ya no se pueden crear tecnicos, mejora tu plan", HttpStatus.BAD_REQUEST);
            }
        }

        if (staffRepository.findByEmail(staffRequest.getEmail().toLowerCase()) != null)
            return new ResponseEntity<>("EMAIL en uso", HttpStatus.BAD_REQUEST);

        if (staffRequest.getDni() == null || staffRequest.getDni().isEmpty() || staffRequest.getDni().isBlank())
            return new ResponseEntity<>("DNI requerido", HttpStatus.BAD_REQUEST);

        if (staffRepository.findByDni(staffRequest.getDni()) != null)
            return new ResponseEntity<>("DNI en uso", HttpStatus.BAD_REQUEST);

        if (staffRequest.getFirstName() == null || staffRequest.getFirstName().isEmpty() || staffRequest.getFirstName().isBlank())
            return new ResponseEntity<>("Nombre requerido", HttpStatus.BAD_REQUEST);

        if (staffRequest.getLastName() == null || staffRequest.getLastName().isEmpty() || staffRequest.getLastName().isBlank())
            return new ResponseEntity<>("Apellido requerido", HttpStatus.BAD_REQUEST);

        if (staffRequest.getUser() == null || staffRequest.getUser().isEmpty() || staffRequest.getUser().isBlank())
            return new ResponseEntity<>("Usuario requerido", HttpStatus.BAD_REQUEST);

        if (staffRepository.findByUser(staffRequest.getUser().toLowerCase()) != null)
            return new ResponseEntity<>("Usuario en uso", HttpStatus.BAD_REQUEST);

        if (staffRequest.getPassword() == null || staffRequest.getPassword().isEmpty() || staffRequest.getPassword().isBlank())
            return new ResponseEntity<>("Contraseña requerida", HttpStatus.BAD_REQUEST);

        if (company == null){
            return new ResponseEntity<>("Compañia requerida", HttpStatus.BAD_REQUEST);
        }


        Staff newStaff = new Staff();
        newStaff.setDni(staffRequest.getDni());
        newStaff.setEmail(staffRequest.getEmail().toLowerCase());
        newStaff.setFirstName(staffRequest.getFirstName());
        newStaff.setLastName(staffRequest.getLastName());
        newStaff.setUser(staffRequest.getUser().toLowerCase());
        newStaff.setPassword(staffRequest.getPassword());
        newStaff.setUserType(staffRequest.getUserType());
        newStaff.setCompany(company);
        newStaff.setDeleted(false);
        staffRepository.save(newStaff);
        return new ResponseEntity<>(staffMapper.toDTO(newStaff), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateStaffByCompany(Authentication authentication,Long id, StaffRequest staffRequest) {
        Company company = companyRepository.findByUser(authentication.getName());
        Staff staff = staffRepository.findById(id).orElse(null);
        List<Staff> staffList = staffRepository.findAllByCompany(company);

        if (staffList.indexOf(staff) == -1)
            return new ResponseEntity<>("Miembro de staff no encontrado", HttpStatus.BAD_REQUEST);

        if (staffRequest.getDni() != null) {
            for (Staff staffE : staffList) {
                if (staffE.getDni().equals(staffRequest.getDni())) {
                    return new ResponseEntity<>("DNI en uso", HttpStatus.BAD_REQUEST);
                }
                else{
                    staff.setDni(staffRequest.getDni());
                }
            }
        }

        if (staffRequest.getFirstName() != null)
            if (!staff.getFirstName().equals(staffRequest.getFirstName()))
                staff.setFirstName(staffRequest.getFirstName());

        if (staffRequest.getLastName() != null)
            if (!staff.getLastName().equals(staffRequest.getLastName()))
                staff.setLastName(staffRequest.getLastName());

        if (staffRequest.getEmail() != null) {
            for (Staff staffE : staffList) {
                if ((staffE.getEmail().toLowerCase()).equals(staffRequest.getEmail().toLowerCase())) {
                    return new ResponseEntity<>("Email en uso", HttpStatus.BAD_REQUEST);
                }
                else{
                    staff.setEmail(staffRequest.getEmail().toLowerCase());
                }
            }
        }

        staffRepository.save(staff);
        return new ResponseEntity<>(staffMapper.toDTO(staff), HttpStatus.OK);
    }

}
