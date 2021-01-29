package com.apt612.depaybackend.controller.api;

import com.apt612.depaybackend.controller.dto.MapperUtils;
import com.apt612.depaybackend.controller.dto.MessageDto;
import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import com.apt612.depaybackend.model.Message;
import com.apt612.depaybackend.service.MessageService;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/{userId}/message")
public class MessageApi extends BaseApi{
    MessageService messageService;

    @Autowired
    public MessageApi(Mapper mapper, MessageService messageService) {
        super(mapper);
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    @Authenticated
    public ResponseEntity<MessageDto> getMessageById(@PathVariable("id") String id) {
        Message message = messageService.getMessageById(id);
        if (message != null) {
            MessageDto messageDto = map(message, MessageDto.class);
            return new ResponseEntity<>(messageDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Authenticated
    public ResponseEntity<MessageDto> createMessage(@RequestBody Message message) {
        Message messageCreated = messageService.createMessage(message);
        if (messageCreated != null) {
            MessageDto messageDto = map(messageCreated, MessageDto.class);
            return new ResponseEntity<>(messageDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @Authenticated
    public ResponseEntity<List<MessageDto>> getMessagesByReceiver(@PathVariable(required = true) String userId) {
        List<Message> messageList = messageService.getMessagesByReceiver(userId);
        List<MessageDto> messageDtoList = mapList(messageList, MessageDto.class);
        return new ResponseEntity<>(messageDtoList, HttpStatus.OK);
    }

    @GetMapping("/unread")
    public ResponseEntity<Integer> getUnreadNumberByUserId(@PathVariable() String userId) {
        Integer unread = messageService.getUnreadNumberByUserId(userId);
        return new ResponseEntity<>(unread, HttpStatus.OK);
    }

    @GetMapping("/conversion/{objectUserId}")
    public ResponseEntity<List<MessageDto>> getSortedConversation(@PathVariable String userId, @PathVariable String objectUserId) {
        List<Message> messageList = messageService.getSortedConversation(userId, objectUserId);
        List<MessageDto> messageDtoList = mapList(messageList, MessageDto.class);
        return new ResponseEntity<>(messageDtoList, HttpStatus.OK);
    }
}
