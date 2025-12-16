package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class Product {
    private String name;
    private String category;
    private Double price;
    private int stockQty;
    private int isActive;
}
