package com.example.vestizar.repositorio;



import com.example.vestizar.entidad.MensajeChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MensajeRepositorio
        extends JpaRepository<MensajeChat, Long> {

    long countByRemitenteAndReceptor(
            Long idRemitente, Long IdReceptor);

    List<MensajeChat> findByConversacion(String idConversacion);
}
