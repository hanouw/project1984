package com.jpa4.pj1984.security;

import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.repository.CmsCustomRepositoryImpl;
import com.jpa4.pj1984.security.domain.CustomCms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomCmsSecurityService implements UserDetailsService {

    private final CmsCustomRepositoryImpl cmsCustomRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("******* CustomCrmSecurityService UserDetail");
        Store store = cmsCustomRepository.findByStoreLoginId(username); //.orElseThrow(()-> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));
        if(store == null){
            throw new UsernameNotFoundException("해당 관리자가 없습니다.");
        }
        CustomCms customCms = new CustomCms(store);
        log.info("******* customCms Authorities = {}",customCms.getAuthorities());
        return customCms;
    }

}
