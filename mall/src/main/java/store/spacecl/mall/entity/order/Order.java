package store.spacecl.mall.entity.order;

import jakarta.persistence.*;
import lombok.*;
import store.spacecl.mall.entity.product.Product;
import store.spacecl.mall.entity.user.Member;

@Getter @Setter @ToString
@Entity @Table(name = "ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderNo;

    private Member member;  //メンバーIDを変更する可能性がある (maybe change member id)
    private Product product;//製品IDを変更する可能性がある (maybe change product id)



}
