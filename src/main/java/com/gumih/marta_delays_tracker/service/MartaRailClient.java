package com.gumih.marta_delays_tracker.service;

import com.gumih.marta_delays_tracker.dto.MartaRailArrival;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Arrays;
import java.util.List;

@Service
public class MartaRailClient {

    private final RestClient restClient;
    private final String railUrl;
    private final String apiKey;

    public MartaRailClient(
            @Value("${marta.rail.url}") String railUrl,
            @Value("${marta.api.key}") String apiKey
    ) {
        this.restClient = RestClient.create();
        this.railUrl = railUrl;
        this.apiKey = apiKey;
    }

    public List<MartaRailArrival> fetchArrivals() {
        try {
            MartaRailArrival[] data = restClient.get()
                    .uri(railUrl + "?apiKey=" + apiKey)
                    .retrieve()
                    .body(MartaRailArrival[].class);

            return data == null ? List.of() : Arrays.asList(data);

        } catch (RestClientResponseException ex) {
            // MARTA sometimes returns 500. Don't crash the dashboard.
            System.out.println("MARTA API error: status=" + ex.getStatusCode()
                    + " body=" + ex.getResponseBodyAsString());
            return List.of();

        } catch (ResourceAccessException ex) {
            // timeout / connection issues
            System.out.println("MARTA API connection error: " + ex.getMessage());
            return List.of();
        }
    }
}