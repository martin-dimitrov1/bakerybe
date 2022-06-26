package com.example.bakery.models.entities;

import com.example.bakery.models.RegistrationUser;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User extends AbstractEntityId {
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String email;
    private String role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Cart cart = new Cart(this);

    public User(RegistrationUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = "USER";
    }

    public void update(User user) {
        if (user.getPassword() != null) this.setPassword(user.getPassword());
        if (user.getEmail() != null) this.setEmail(user.getEmail());
    }

    public void addProductToCart(Product product) {
        this.cart.addProduct(product);
    }

    public void removeProductFromCart(Long productId) {
        this.cart.removeProduct(productId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "email = " + email + ", " +
                "role = " + role + ")";
    }
}
