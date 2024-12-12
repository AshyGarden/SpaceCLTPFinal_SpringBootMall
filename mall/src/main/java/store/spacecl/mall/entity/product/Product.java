package store.spacecl.mall.entity.product;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@Entity @Table(name = "PRODUCT")
@NoArgsConstructor @AllArgsConstructor //Delete after dev
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productNo;

    @Column(unique = true, nullable = false)
    private String productName;

    @Column(nullable = false)
    private int productPrice;

    @Column(nullable = false)
    private String productDetail;


}
