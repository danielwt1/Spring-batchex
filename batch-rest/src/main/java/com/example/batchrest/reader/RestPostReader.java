package com.example.batchrest.reader;

import com.example.batchrest.dto.PostDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



import java.util.Arrays;
import java.util.List;

public class RestPostReader implements ItemReader<PostDTO> {
    private  final String API_URL;
    private final RestTemplate restTemplate;
    private int nextPostIndex;
    private List<PostDTO> postData;

    public RestPostReader(String API_URL, RestTemplate restTemplate) {
        this.API_URL = API_URL;
        this.restTemplate = restTemplate;
        nextPostIndex=0;
    }

    @Override
    public PostDTO read() throws Exception {
        if(isReadDataPost()){
            postData=readDataPostFromApiRest();
        }
        PostDTO nextPost=null;
        if(nextPostIndex<postData.size()){
            nextPost=postData.get(nextPostIndex);
            nextPostIndex++;
        }
        else{
            nextPostIndex=0;
            postData=null;
        }
        return nextPost;
    }
    public boolean isReadDataPost(){

        return this.postData==null;
    }
    public List<PostDTO>readDataPostFromApiRest(){
        ResponseEntity<PostDTO[]>response=restTemplate.getForEntity(API_URL,PostDTO[].class);
        PostDTO[] postData=response.getBody();
        return Arrays.asList(postData);
    }

}
