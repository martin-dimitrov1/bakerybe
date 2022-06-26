package com.example.bakery.models.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@RequiredArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "cart_table")
public class Cart extends AbstractEntityId {
    @OneToMany(cascade = CascadeType.DETACH)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
    private User user;

    public Cart(User user) {
        this.user = user;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Long productId) {
        this.products.removeIf(product -> product.getId().equals(productId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cart cart = (Cart) o;
        return getId() != null && Objects.equals(getId(), cart.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
