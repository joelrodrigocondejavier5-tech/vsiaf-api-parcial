package com.vsiaf.api.repository;

import com.vsiaf.api.model.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    long countByOficinaId(Long oficinaId);
    boolean existsByCi(String ci);
    List<Responsable> findByOficinaId(Long oficinaId);
}