package com.rodrigues.restauranterodrigues.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesasRepository extends JpaRepository<MesasEntity, Integer> {
    MesasEntity findTopByOrderByIdDesc();
}
