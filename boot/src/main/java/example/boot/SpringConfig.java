package example.boot;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.boot.repository.JdbcMemberRepository;
import example.boot.repository.MemberRepository;
// import example.boot.repository.MemoryMemberRepository;
import example.boot.service.MemberService;


@Configuration
public class SpringConfig {
    
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
    
}