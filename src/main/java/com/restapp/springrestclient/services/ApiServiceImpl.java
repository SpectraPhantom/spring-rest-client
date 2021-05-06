package com.restapp.springrestclient.services;

import com.restapp.springrestclient.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;

    private final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate,@Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        log.debug(api_url);
        UriComponentsBuilder uriComponentsBuilder=UriComponentsBuilder.
                fromUriString(api_url).
                queryParam("_limit",limit);

        List<User> userData=restTemplate.getForObject(uriComponentsBuilder.toUriString(),List.class);
        log.debug(uriComponentsBuilder.toUriString());
        return userData;
    }
}
