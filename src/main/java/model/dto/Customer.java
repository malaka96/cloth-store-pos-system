package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Customer {
    private String name;
    private String phone;
    private String email;
    private String address;
    private int isActive; // 1 or 0 (1 = true, 0 = false)
}
