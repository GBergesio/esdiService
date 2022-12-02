package esdi.Services.services;

import esdi.Services.dtos.CompanyDTO;
import esdi.Services.enums.UserType;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Company company = companyRepository.findByUsername(username);
        Staff staff = staffRepository.findByUser(username);
        Client client = clientRepository.findByUser(username);

        if(company != null){
            if (company == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            return new org.springframework.security.core.userdetails.User(company.getUsername(), company.getPassword(),
                    AuthorityUtils.createAuthorityList("COMPANY"));
        }
        if(staff != null){
           if (staff.getUserType() == UserType.TECHNICIAN){
                return new User(staff.getUser(), staff.getPassword(),AuthorityUtils.createAuthorityList("TECHNICIAN"));
          }
           if (staff.getUserType() == UserType.ADMIN){
                return new User(staff.getUser(), staff.getPassword(),AuthorityUtils.createAuthorityList("ADMIN"));
           }
        }
        if(client != null){
            if (client == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            return new User(client.getUser(), client.getPassword(),AuthorityUtils.createAuthorityList("CLIENT"));
        }
        if(staff == null || client == null || company == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        else {
                throw new UsernameNotFoundException("Usuario desconocido: " + username);
        }

    }

    public Company save(CompanyDTO companyDTO) {
        Company newUser = new Company();
        newUser.setUsername(companyDTO.getUsername());
        newUser.setPassword(bcryptEncoder.encode(companyDTO.getPassword()));
        return companyRepository.save(newUser);
    }



}

//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// no borrar ↓
//        if(staff != null){
//                if (staff == null) {
//                throw new UsernameNotFoundException("User not found with username: " + username);
//                }
//                return new org.springframework.security.core.userdetails.User(staff.getUser(), staff.getPassword(),
//                new ArrayList<>());
//        }
