package com.healthcare.controllers;

import com.healthcare.error.UnAuthorize;
import com.healthcare.repo.ConsultationRepository;
import com.healthcare.error.NotFoundException;
import com.healthcare.model.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class ConsultationController {

    @Autowired
    private ConsultationRepository repository;

    // Find
//    @GetMapping("/reports")
//    List<Consultation> findAll() {
//        return repository.findAll();
//    }

    // Save
    @PostMapping("/reports")
    @ResponseStatus(HttpStatus.CREATED)
    Consultation newReport(@Valid @RequestBody Consultation newConsultation) {
        return repository.save(newConsultation);
    }

    // Find
    @GetMapping("/reports/{id}")
    Consultation findConsultation(@PathVariable @Min(1) Long id) {
        Consultation consultation = repository.findById(id).orElse(null);
        if (consultation == null) {
            throw new NotFoundException(id);
        } else if (((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().equals(consultation.getDoctor().getUser().getUserName())) {
            return consultation;
        } else {
            throw new UnAuthorize();
        }
    }

    // ToDO add auth
    @PutMapping("/reports/{id}")
    Consultation saveOrUpdate(@RequestBody Consultation newConsultation, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setDiagnosis(newConsultation.getDiagnosis());
                    x.setBloodPressure(newConsultation.getBloodPressure());
                    x.setDoctor(newConsultation.getDoctor());
                    x.setPatient(newConsultation.getPatient());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newConsultation.setId(id);
                    return repository.save(newConsultation);
                });
    }

    // ToDO add auth
    @DeleteMapping("/reports/{id}")
    void deleteReport(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
