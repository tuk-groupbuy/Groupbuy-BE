package com.example.tugether_be.post.bean.small;

import com.example.tugether_be.chat.domain.DTO.RequestChatRoomsGetDTO;
import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostDAOBean {

    PostRepositoryJPA postRepositoryJPA;

    @Autowired
    public GetPostDAOBean(PostRepositoryJPA postRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
    }

    public PostDAO exec(Long postId){
        return postRepositoryJPA.findByPostId(postId);
    }
}