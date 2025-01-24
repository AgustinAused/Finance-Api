package com.finance.backend_api.DTOs;

import com.finance.backend_api.models.Company;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean active;
    private Company company;
    private String avatarUrl;
    private String phone;
}
