package com.example.bakery.models.entities;

import com.example.bakery.models.CartForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class Order extends AbstractEntityId {
    @OneToOne(cascade = CascadeType.DETACH)
    private User forUser;

    private String products;

    private String billingAddress;
    private Date toDate;

    public Order(Cart cart, CartForm form) {
        this.forUser = cart.getUser();
        this.billingAddress = form.address();
        this.products = cart.getItems().get(0).toString();
        this.toDate = form.toDate();
    }
}
