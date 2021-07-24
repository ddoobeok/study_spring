package OneStep.hellospring.service;

import OneStep.hellospring.domain.Member;
import OneStep.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// main class에서 command + shift + t --> Test 한번에 만들 수 있음
// Test 할 때 머리가슴배 마냥 given/when/then 으로 나눠서 하면 명료함
// given : 이 데이터로 / when : 이런 경우를 / then : 검증하는 부분
class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();   // static이라 이 상황에서 가능은 함..

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        // beforeEach 에서 초기화
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member m1 = new Member();
        m1.setName("same name");

        // when
        Long savedID = memberService.join(m1);

        // then
        Member foundMember = memberService.findOne(savedID).get();
        Assertions.assertThat(m1.getName()).isEqualTo(foundMember.getName());

    }

    @Test
    void 중복_회원_예외(){
        // given
        Member m1 = new Member();
        m1.setName("same name");

        Member m2 = new Member();
        m2.setName("same name");

        // when
        memberService.join(m1);
        // 1번 방법 - try-catch
//        try {
//            memberService.join(m2);
//            fail("이름 중복으로 예외 발생 해야함 !");
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
//        }

        // 2번 방법 assertThrows( 이 예외가 throw 되어야 한다, () -> 이거 실행 했을 때)
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));
        //assertThrows(NullPointerException.class, () -> memberService.join(m2));   // 실패함

        // 에러 메시지 내용 검증. option+command+v 로 리턴 받아서 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}