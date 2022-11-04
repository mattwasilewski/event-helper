package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import com.codecool.CodeCoolProjectGrande.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class PasswordService {


    public void addToken(ResetPasswordToken token){
        ResetTokenRepository.TOKENS_IN_MEMORY.add(token);
    }


    public Optional<ResetPasswordToken> getTokenByTokenId(String token) {
        return ResetTokenRepository.TOKENS_IN_MEMORY.stream().filter(element -> element.getTokenID().equals(token)).findFirst();

    }
}
