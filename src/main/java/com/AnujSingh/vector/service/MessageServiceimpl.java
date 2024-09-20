package com.AnujSingh.vector.service;

import com.AnujSingh.vector.model.Chat;
import com.AnujSingh.vector.model.Message;
import com.AnujSingh.vector.model.User;
import com.AnujSingh.vector.repository.ChatRepository;
import com.AnujSingh.vector.repository.MessageRepository;
import com.AnujSingh.vector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceimpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;
    @Override
    public Message sendMessage(Long senderId, Long chatId, String content) throws Exception {
        User sender = userRepository.findById(senderId).orElseThrow(()->
                new Exception("User not found with the id " + senderId ));

        Chat chat = projectService.getProjectById(chatId).getChat();

        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);

        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message> findByChatIdOrderByCreatedAtAsc =
                messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return findByChatIdOrderByCreatedAtAsc;
    }
}
