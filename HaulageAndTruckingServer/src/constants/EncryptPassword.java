package src.constants;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class EncryptPassword {

    private final static Logger logger = LogManager.getLogger(EncryptPassword.class);
    private static final String ALGORITHM = "AES";
    private static final String KEY_ALGORITHM = "SHA-256";
    private static final String SECRET_KEY = "geovanni_is_cool"; // Replace with your own secret key

    public static String encrypt(String password) {
        byte[] encryptedBytes = null;

        SecretKeySpec secretKey;
        try {
            secretKey = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            
            
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error {} ", e.getMessage(), e);
          
        } catch (NoSuchPaddingException e) {
             logger.error("Error {} ", e.getMessage(), e);
        } catch (InvalidKeyException e) {
            logger.error("Error {} ", e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            logger.error("Error {} ", e.getMessage(), e);
        } catch (BadPaddingException e) {
            logger.error("Error {} ", e.getMessage(), e);
        }
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedPassword) throws Exception {
        SecretKeySpec secretKey = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKeySpec generateKey() throws NoSuchAlgorithmException {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance(KEY_ALGORITHM);
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16); // Use only the first 128 bits
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}