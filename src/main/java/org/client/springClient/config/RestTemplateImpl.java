package org.client.springClient.config;

import org.client.springClient.dto.JwtTokenDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Configuration
public class RestTemplateImpl {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    public RestTemplate restTemplate() {
        HttpClient client = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(provider())
                .build();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(client);
        return new RestTemplate(clientHttpRequestFactory);
    }

    private CredentialsProvider provider() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("111", "111"));
        return provider;
    }*/

    public static HttpHeaders getHttpHeaders(JwtTokenDto jwtTokenDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(jwtTokenDto.getTokenName(), "Bearer_" + jwtTokenDto.getTokenValue());
        return headers;
    }
}
