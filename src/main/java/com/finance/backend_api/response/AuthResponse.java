package com.finance.backend_api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Data
@Builder
@AllArgsConstructor
public class AuthResponse {

    @JsonProperty("access_token")
    private String jwt;
}
