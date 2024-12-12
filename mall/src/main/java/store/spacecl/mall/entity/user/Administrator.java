package store.spacecl.mall.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@Entity @Table(name = "ADMINISTRATOR")
@NoArgsConstructor @AllArgsConstructor //Delete after dev
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String password;

    private String email;

}
