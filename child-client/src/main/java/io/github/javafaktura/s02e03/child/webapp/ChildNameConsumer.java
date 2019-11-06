package io.github.javafaktura.s02e03.child.webapp;

import io.github.javafaktura.s02e03.child.client.model.ChildNameStats;
import io.github.javafaktura.s02e03.child.client.model.ParentChoice;
import io.github.javafaktura.s02e03.child.client.model.ParentPreferences;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
                buildUriWithParams(childNameServiceApiHost + "/child-names/random", parentPreferences),
                ChildNameStats.class
        );
    }

    public ChildNameStats lookFor(String name) {
        return restTemplate.getForObject(
                childNameServiceApiHost + String.format("/child-names/%s", name), ChildNameStats.class);
    }

    public List<ChildNameStats> getAll(ParentPreferences parentPreferences) {
        return Arrays.stream(restTemplate.exchange(
                buildUriWithParams(childNameServiceApiHost + "/child-names", parentPreferences),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                ChildNameStats[].class,
                parentPreferences.getGender()
        ).getBody()).collect(Collectors.toList());
    }

    public void add(String name) {
        HttpEntity<ParentChoice> request =
                new HttpEntity<ParentChoice>(new ParentChoice(name), HttpHeaders.EMPTY);
        restTemplate.postForObject(childNameServiceApiHost + "/child-names", request, ChildNameStats.class);
    }

    private String buildUriWithParams(String uri, ParentPreferences parentPreferences) {
        if(parentPreferences == null) {
            return uri;
        }

        UriComponentsBuilder childNameUriBuilder = UriComponentsBuilder.fromUriString(uri);

        if(parentPreferences.getGender() != null) {
            childNameUriBuilder.queryParam("gender", parentPreferences.getGender());
        }

        if(parentPreferences.getPopularity() != null) {
            childNameUriBuilder.queryParam("popularity", parentPreferences.getPopularity());
        }
        return childNameUriBuilder.toUriString();
    }
}
