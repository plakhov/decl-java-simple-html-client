package ru.padegfio.html.client.dto;

public enum DeclinationType {

    FIO("fio.txt", "fio"),
    DEPARTMENT("office.txt", "office"),
    PROFESSION("prof.txt", "prof");

    private final String fileName;
    private final String templateName;

    DeclinationType(String fileName, String templateName) {
        this.fileName = fileName;
        this.templateName = templateName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTemplateName() {
        return templateName;
    }
}
