package com.codecool.CodeCoolProjectGrande.user.auth.ReCaptchaV3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReCAPTCHAvs3KeyProvider {

    public static String recaptchaKey;

    @Autowired
    public ReCAPTCHAvs3KeyProvider(@Value("${reCaptchaSecretKey}") String recaptchaKey){
        ReCAPTCHAvs3KeyProvider.recaptchaKey = recaptchaKey;
    }

}