package com.example.bakery.repositories.customized;

import com.example.bakery.models.entities.customized.LifeCelebration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeCelebrationRepository extends JpaRepository<LifeCelebration, Long> {
}
