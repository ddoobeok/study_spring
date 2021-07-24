package OneStep.hellospring.repository;

import OneStep.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("test member");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();    // return type이 Optional 이므로 get 한번 더
        //Assertions.assertEquals("무야호", result);    // 비교. 출력은 없지만 run했을 때 build output에 결과 보임 // org.junit
        assertThat(member).isEqualTo(result);    // org.assertj

    }

    @Test
    public void findByName(){
        Member m1 = new Member();
        m1.setName("test1");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("test2");
        repository.save(m2);

        Member result = repository.findByName("test1").get();

        assertThat(result).isEqualTo(m1);
        //assertThat(result).isEqualTo(m2);
    }

    @Test
    public void findAll(){
        Member m1 = new Member();
        m1.setName("test1");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("test2");
        repository.save(m2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
