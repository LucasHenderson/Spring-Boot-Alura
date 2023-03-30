package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.usuario.Usuario;

@Service
public class TokenService {

    // esta pegando esse valor dentro do application.properties
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API Voll.med")
                .withSubject(usuario.getLogin())
                //.withClaim("id", usuario.getId()) caso queira passar mais alguma coisa no token e so fazer dessa maneira
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        } 
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                            .withIssuer("API Voll.med")
                            .build()
                            .verify(tokenJWT)
                            .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    } 

    private Instant dataExpiracao() {
        // pega a hora atual com o localdatetime, soma duas horas (pode ser plusDays, plusMinutes, plusMonths e etc), 
        //converte para to instant falando qual e o fuso horario com zoneoffset
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
