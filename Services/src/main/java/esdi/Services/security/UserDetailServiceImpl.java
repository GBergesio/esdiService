//package esdi.Services.security;
//import esdi.Services.models.users.Company;
//import esdi.Services.repositories.CompanyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//    @Autowired
//    private CompanyRepository companyRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//      Company company =  companyRepository.findOneByUser(username)
//        .orElseThrow(() -> new UsernameNotFoundException("El usuario con el nombre de usuario " + username + " no existe"));
//      return new UserDetailsImpl(company);
//    }
//
//}

