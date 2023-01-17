package example.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.boot.repository.MemberRepository;
import example.boot.repository.MemoryMemberRepository;
import example.boot.service.MemberService;

/**
 * SpringConfig
 */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    
}