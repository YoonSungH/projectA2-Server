package com.projectA1.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectA1.config.auth.PrincipalUser;
import com.projectA1.model.User;
import com.projectA1.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder; //비밀번호 비교할때 사용
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //비밀번호 암호화 때 사용
    
    private String encodePassword(String password) {
        // 비밀번호 암호화
        return bCryptPasswordEncoder.encode(password);
    }

	// user 로그인확인
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// user 추가
	public void join(User user) {
        // 비밀번호 암호화
        user.setPassword(encodePassword(user.getPassword()));
		userRepository.save(user);
	}

	// user 상세보기
	public Optional<User> view(long id) {
		return userRepository.findById(id);
	}

	// user 수정 (더티체킹) => 전화번호, 주소, 이메일, 비밀번호 수정가능
	@Transactional
	public void update(User currentUser, User updateUser) {
        // 새로운 비밀번호가 입력되었는지 확인
       if (!updateUser.getPassword().isEmpty()) {
            // 입력된 새로운 비밀번호가 현재 비밀번호와 같은지 확인
            if (passwordEncoder.matches(updateUser.getPassword(), currentUser.getPassword())) {
                // 현재 비밀번호와 새로운 비밀번호가 같으면 오류 처리
                throw new IllegalArgumentException("새로운 비밀번호는 현재 비밀번호와 다르게 설정해야 합니다.");
            }
             //새로운 비밀번호를 암호화하여 저장
            currentUser.setPassword(encodePassword(updateUser.getPassword()));
        }
		
		currentUser.setName(updateUser.getName());
		currentUser.setPhoneNumber(updateUser.getPhoneNumber());
		currentUser.setAddress(updateUser.getAddress());

		//저장완료
		userRepository.save(currentUser);
		System.out.println("서비스에서 업데이트 완료");
		// 세션 업데이트
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
		principalUser.setUser(currentUser); // 새로운 사용자 정보로 Principal 업데이트*/
	}

	// user 삭제
	@Transactional
	public void delete(String email) {
		userRepository.deleteByEmail(email);
	}


	public Optional<User> findById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
}
