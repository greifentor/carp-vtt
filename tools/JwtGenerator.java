import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

public class JwtGenerator {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("Usage: java JwtGenerator.java <username> <offsetMinutes> <secretKey>");
            System.exit(1);
        }

        String username = args[0];
        long offsetMinutes = Long.parseLong(args[1]);
        String secretKey = args[2];

        // JWT Header (HS512)
        String headerJson = """
            {"alg":"HS512","typ":"JWT"}
            """;

        // JWT Payload
        Instant now = Instant.now();
        Instant expires = now.plusSeconds(offsetMinutes * 60);

        String payloadJson = String.format("""
            {"sub":"%s","iat":%d,"exp":%d}
            """, username, now.getEpochSecond(), expires.getEpochSecond());

        // Base64URL Encoding
        String header = base64UrlEncode(headerJson.getBytes(StandardCharsets.UTF_8));
        String payload = base64UrlEncode(payloadJson.getBytes(StandardCharsets.UTF_8));

        String unsignedToken = header + "." + payload;

        // Signatur erzeugen (HMAC-SHA512)
        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        mac.init(keySpec);

        byte[] signatureBytes = mac.doFinal(unsignedToken.getBytes(StandardCharsets.UTF_8));
        String signature = base64UrlEncode(signatureBytes);

        // Finales JWT
        String jwt = unsignedToken + "." + signature;

        System.out.println(jwt);
    }

    private static String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }
}
