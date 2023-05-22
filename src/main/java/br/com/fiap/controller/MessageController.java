package br.com.fiap.controller;

import br.com.fiap.entity.Message;
import br.com.fiap.entity.User;
import br.com.fiap.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public String sendMessage(@RequestBody Message message) {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       message.setUser(user);
       return messageService.sendMessage(message);
    }

    @GetMapping
    public List<Message> getMessages() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageService.findByUserId(user.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message message = messageService.findByIdAndUserId(id, user.getId());

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        messageService.deleteMessage(message);
        return ResponseEntity.ok().build();
    }

}
