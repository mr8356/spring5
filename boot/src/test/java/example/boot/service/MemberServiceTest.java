package example.boot.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import example.boot.domain.Member;
import example.boot.repository.MemoryMemberRepository;

public class MemberServiceTest {
    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    // 테스트 케이스는 
    // Given when then 형식으로 작성

    @Test
    public void 회원가입(){
        // Given
        Member member = new Member();
        member.setName("spring");
        // When
        service.join(member);
        // Then
        Assertions.assertEquals(member, service.findOne(member.getId()).get());
    }

    @Test
    public void 중복_회원_예외(){
        // Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        // When
        service.join(member1);

        // Then
        Exception e = Assertions.assertThrows(IllegalStateException.class, () ->  service.join(member2));
        Assertions.assertEquals(e.getMessage(), "already Exists");
    }


}
