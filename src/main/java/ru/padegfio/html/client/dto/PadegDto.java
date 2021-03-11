package ru.padegfio.html.client.dto;

public class PadegDto {
    private final String fio;

    public PadegDto(String fio) {
        this.fio = fio.trim();
    }

    public String getFio() {
        return fio;
    }
}
