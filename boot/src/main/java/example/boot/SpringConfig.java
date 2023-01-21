package example.boot;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.boot.repository.JdbcMemberRepository;
import example.boot.repository.JpaMemberRepository;
import example.boot.repository.MemberRepository;
// import example.boot.repository.MemoryMemberRepository;
import example.boot.service.MemberService;


@Configuration
public class SpringConfig {
    
    private final DataSource dataSource;
    //JPA EntityManager추가
    public final EntityManager em;
    
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        // 추가
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    
}