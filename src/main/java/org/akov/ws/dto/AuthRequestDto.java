package org.akov.ws.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    private String email;
    private String mdp;
}
