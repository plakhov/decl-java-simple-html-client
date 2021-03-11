package ru.padegfio.html.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PadegFioResponseDto {

    private final List<String> result;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PadegFioResponseDto(@JsonProperty("result") List<String> result) {
        this.result = new ArrayList<>(result);
    }

    public List<String> getResult() {
        return result;
    }
}
