package OneStep.hellospring.service;

import OneStep.hellospring.domain.Member;
import OneStep.hellospring.repository.MemberRepository;
import OneStep.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // repository는 외부에서 인스턴스 new 할 때 넣어주도록 바꿈 => DI(Dependency Injection) 다음 시간에 자세히 알아볼 것임
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){

        validateDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    // 코드 블럭 잡고 command+option+m --> 메서드로 빼줌
    private void validateDuplicatedMember(Member member) {
        //Optional<Member> byName = memberRepository.findByName(member.getName());  // option+command+v
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {   // return할 때 Optional로 감쌌기 때문에 이런것도 사용 가능
                    throw new IllegalStateException("이미 존재하는 회원 입니다.");
                });
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID){
        return memberRepository.findById(memberID);
    }
}
