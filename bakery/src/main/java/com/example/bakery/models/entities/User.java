package com.example.bakery.models.entities;

import com.example.bakery.models.RegistrationUser;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
