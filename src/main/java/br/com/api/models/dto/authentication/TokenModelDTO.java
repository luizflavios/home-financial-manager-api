package br.com.api.models.dto.authentication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenModelDTO {
    private String accessToken;
    private Long expiresIn;
}
