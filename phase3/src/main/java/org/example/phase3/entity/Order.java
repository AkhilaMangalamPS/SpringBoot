package org.example.phase3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getProduct(){
        return product;
    }
    public void setProduct(String product){
        this.product = product;
    }

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

}
