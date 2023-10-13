package com.example.news_system.api;

import com.example.news_system.dto.response.sport.SportsNewsResDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@NoArgsConstructor
@Slf4j
public class GetSportsResultFromApi {
    private String key="57fd1020aae74164958a06476da1f9c2";
    private Object response;

    private SportsNewsResDTO resultCast(){
        if(this.response == null)
            return null;

        return
                new ObjectMapper().convertValue(
                        this.response,
                        new TypeReference<SportsNewsResDTO>() {});
    }

    @SneakyThrows
    public String getResultAsString(){
        if(this.response == null)
            return null;
        return new ObjectMapper().writeValueAsString(this.response);

    }

    public SportsNewsResDTO getResult(){
        getSportResultFromApi();
        return resultCast();
    }

    private void getSportResultFromApi(){

        RestTemplate restTemplate = new RestTemplate();
        UriComponents builder = UriComponentsBuilder.fromHttpUrl("https://newsapi.org/v2/everything")
                .queryParam("q", "Sport")
                .queryParam("apiKey", this.key)
                .build();

        try {
            this.response = restTemplate.getForObject(builder.toUriString(), Object.class);
        } catch (Exception e) {
            log.debug("Cannot get response from ");
        }
    }
}
