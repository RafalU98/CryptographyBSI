package Cryptography;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

/**
 * Cryptography.AES- algorithm encryption and decryption
 *
 *  The Advanced Encryption Standard (Cryptography.AES)  is a specification for the encryption of electronic data,is based on a
 *  family of ciphers with different key and block sizes. For Cryptography.AES is selected three members of the Rijndael family,
 *  each with a block size of 128 bits, but three different key lengths: 128, 192 and 256 bits.
 *  Cryptography.AES uses a key to customize the transformation and only if you know
 *  the secret key and what byte size are used, you will be able to decrypt the ciphertext properly.
 *
 * @Authors Jakub Wisniewski
 */
class AES {

    private static final String ALG = "Cryptography.AES";
    private static byte[] keyValue;

    /**
     * Creates a secret seed for key
     *
     * @param key a seed to randomize encrypt
     */
    public static byte[] AES(String key) {
        keyValue = (key).getBytes();
        return keyValue;
    }

    /**
     * Generates key which is then used to secure the code
     *
     * @param keyValue byted seed which is transformed to a key
     * @return returns the key to secure
     */
    private static Key generateKey(byte[] keyValue) {

        Key key = new SecretKeySpec(keyValue, ALG);
        return key;
    }


    /**
     * Encrypt function to encrypt a string
     *
     * @param Data data to encrypt
     * @return return encrypted data
     *
     */
    public static String encrypt(String Data,byte[] keyValue) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Key key = generateKey(keyValue);
        Cipher c = Cipher.getInstance(ALG);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        byte[] encodedBytes = Base64.getEncoder().encode(encVal);
        String encryptedValue = new String(encodedBytes);
        return encryptedValue;
    }

    /**
     * Decrypt function which decrypt an encrypted text
     *
     * @param encryptedData encrypted data to decrypt
     * @return return decrypted data
     *
     */
    public static String decrypt(String encryptedData,byte[] keyValue) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        Key key = generateKey(keyValue);
        Cipher c = Cipher.getInstance(ALG);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decorderValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decorderValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;

    }


    /**
     * Cryptography.Main method to initialize the text you either want to encrypt or decrypt
     *
     */
    public static void DecrypteEncrypte() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String text;

        System.out.println("Do you want to 1.Encrypt or 2.Decrypt?");
        choice = scanner.nextInt();

        byte[] aes = AES("lv39eptlvuhaggsr");

        if (choice == 1) {
            System.out.println("Enter the Text you want to Encrypt");
            text = scanner.next();
            System.out.println("Encrypted Text is: " + encrypt(text,aes));
        } else if (choice == 2) {
            System.out.println("Enter the Text you want to Decrypt");
            text = scanner.next();
            System.out.println("Decrypted Text is: " + decrypt(text,aes));
        } else
            System.out.println("You chose a Wrong Number ");

        System.out.println("Do you want to continue? Enter 1, if not enter any other number");
        if (scanner.nextInt() == 1 ) {
            DecrypteEncrypte();
        }
    }
}
