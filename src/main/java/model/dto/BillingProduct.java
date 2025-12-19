package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BillingProduct {
    private String barcode;
    private String name;
    private int buyQty;
    private int stockQty;
    private double unitPrice;
    private double totalPrice;
}
