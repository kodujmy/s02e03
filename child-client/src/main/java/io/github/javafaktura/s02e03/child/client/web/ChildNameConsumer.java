package io.github.javafaktura.s02e03.child.client.web;

import io.github.javafaktura.s02e03.child.client.model.ChildNameStats;
import io.github.javafaktura.s02e03.child.client.model.ParentChoice;
import io.github.javafaktura.s02e03.child.client.model.ParentPreferences;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildNameConsumer {

    @Value("${child.name.service.host}")
    private String childNameServiceApiHost;

    private final RestTemplate restTemplate;

    public ChildNameConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ChildNameStats getRandom(ParentPreferences parentPreferences) {
        return restTemplate.getForObject(
                childNameServiceApiHost + "/child-names/random", ChildNameStats.class);
    }

    public ChildNameStats lookFor(String name) {
        return restTemplate.getForObject(
                childNameServiceApiHost + String.format("/child-names/%s", name), ChildNameStats.class);
    }

    public List<ChildNameStats> getAll(ParentPreferences parentPreferences) {
        return Arrays.stream(restTemplate.exchange(childNameServiceApiHost + "/child-names?gender={gender}",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                ChildNameStats[].class,
                parentPreferences.getGender()
        ).getBody()).collect(Collectors.toList());
    }

    public void add(String name) {
        HttpEntity<ParentChoice> request =
                new HttpEntity<ParentChoice>(new ParentChoice(name), HttpHeaders.EMPTY);
        ChildNameStats childNameStats = restTemplate.postForObject(childNameServiceApiHost + "/child-names/choose", request, ChildNameStats.class);
    }
}
