package org.example.lab520202269.Repository;

import org.example.lab520202269.Entity.Persona;
import org.example.lab520202269.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugaresRepository extends JpaRepository<Lugares,Integer> {

}
