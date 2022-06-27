package com.example.bakery.models.entities.customized;

import com.example.bakery.models.entities.AbstractEntityId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "life_celebration_table")
public class LifeCelebration extends AbstractEntityId {
    private String name;

    public void update(LifeCelebration celebration) {
        if (celebration.getName() != null) this.setName(celebration.getName());
    }
}
