package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class PasswordServiceImpl {
    private final ResetPasswordRepository resetPasswordRepository;

    public PasswordServiceImpl(ResetPasswordRepository resetPasswordRepository) {
        this.resetPasswordRepository = resetPasswordRepository;
    }

    public void addToken(ResetPasswordToken token){
        resetPasswordRepository.save(token);
    }


    public Optional<ResetPasswordToken> getTokenByTokenId(String token) {
        return resetPasswordRepository.findResetPasswordTokenByTokenId(token);

    }
}
