package com.Inventory.inventory.kafka;

import com.base.base.dto.OrderEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventDto.class);

    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "spring.kafka.consumer.group-id"
    )
    public void consume(OrderEventDto orderEventDto) {
        LOGGER.info(String.format("receive order event -> %s", orderEventDto));
    }


}
