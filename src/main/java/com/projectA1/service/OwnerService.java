package com.projectA1.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectA1.config.auth.PrincipalUser;
import com.projectA1.model.FitnessCenter;
import com.projectA1.model.Owner;
import com.projectA1.repository.FitnessCenterRepository;
import com.projectA1.repository.OwnerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerService {
	
	private final OwnerRepository ownerRepository;
	private final FitnessCenterRepository fitnessCenterRepository;
	
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //비밀번호 암호화 때 사용
	private final PasswordEncoder passwordEncoder; //비밀번호 비교할때 사용

    private String encodePassword(String password) {
        // 비밀번호 암호화
        return bCryptPasswordEncoder.encode(password);
    }
    
	public boolean existsByEmail(String email) {
		return ownerRepository.existsByEmail(email);
	}
    
    ///////////////////////////////////////////////////////////////////////
	//센터추가하면서 owner상태변경
    @Transactional
    public void addFitnessCenterToOwner(Owner owner, FitnessCenter fitnessCenter) {
        // Owner 엔티티에 센터 번호 등록
        owner.setFitnessCenter(fitnessCenter);    
        // Owner 엔티티를 저장하고 데이터베이스에 반영
        ownerRepository.save(owner);
    }
	
	//회원가입
	public void join(Owner owner) {
		owner.setPassword(encodePassword(owner.getPassword()));
		ownerRepository.save(owner);
	}
	
	//오너(상세보기)
	public Optional<Owner> view(long id) {
		return ownerRepository.findById(id);
	}
	
	//수정 (더티체킹) ==> 전화번호, 주소, 사업자 이름
	@Transactional
	public void update(Owner currentOwner, Owner updateOwner)
	{
        // 새로운 비밀번호가 입력되었는지 확인
        if (!updateOwner.getPassword().isEmpty()) {
            // 입력된 새로운 비밀번호가 현재 비밀번호와 같은지 확인
            if (passwordEncoder.matches(updateOwner.getPassword(), currentOwner.getPassword())) {
                // 현재 비밀번호와 새로운 비밀번호가 같으면 오류 처리
                throw new IllegalArgumentException("새로운 비밀번호는 현재 비밀번호와 다르게 설정해야 합니다.");
            }
             //새로운 비밀번호를 암호화하여 저장
            currentOwner.setPassword(encodePassword(updateOwner.getPassword()));
        }
        currentOwner.setName(updateOwner.getName());
        currentOwner.setPhoneNumber(updateOwner.getPhoneNumber());
        currentOwner.setAddress(updateOwner.getAddress());
        //센터이름변경
        
		//저장완료
        ownerRepository.save(currentOwner);
		
		// 세션 업데이트
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
		principalUser.setUser(currentOwner); // 새로운 사용자 정보로 Principal 업데이트
        
	}
	
	//center Id 초기화 => 센터DB삭제위함
	public void clearCenterId(Owner owner) {
		owner.setFitnessCenter(null);
		ownerRepository.save(owner);
	}
	//삭제
	public void delete(Long id) {
		ownerRepository.deleteById(id);
	}
	
	
}
