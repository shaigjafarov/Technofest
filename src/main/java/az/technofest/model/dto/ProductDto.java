package az.technofest.model.dto;

import lombok.Data;

@Data
public class ProductDto {


    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}