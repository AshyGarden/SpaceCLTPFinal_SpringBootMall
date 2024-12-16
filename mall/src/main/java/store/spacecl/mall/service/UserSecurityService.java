package store.spacecl.mall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.spacecl.mall.entity.user.Member;
import store.spacecl.mall.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository; //これをimplimentsしたサービスは、postロジックがなくても実行されることになります

    @Override //ユーザーがいない場合
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> _member = memberRepository.findByUserName(username);

        if(_member.isEmpty()){
            throw new UsernameNotFoundException("ユーザーが存在しません.");
        }

        Member member = _member.get();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        if("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        //ユーザーの権限によって表示される画面が変わりますからユーザーを作ってリータン
        return new User(member.getUserName(), member.getPassword(), authorities);
    }

    //ユーザが存在する場合
    public Member getUser(String name) {
        Member member = null;
        Optional<Member> _member = memberRepository.findByUserName(name);
        if(_member.isPresent()) {
            member = _member.get();
        }

        return member;
    }
}
