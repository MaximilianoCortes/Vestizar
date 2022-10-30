package com.example.vestizar.chat.servicio.repositorio;

import com.example.vestizar.chat.modelos.Conversacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConversacionRepositorio extends MongoRepository<Conversacion, String> {
    Optional<Conversacion> findById_RemitenteAndId_Receptor(String idRemitente, String recipientId);
}