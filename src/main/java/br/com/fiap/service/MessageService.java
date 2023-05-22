package br.com.fiap.service;

import br.com.fiap.controller.AuthController;
import br.com.fiap.entity.Message;
import br.com.fiap.entity.User;
import br.com.fiap.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final GptService gptService;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, GptService gptService) {
        this.messageRepository = messageRepository;
        this.gptService = gptService;
    }

    public String sendMessage(Message message) {
        String answer = gptService.sendMessageGpt(message.getQuestion());
        message.setAnswer(answer);
        messageRepository.save(message);
        return answer;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public List<Message> findByUserId(Long id) {
        return messageRepository.findMessageByUserId(id);
    }

    public Message findByIdAndUserId(Long messageId, Long userId) {
        return messageRepository.findByIdAndUserId(messageId, userId);
    }

    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }
}
