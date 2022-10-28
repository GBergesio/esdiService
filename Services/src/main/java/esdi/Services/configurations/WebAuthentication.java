package esdi.Services.configurations;

import esdi.Services.enums.UserType;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName -> {

            Company company = companyRepository.findByUser(inputName);
            Staff staff = staffRepository.findByUser(inputName);
            Client client = clientRepository.findByUser(inputName);

            if (company != null) {
                if (company.getEmail().contains("@ampersand.com") && company.getUserType() == UserType.SUPERADMIN){
                    return new User(company.getUser(), company.getPassword(),
                            AuthorityUtils.createAuthorityList("SUPERADMIN","ADMIN", "CLIENT","TECHNICIAN","COMPANY"));
                }
                if (company.getUser().contains("C-") && company.getUserType() == UserType.COMPANY){
                    return new User(company.getUser(), company.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN","TECHNICIAN","COMPANY"));
                }
            }
            if(staff != null){
                if (staff.getEmail().contains("@tt.com") && staff.getUserType() == UserType.TECHNICIAN){
                    return new User(staff.getUser(), staff.getPassword(),
                            AuthorityUtils.createAuthorityList("TECHNICIAN"));
                }
                if (staff.getEmail().contains("@aa.com") && staff.getUserType() == UserType.TECHNICIAN){
                    return new User(staff.getUser(), staff.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }
            }
            if(client != null){
                    return new User(client.getUser(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("CLIENT"));
            }
            else {
                throw new UsernameNotFoundException("Usuario desconocido: " + inputName);
            }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
