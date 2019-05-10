package com.ro8.varastosofta.application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;



/**
 * Salasanan salaus uuden käyttäjän luomista, sekä kirjautumista varten.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class PasswordEncryptionService {

	
	/**
	 * Autentikointi
	 * @param attemptedPassword Kokeiltava salasana
	 * @param encryptedPassword Tietokannassa oleva salattu salasana
	 * @param salt suola
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Salataan syötetty salasana samalla suolalla, jota käytettiin salaamaan kannassa oleva salasana
		byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);

		// Jos salattu syötetty salasana on sama kuin kannassa oleva, niin palautetaan true
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	
	/**
	 * Salaa salasana.
	 * @param password Salattava salasana
	 * @param salt suola
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] getEncryptedPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String algorithm = "PBKDF2WithHmacSHA1";

		int derivedKeyLength = 160;

		int iterations = 20000;

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		return f.generateSecret(spec).getEncoded();
	}

	
	/**
	 * Generoi suola
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

		byte[] salt = new byte[8];
		random.nextBytes(salt);

		return salt;
	}
}
