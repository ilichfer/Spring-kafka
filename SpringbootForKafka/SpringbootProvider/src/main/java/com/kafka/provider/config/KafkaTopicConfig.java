package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generaNewTopic(){

        Map<String, String> configuration = new HashMap<>();
        //Delete borra el mensaje
        //compact Mantiene el mas actual
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        //tiempo que se retiene el mensaje en miliosegundos viene por defecto en -1
        configuration.put(TopicConfig.RETENTION_MS_CONFIG,"864000");
        //la capacidad maxima del segmento - por defecto tiene un Giga
        configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        //la capacidad maxima de cada mensaje - por defecto tiene un MegaB
        configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"1000765");

        NewTopic newTopic =  TopicBuilder.name("dev-topic")
                .partitions(2)
                .replicas(1)
                .configs(configuration)
                .build();
        return newTopic;
    }
}
