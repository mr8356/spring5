package example.boot.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import example.boot.domain.Member;

public class MemoryMemberRepositoryTest {
    // MemoryMemberRepository 클래스를 테스트를 하는 것이므로 의존성 끌어옴
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 이 클래스의 여러 테스트 케이스는 순서가 랜덤이므로 잘못될수 있습니다.
    // @AfterEach는 각 테스트케이스 이후에 실행할 내용을 정의할 수 있습니다.
    // 각 테스트 케이스 이후에 respotory를 초기화 하는 것입니다.
    @AfterEach
    public void clear(){
        repository.clearStore();
    }

    // 테스트 케이스마다 @Test 붙여주면 실행 가능
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findById() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Assertions.assertEquals(member1, repository.findById(member1.getId()).get());
        Assertions.assertEquals(member2, repository.findById(member2.getId()).get());
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring3");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring4");
        repository.save(member2);
        Assertions.assertEquals(member1, repository.findByName(member1.getName()).get());
        Assertions.assertEquals(member2, repository.findByName(member2.getName()).get());
    }

    @Test
    public void findAll(){
        int num = 10;
        for (int i = 0; i < num; i++) {
            Member tempMember = new Member();
            String name = "spring" + Integer.toString(i);
            tempMember.setName(name);
            repository.save(tempMember);
        }
        Assertions.assertEquals(repository.findAll().size(), num);
    }
}
