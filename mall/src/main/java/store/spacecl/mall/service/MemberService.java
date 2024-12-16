package store.spacecl.mall.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.spacecl.mall.entity.user.Member;
import store.spacecl.mall.forms.MemberForm;
import store.spacecl.mall.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; //bean作ったからエラーなし

    public Member create(@Valid MemberForm memberForm) {

        Member member = null;

        //memberにない場合
        if(memberRepository.findByUserName(memberForm.getUserName()).isEmpty()) {
            member = new Member();
            member.setUserName(memberForm.getUserName());
            member.setEmail(memberForm.getEmail());
            member.setPassword(passwordEncoder.encode(memberForm.getPassword1()));

            memberRepository.save(member);
        }

        return member;
    }



}
