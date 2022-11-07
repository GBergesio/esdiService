//package esdi.Services.security;
//
//import esdi.Services.models.users.Company;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//
//@AllArgsConstructor
//public class UserDetailsImpl implements UserDetails {
//
//    private final Company company;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
////        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
//        return Collections.emptyList();
//    }
//
//    @Override
//    public String getPassword() {
//        return company.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return company.getUser();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public String getNombre(){
//        return company.getName();
//    }
//
//    public boolean isActive(){
//        return true;
//    }
//
//}

