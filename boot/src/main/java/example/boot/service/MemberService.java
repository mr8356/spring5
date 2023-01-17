package example.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.boot.domain.Member;
import example.boot.repository.MemberRepository;

// @Service
public class MemberService {
    
    private MemberRepository memberRepository;

    // @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        validateOverapMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복된 회원이 있는지 검증해주는 함수
    public void validateOverapMember(Member member){
        // Optional 객체 이므로 ifPresent 함수를 이용해서 null 조건문 없이 작성 할 수 있습니다
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("already Exists");
        });
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne (Long id){
        return memberRepository.findById(id);
    }

}
