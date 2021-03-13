package ru.padegfio.html.client.dto;

public enum DeclinationType {

    FIO("fio.txt"), DEPARTMENT("office.txt"), PROFESSION("prof.txt");

    private String fileName;

    DeclinationType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
