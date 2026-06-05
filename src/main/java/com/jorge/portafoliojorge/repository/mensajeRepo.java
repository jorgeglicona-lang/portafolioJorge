package com.jorge.portafoliojorge.repository;
import com.jorge.portafoliojorge.model.ContactoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mensajeRepo extends JpaRepository<ContactoDTO, Long>{

}
