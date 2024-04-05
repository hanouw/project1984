package com.jpa4.pj1984.security.domain;

import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.StoreDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;

@Getter
public class CustomCms extends User {

    private StoreDTO store;

    public CustomCms(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomCms(Store store){
        super(store.getStoreLoginId(), store.getStorePassword(), Arrays.asList(new SimpleGrantedAuthority(store.getStoreStatus().getValue())));
        this.store = new StoreDTO(store);
    }

}
