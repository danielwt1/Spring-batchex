package com.example.batchrest.processor;

import com.example.batchrest.dto.PostDTO;
import com.example.batchrest.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PostProcessor implements ItemProcessor<PostDTO, Post> {

    private  static final Logger LOG= LoggerFactory.getLogger(PostProcessor.class);

    @Override
    public Post process(PostDTO postDTO) throws Exception {
        Post post=new Post();
        post.setId(postDTO.getId());
        post.setUserId(postDTO.getUserId());
        post.setTittle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        LOG.info("Convirtiendo  DTO ("+postDTO+") a ENTIDAD("+post+")");
        return post;
    }
}
