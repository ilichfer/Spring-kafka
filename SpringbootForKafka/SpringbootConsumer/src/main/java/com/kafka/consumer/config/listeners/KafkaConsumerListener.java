package com.kafka.consumer.config.listeners;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.kafka.consumer.dto.PersonaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = {"dev-topic"}, groupId = "my-group-id")
    public void listener(String jsonStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //LOGGER.info("Mensaje {} / para el registro", jsonStr);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        PersonaDto per = mapper.readValue(jsonStr, PersonaDto.class);
        LOGGER.info("el documento de la persona es {} / para el registro", per.getDocumento());
    }


}
