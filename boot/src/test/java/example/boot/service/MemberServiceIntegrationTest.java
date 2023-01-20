package example.boot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import example.boot.domain.Member;
import example.boot.repository.MemberRepository;

// 스프링부트 컨테이너 이용한 테스트
@SpringBootTest
// 테스트 할때마다 DB 롤백해서 영향X
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원_가입(){
        //Given
        Member member = new Member();
        member.setName("spring1");
        //Then
        Long id = memberService.join(member);
        // When
        Assertions.assertEquals(member.getName(), memberService.findOne(id).get().getName());
    }

    @Test
    public void 중복_회원_예외(){
        // Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        
        // When
        memberService.join(member1);

        // Then
        Exception e = Assertions.assertThrows(IllegalStateException.class, () ->  memberService.join(member2));
        Assertions.assertEquals(e.getMessage(), "already Exists");
    }
}
