package com.ms.user.producer;

import com.ms.user.dto.EmailDto;
import com.ms.user.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMethodEmail(User userModel){
        var emailDto = new EmailDto();
        emailDto.setId(userModel.getId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro Realizado com sucesso!");
        emailDto.setText("Bem vindo!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
