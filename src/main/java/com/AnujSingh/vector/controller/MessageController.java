package com.AnujSingh.vector.controller;

import com.AnujSingh.vector.model.Chat;
import com.AnujSingh.vector.model.Message;
import com.AnujSingh.vector.model.User;
import com.AnujSingh.vector.request.CreateMessageRequest;
import com.AnujSingh.vector.service.MessageService;
import com.AnujSingh.vector.service.ProjectService;
import com.AnujSingh.vector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request)
            throws Exception {
        User user = userService.findUserById(request.getSenderId());
        if(user == null) {
            throw new Exception("User not found with the id " + request.getSenderId());
        }
        Chat chat = projectService.getChatByProjectId(request.getProjectId());
        if(chat == null) {
            throw new Exception("Chat not found with the id " + request.getProjectId());
        }
        Message sendMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(),
                request.getContent());
        return ResponseEntity.ok(sendMessage);
    }


    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId)
            throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }

}
