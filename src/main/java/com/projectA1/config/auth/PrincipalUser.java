package com.projectA1.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projectA1.model.Owner;
import com.projectA1.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrincipalUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//범용 user
    private Object user;

    // 사용자 객체 반환
    public Object getUser() {
        return user;
    }
    public PrincipalUser(Object user) {
        this.user = user;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof User) {
            User currentUser = (User) user;
            for (String userRole : currentUser.getRole()) {
                authorities.add(new SimpleGrantedAuthority(userRole));
            }
        } else if (user instanceof Owner) {
            Owner currentOwner = (Owner) user;
            for (String ownerRole : currentOwner.getRole()) {
                authorities.add(new SimpleGrantedAuthority(ownerRole));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        if (user instanceof User) {
            return ((User) user).getPassword();
        } else if (user instanceof Owner) {
            return ((Owner) user).getPassword();
        }
        return null;
    }


    //이름반환(진짜이름)
    @Override
    public String getUsername() {
        if (user instanceof User) {
            return ((User) user).getName();
        } else if (user instanceof Owner) {
            return ((Owner) user).getName();
        }
        return null;
    }
    
    //아이디반환
    public String getUserEmail() {
        if (user instanceof User) {
            return ((User) user).getEmail();
        } else if (user instanceof Owner) {
            return ((Owner) user).getEmail();
        }
        return null;
    }
    
    //고유아이디(id)반환
    public Long getUserId() {
        if (user instanceof User) {
            return ((User) user).getId();
        } else if (user instanceof Owner) {
            return ((Owner) user).getId();
        }
        return null;
    }
    
    // 추가된 메소드들
    public String getAddress() {
        if (user instanceof User) {
            return ((User) user).getAddress();
        } else if (user instanceof Owner) {
            return ((Owner) user).getAddress();
        }
        return null;
    }

    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
