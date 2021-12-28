package ru.yegorr.ms;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

/**
 * User: egorr<br>
 * Date: 28.12.2021<br>
 * Time: 1:13<br>
 * todo name and feature
 */
@Configuration
public class RabbitConfiguration {
    @Bean
    public Queue audioDownloadQueue(@Value("${queue.download-audio-queue}") String queueName) {
        return new Queue(queueName);
    }
}
