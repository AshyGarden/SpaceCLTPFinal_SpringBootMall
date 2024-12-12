package store.spacecl.mall.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@Entity @Table(name = "MEMBER")
@NoArgsConstructor @AllArgsConstructor //Delete after dev
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    private String password;
    private String email;

}
