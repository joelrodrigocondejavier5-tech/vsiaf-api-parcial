package com.vsiaf.api.repository;

import com.vsiaf.api.model.UnidadAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAdministrativaRepository extends JpaRepository<UnidadAdministrativa, Long> {
    boolean existsByCodUnidad(String codUnidad);
}