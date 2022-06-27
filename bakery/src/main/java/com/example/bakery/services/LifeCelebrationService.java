package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.entities.customized.LifeCelebration;
import com.example.bakery.repositories.customized.LifeCelebrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LifeCelebrationService {
    private final LifeCelebrationRepository lifeCelebrationRepository;

    public List<LifeCelebration> getAll() {
        return lifeCelebrationRepository.findAll();
    }

    public LifeCelebration insert(LifeCelebration celebration) {
        return lifeCelebrationRepository.save(celebration);
    }

    public LifeCelebration update(LifeCelebration celebration) {
        LifeCelebration lifeCelebration = lifeCelebrationRepository.findById(celebration.getId())
                .orElseThrow(() -> new CustomException("Cannot update non-existing celebration with id: " + celebration.getId()));
        lifeCelebration.update(celebration);
        return lifeCelebration;
    }

    public void delete(Long id) {
        LifeCelebration lifeCelebration = lifeCelebrationRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cannot update non-existing celebration with id: " + id));
        lifeCelebrationRepository.delete(lifeCelebration);
    }
}
