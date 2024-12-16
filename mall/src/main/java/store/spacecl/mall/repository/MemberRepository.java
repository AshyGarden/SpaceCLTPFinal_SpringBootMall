package store.spacecl.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.spacecl.mall.entity.user.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUserName(String userName); //ユーザー名で探す
}
