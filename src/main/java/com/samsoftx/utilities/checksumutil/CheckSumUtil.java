package com.samsoftx.utilities.checksumutil;


import com.samsoftx.utilities.checksumutil.exception.CheckSumGenerationException;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

/**
 * Checksum generator utility class
 * <p>
 * Used to compute an HMAC hash to verify that the message is cryptographically hashed
 * using a private key known only to you and Poscloud
 * <p>
 * HMAC guarantees that the message is from a trusted external source
 * and has not been altered on the way by a third party.
 *
 * @author Samuel Gwokuda <samuel@poscloud.co.zw>
 * @version v1.0.2
 * @since v1.0.0 codename Chamakuvangu
 */

public final class CheckSumUtil {

    private static final Logger LOG = Logger.getLogger(CheckSumUtil.class.getCanonicalName());

    /**
     * Private constructor to prevent instantiation
     */
    private CheckSumUtil() {
    }

    /**
     * Helper method to generate a checksum which will be populated in the checksum parameter
     * to verify the integrity of the data
     *
     * @param alias                         The alias.
     * @param keyStoreLocation              The location of the keystore.
     * @param keystorePassArr               The keystore password as a char array, char array is preferred over string
     * @see <a href="https://stackoverflow.com/questions/8881291/why-is-char-preferred-over-string-for-passwords"></a>
     * @param keyStoreType                  Keystore Type.
     * @param saltValue                     The salt value.
     * @throws IOException                  Input Output Exception when the file is not found.
     * @throws KeyStoreException            KeyStoreException.
     * @throws CertificateException         Indicates one of a variety of certificate problems.
     * @throws InvalidKeyException          For invalid Keys (invalid encoding, wrong length, uninitialized, etc).
     * @throws NoSuchAlgorithmException     Thrown when a particular cryptographic algorithm is requested.
     *                                      but is not available in the environment.
     * @throws SignatureException           Signature exception.
     * @throws UnrecoverableEntryException  This exception is thrown if an entry in the keystore cannot be recovered.
     * @return The checksum as a String
     */

    public static String generateChecksum(
            final String saltValue,
            final String keyStoreType,
            final String keyStoreLocation,
            final char[] keystorePassArr,
            final String alias) throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, KeyStoreException, UnrecoverableEntryException {

        try (final FileInputStream keystoreInputStream = new FileInputStream(keyStoreLocation)) {
            final KeyStore keystore;
            try {
                keystore = KeyStore.getInstance(keyStoreType);
            } catch (final KeyStoreException e) {
                throw new CheckSumGenerationException(e.getLocalizedMessage());
            }
            keystore.load(keystoreInputStream, keystorePassArr);
            final KeyStore.ProtectionParameter keyPass = new KeyStore.PasswordProtection(keystorePassArr);
            final KeyStore.PrivateKeyEntry keystoreEntry = (KeyStore.PrivateKeyEntry) keystore.getEntry(alias, keyPass);
            final PrivateKey privateKey = keystoreEntry.getPrivateKey();
            final X509Certificate cert = (X509Certificate) keystore.getCertificate(alias);
            final Signature signature = Signature.getInstance(cert.getSigAlgName());
            signature.initSign(privateKey);
            signature.update(saltValue.getBytes());
            final byte[] signedInfo = signature.sign();
            return Base64.encodeBase64String(signedInfo);
        }
    }
}
