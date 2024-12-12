package store.spacecl.mall.entity.order;

import jakarta.persistence.*;
import lombok.*;
import store.spacecl.mall.entity.product.Product;
import store.spacecl.mall.entity.user.Member;

@Getter @Setter @ToString
@Entity @Table(name = "ORDER")
@NoArgsConstructor @AllArgsConstructor //Delete after dev
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderNo;

    private Member member;  //maybe change member id
    private Product product;//maybe change product id



}
