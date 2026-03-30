package com.educert.repository;

import com.educert.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Optional<Certificate> findByHash(String hash);
}
