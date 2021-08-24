/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.service;


import cz.fim.uhk.lab.model.SecuredPersonInfo;
import cz.fim.uhk.lab.repository.PersonRepository;
import cz.fim.uhk.lab.repository.SecuredPersonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/*
    UserDetailsServiceImpl is an implementation of a Spring Security service with user roles
    customized to fit the project.
 */


@Service("CustomUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SecuredPersonInfoRepository securedPersonInfoRepository;

    @Autowired
    UsernameResolverService usernameResolverService;

    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Long userid = usernameResolverService.findIdByUsername(username);
        SecuredPersonInfo user = personRepository.findById(userid).get().getSecuredDetails();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (user.getUserrole().equals("ROLE_ADMIN")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}

