package example.boot.repository;

import java.util.Optional;
import java.util.List;

import example.boot.domain.Member;

public interface MemberRepository {
    // 인터페이스란 기능에 대한 명세 집합
    // 공통적인 함수들을 정의하고 실질적인 repository에 상속 받는다

    // 멤버를 받으면 저장
    Member save(Member member);

    // id / name 으로 받고 member를 반환
    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    //여러 멤버들을 리스트에 담아서 묶음으로 반환
    List<Member> findAll();
}
