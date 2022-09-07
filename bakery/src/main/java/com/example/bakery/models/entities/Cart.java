package com.example.bakery.models.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ToString
@RequiredArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "cart_table")
public class Cart extends AbstractEntityId {
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CartItem> items = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
    private User user;

    public Cart(User user) {
        this.user = user;
    }

    public void addProduct(CartItem item) {
        Optional<CartItem> foundItem = this.items
                .stream()
                .filter(i -> i.getProductId().equals(item.getProductId()))
                .findFirst();
        if (foundItem.isPresent()) {
            CartItem i = foundItem.get();
            i.setQuantity(item.getQuantity() + i.getQuantity());
        } else {
            this.items.add(item);
        }
    }

    public void removeItem(Long itemId) {
        this.items.removeIf(item -> item.getId().equals(itemId));
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
