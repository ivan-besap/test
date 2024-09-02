package com.eVolGreen.eVolGreen.Repositories;
import com.eVolGreen.eVolGreen.Models.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombre(@Param("nombre") String nombre); // Cambiado de "name" a "nombre"
}