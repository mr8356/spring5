package example.boot.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;


import example.boot.domain.Member;

public class MemoryMemberRepository implements MemberRepository {

    // 아직 데이터베이스를 연결 안했으므로 간이로 데이터베이스(store해시맵) 과 id를 일일히 할당해줍시다
    // 데이터베이스가 자동으로 id를 할당하지만 아직 연결 안했으므로 id  1씩 증가하면서 하겠습니다.
    private static Map<Long, Member> store = new HashMap<Long,Member>();
    private static Long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Optional은 null 반환할 수도 있는데 그대로 반환 시킨다. -> 프론트에서 null 처리
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // value들, 즉 멤버들을  stream으로 하나씩 확인하는데 filter 람다식으로 이름이 같은것만 고릅니다
        // findAny로 일치하는 것을 반환합니다.
        return store.values().stream().filter(m -> m.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        // 저장된 값들을 arrayList로 변환해서 반응
        return new ArrayList<Member>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
    
}
