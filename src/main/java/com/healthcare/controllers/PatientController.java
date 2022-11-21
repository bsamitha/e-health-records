package com.healthcare.controllers;

import com.healthcare.repo.PatientRepository;
import com.healthcare.error.NotFoundException;
import com.healthcare.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class PatientController {

    @Autowired
    private PatientRepository repository;

    // Find
    @GetMapping("/patients")
    List<Patient> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    Patient createPatient(@Valid @RequestBody Patient newPatient) {
        return repository.save(newPatient);
    }

    // Find
    @GetMapping("/patients/{id}")
    Patient findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    // Save or update
    @PutMapping("/patients/{id}")
    Patient saveOrUpdate(@RequestBody Patient newPatient, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setFirstName(newPatient.getFirstName());
                    x.setLastName(newPatient.getLastName());
                    x.setGender(newPatient.getGender());
                    x.setBirthDate(newPatient.getBirthDate());
                    x.setContactNo(newPatient.getContactNo());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newPatient.setId(id);
                    return repository.save(newPatient);
                });
    }

    @DeleteMapping("/patients/{id}")
    void deletePatient(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
