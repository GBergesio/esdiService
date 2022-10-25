package esdi.Services.configurations;

import esdi.Services.enums.UserType;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.StaffRepository;
import esdi.Services.services.CompanyService;
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

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName -> {

//            Company company = companyRepository.findByEmail(inputName);
            Company company = companyRepository.findByUser(inputName);
//            Staff staff = staffRepository.findByEmail(inputName);

            if (company != null) {
                if (company.getEmail().contains("@ampersand.com") && company.getUserType() == UserType.SUPERADMIN){
                    return new User(company.getUser(), company.getPassword(),
                            AuthorityUtils.createAuthorityList("SUPERADMIN","ADMIN", "CLIENT","TECHNICIAN","COMPANY"));
                }
//                if (staff.getEmail().contains("@gmail.com") && staff.getUserType() == UserType.TECHNICIAN){
//                    return new User(staff.getUser(), staff.getPassword(),
//                            AuthorityUtils.createAuthorityList("TECHNICIAN"));
//                }
                else{
                    return new User(company.getUser(), company.getPassword(),
                            AuthorityUtils.createAuthorityList("COMPANY"));
                }
            } else {
                throw new UsernameNotFoundException("Compañia desconocida: " + inputName);
            }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
