package com.vsiaf.api.repository;

import com.vsiaf.api.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
    boolean existsByCodOficina(String codOficina);
}