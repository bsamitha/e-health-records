package com.healthcare.repo;

import com.healthcare.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository  extends JpaRepository<Consultation, Long> {
}
