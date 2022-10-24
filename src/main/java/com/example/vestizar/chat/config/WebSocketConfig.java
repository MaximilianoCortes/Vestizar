package com.example.vestizar.chat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker( "/usuario");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/usuario");
    }
    //un message broker con la memoria de ejecucion con un mismo destino para enviar y recibir mensajes, el destino es /usuario
    //el /app para mensajes con metodos que tengan @MessageMapping

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }
    //registra un endpoint ws (websocket), es usado para que los clientes se conecten al servidor stomp (simple text orientated messaging protocol) y habilita opcion
    // de conexion con sockjs en caso que websocket no este disponible

/* Este metodo lo queda desactivado debido a que fue creado cuando se planteo usar mongoDB como base de datos en vez de mysql

@Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }
    //este metodo convierte los mensajes a JSON

 */
}
