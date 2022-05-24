package com.sahabt.candidatepool.repository;

import com.sahabt.candidatepool.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
