package com.healthcare.controllers;

import com.healthcare.repo.DoctorRepository;
import com.healthcare.error.NotFoundException;
import com.healthcare.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    // Find
    @GetMapping("/doctors")
    List<Doctor> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    Doctor newDoctor(@Valid @RequestBody Doctor newDoctor) {
        return repository.save(newDoctor);
    }

    // Find
    @GetMapping("/doctors/{id}")
    Doctor findDoctor(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    // Save or update
    @PutMapping("/doctors/{id}")
    Doctor saveOrUpdate(@RequestBody Doctor newDoctor, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setFirstName(newDoctor.getFirstName());
                    x.setLastName(newDoctor.getLastName());
                    x.setContactNo(newDoctor.getContactNo());
                    x.setUser(newDoctor.getUser());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newDoctor.setId(id);
                    return repository.save(newDoctor);
                });
    }

    @DeleteMapping("/doctors/{id}")
    void deleteDoctor(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
