package com.vsiaf.api.repository;

import com.vsiaf.api.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
    Optional<Oficina> findFirstByCodOficina(String codOficina);
    boolean existsByCodOficina(String codOficina);
}