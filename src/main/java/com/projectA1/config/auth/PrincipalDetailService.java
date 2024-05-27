package com.projectA1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectA1.model.Owner;
import com.projectA1.model.User;
import com.projectA1.repository.OwnerRepository;
import com.projectA1.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 사용자 테이블에서 사용자 정보 조회
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // 비밀번호 암호화
            //user.setPassword(encodePassword(user.getPassword()));
            // 사용자 정보가 존재하면 PrincipalUser 객체를 생성하여 반환
            return new PrincipalUser(user);
        }

        // 소유자 테이블에서 소유자 정보 조회
        Owner owner = ownerRepository.findByEmail(email);

        if (owner != null) {
            // 비밀번호 암호화
        	//owner.setPassword(encodePassword(owner.getPassword()));
            // 소유자 정보가 존재하면 PrincipalOwner 객체를 생성하여 반환
            return new PrincipalUser(owner);
        }

        // 사용자 정보 또는 소유자 정보가 없을 경우 빈 객체 반환
        return new PrincipalUser(new User());
    }
    
//    private String encodePassword(String password) {
//        // 비밀번호 암호화
//        return bCryptPasswordEncoder.encode(password);
//    }
}
