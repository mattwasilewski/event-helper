package com.codecool.CodeCoolProjectGrande.user.auth.ReCaptchaV3;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public final class ReCAPTCHAv3Utils {

    private static ObjectMapper mapper = new ObjectMapper();


    private ReCAPTCHAv3Utils() {
    }

    private static String readStream(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line);
            }
        }
        return builder.toString();
    }

    private static ReCAPTCHAv3Response readObject(InputStream stream) throws IOException {
        String response = readStream(stream);
        return mapper.readValue(response, ReCAPTCHAv3Response.class);
    }

    public static ReCAPTCHAv3Response request(String token, String ip) throws ReCAPTCHAv3Exception {
        int retries = 3;
        int retryInterval = 5000; // 5 seconds
        while (retries > 0) {
            try {
                URL url = new URL("https://www.google.com/recaptcha/api/siteverify?secret="
                        + ReCAPTCHAvs3KeyProvider.recaptchaKey + "&response=" + token + "&remoteip=" + ip);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                try {
                    ReCAPTCHAv3Response response = setConnection(connection);
                    if (response.isSuccess()) {
                        return response;
                    } else if (response.getAction().contains("timeout-or-duplicate")) {
                        retries--;
                        Thread.sleep(retryInterval);
                    } else {
                        throw new ReCAPTCHAv3Exception(response.getErrors());
                    }
                } finally {
                    connection.disconnect();
                }
            } catch (Exception e) {
                throw new ReCAPTCHAv3Exception("Verification failed", e);
            }
        }
        throw new ReCAPTCHAv3Exception("Verification failed after maximum number of retries");
    }

    private static ReCAPTCHAv3Response setConnection(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        ReCAPTCHAv3Response response = readObject(connection.getInputStream());
        return response;
    }
}