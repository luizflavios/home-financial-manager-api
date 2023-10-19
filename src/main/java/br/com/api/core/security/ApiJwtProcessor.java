package br.com.api.core.security;

import br.com.api.core.configuration.ApiConfiguration;
import br.com.api.core.exception.TokenGenerationException;
import br.com.api.core.exception.ValidateTokenException;
import br.com.api.models.dto.authentication.AuthenticationModelResponseDTO;
import br.com.api.repository.AuthenticationModelRepository;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Date;
import java.util.UUID;

import static br.com.api.core.enums.AuthenticationModelStatus.INACTIVE;
import static com.nimbusds.jose.JWSAlgorithm.HS256;
import static java.lang.Boolean.FALSE;
import static java.lang.System.currentTimeMillis;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class ApiJwtProcessor {

    public static final String AUTHORIZATION = "Authorization";
    @Serial
    private static final long serialVersionUID = 2405172041950251807L;
    private final ApiConfiguration apiConfiguration;
    private final AuthenticationModelRepository authenticationModelRepository;

    public AuthenticationModelResponseDTO generateAuthentication(String userHash) {
        try {
            var signer = new MACSigner(apiConfiguration.getSecretKey());
            var expiresIn = generateExpiration();

            var claimsSet = new JWTClaimsSet.Builder()
                    .subject(userHash)
                    .expirationTime(expiresIn)
                    .build();

            var signedJWT = new SignedJWT(new JWSHeader(HS256), claimsSet);
            signedJWT.sign(signer);

            var token = signedJWT.serialize();

            return AuthenticationModelResponseDTO.builder()
                    .accessToken(token)
                    .expiresIn(expiresIn.getTime())
                    .build();

        } catch (Exception e) {
            throw new TokenGenerationException("error on token generation");
        }
    }

    public ApiAuthentication authenticate(HttpServletRequest request) {
        try {

            var token = request.getHeader(AUTHORIZATION);

            if (isBlank(token)) {
                throw new ValidateTokenException("token isn't present on current request");
            }

            var signedJWT = SignedJWT.parse(token);
            var verifier = new MACVerifier(apiConfiguration.getSecretKey());

            if (FALSE.equals(signedJWT.verify(verifier))
                    && FALSE.equals(signedJWT.getJWTClaimsSet().getExpirationTime().after(new Date()))) {
                throw new ValidateTokenException("invalid token");
            }

            var hash = signedJWT.getJWTClaimsSet().getSubject();
            var optionalAuthenticationModel = authenticationModelRepository.findByHash(UUID.fromString(hash));

            if (optionalAuthenticationModel.isEmpty()) {
                throw new ValidateTokenException("user not found on database");
            }

            var authenticationModel = optionalAuthenticationModel.get();

            if (INACTIVE.equals(authenticationModel.getStatus())) {
                throw new ValidateTokenException("user was not able to proceed with this request");
            }

            return new ApiAuthentication(authenticationModel.getRoles(), authenticationModel.getUser(), signedJWT.getJWTClaimsSet(), token);

        } catch (Exception e) {
            throw new ValidateTokenException(e.getMessage());
        }
    }

    private Date generateExpiration() {
        return new Date(currentTimeMillis() + apiConfiguration.getTokenLife());
    }
}
