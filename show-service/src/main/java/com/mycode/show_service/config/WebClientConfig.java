package com.mycode.show_service.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {


/*
    WebClient webClient = WebClient.builder().baseUrl("http://movie-service").build();

    HttpServiceProxyFactory factory = HttpServiceProxyFactory
            .builder(WebClientAdapter.forClient(webClient))
            .build();

    MovieClient client = factory.createClient(MovieClient.class);*/

 /*   @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient movieWebClient() {
        return WebClient.builder()
                .baseUrl("http://movie-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public MovieClient movieClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(movieWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(MovieClient.class);
    }*/

/*
    @Bean
    public WebClient.Builder webClientBuilder(LoadBalancedExchangeFilterFunction filterFunction) {
        return WebClient.builder().filter(filterFunction);
    }

    private <T> T createClient(Class<T> clientType, String baseUrl, WebClient.Builder builder) {
        WebClient webClient = builder.baseUrl(baseUrl).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(clientType);
    }

    @Bean
    public MovieClient movieClient(WebClient.Builder builder) {
        return createClient(MovieClient.class, "http://movie-service", builder);
    }

    @Bean
    public TheatreClient theatreClient(WebClient.Builder builder) {
        return createClient(TheatreClient.class, "http://theatre-service", builder);
    }
*/

}
