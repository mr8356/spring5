package example.boot.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import example.boot.domain.Member;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // PK 즉 필수 키가 아니면 중복과 부재의 문제가 있으므로 쿼리를 작성해야함
        List<Member> result = em.createQuery("select m from members m where m.name = :name",Member.class)
                            .setParameter("name", name)
                            .getResultList();
        return result.stream().findAny();
        
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from members m",Member.class)
                .getResultList();
    }
    
}