package com.example.gatewayservice.LiloConfig;

import io.fria.lilo.GraphQLQuery;
import io.fria.lilo.LiloContext;
import io.fria.lilo.SchemaSource;
import io.fria.lilo.SyncQueryRetriever;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

class QueryRetrieverImpl implements SyncQueryRetriever {

    private final String schemaUrl;
    private final RestTemplate restTemplate;

    QueryRetrieverImpl(final @NonNull String schemaUrl) {
        this.schemaUrl = schemaUrl + "/graphql";
        this.restTemplate =
                new RestTemplateBuilder() //
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
                        .build(); //
    }

    @Override
    public @NonNull String get(
            final @NonNull LiloContext liloContext,
            final @NonNull SchemaSource schemaSource,
            final @NonNull GraphQLQuery graphQLQuery,
            final @Nullable Object localContext) {

        return Objects.requireNonNull(
                this.restTemplate.postForObject(this.schemaUrl, graphQLQuery.getQuery(), String.class));
    }
}
