package com.learncode_backend.dto;

public class PlanDTO {

    private String code;
    private String name;
    private String description;
    private Double price;
    private Integer durationDays;

    public PlanDTO() {}

    public PlanDTO(
        String code,
        String name,
        String description,
        Double price,
        Integer durationDays
    ) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationDays = durationDays;
    }

    public String getCode() { return code; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public Double getPrice() { return price; }

    public Integer getDurationDays() { return durationDays; }
}

