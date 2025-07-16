package com.example.tugether_be.chat.bean.small;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CreateChatPageRequestBean {

    // pageRequest 객체 생성
    public PageRequest exec(int page, int size){
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "sendAt"));
    }
}
