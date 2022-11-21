package com.healthcare;

import com.healthcare.model.Consultation;
import com.healthcare.model.Doctor;
import com.healthcare.model.Patient;
import com.healthcare.model.User;
import com.healthcare.repo.ConsultationRepository;
import com.healthcare.repo.DoctorRepository;
import com.healthcare.repo.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Profile("health")
    @Bean
    CommandLineRunner initDatabase(PatientRepository repository, DoctorRepository doctorRepository, ConsultationRepository consultationRepository) {
        return args -> {
//            repository.save(new Patient("samitha", "basnayake", "male", "1992.06.19", "+94774037492"));
//            doctorRepository.save(new Doctor("sam", "basnayake", "+94774037492", new User("sam", "pass", "ADMIN")));
            consultationRepository.save(new Consultation("fever", "-",
                    new Doctor("sam", "basnayake", "+94774037492", new User("sam", "pass", "ADMIN")),
                    new Patient("samitha", "basnayake", "male", "1992.06.19", "+94774037492")));
        };
    }
}