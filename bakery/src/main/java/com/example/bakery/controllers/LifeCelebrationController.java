package com.example.bakery.controllers;

import com.example.bakery.models.entities.customized.LifeCelebration;
import com.example.bakery.services.LifeCelebrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/celebration")
@CrossOrigin(origins = "http://localhost:3000")
public class LifeCelebrationController {
    private final LifeCelebrationService lifeCelebrationService;

    @GetMapping("/getAll")
    public List<LifeCelebration> getAll() {
        return lifeCelebrationService.getAll();
    }

    @PostMapping("/insert")
    public LifeCelebration insert(@RequestBody LifeCelebration celebration) {
        return lifeCelebrationService.insert(celebration);
    }

    @PutMapping("/update")
    public LifeCelebration update(@RequestBody LifeCelebration celebration) {
        return lifeCelebrationService.update(celebration);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        lifeCelebrationService.delete(id);
    }
}
