package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import com.codecool.CodeCoolProjectGrande.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface ResetTokenRepository {
    List<ResetPasswordToken> TOKENS_IN_MEMORY = new ArrayList<>();



}
