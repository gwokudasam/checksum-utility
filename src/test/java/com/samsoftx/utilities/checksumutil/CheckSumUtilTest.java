package com.samsoftx.utilities.checksumutil;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Map;

import static com.samsoftx.utilities.checksumutil.CheckSumUtil.generateChecksum;
import static org.junit.jupiter.api.Assertions.assertThrows;


final class CheckSumUtilTest {

    public static final String CA_CERT_SUBJECT_DN_CN = "CN=ca.test.samsoftx.org";
    public static final String SERVER_CERT_SUBJECT_DN = "CN=Server Cert signed and with extended key usage server, C=ZW, ST=Zimbabwe, L=Harare, OU=Test, O=samsoftx.org";

    public static final String CA_ALIAS = "cn=ca.test.samsoftx.org,c=zw,st=zimbabwe,l=harare,1.2.840.113549.1.9.1=#1612726f6c616e64406a6f6c6f6b69612e6f7267,ou=dev,o=samsoftx";
    public static final String SERVER_ALIAS = "cn=server cert signed and with extended key usage server,c=zw,st=zimbabwe,l=harare,ou=test,o=samsoftx.org";

    private static final String SALT = "";
    private static final String KEY_STORE_TYPE = "";
    private static final String KEY_STORE_LOCATION = "";
    private static final char[] KEY_STORE_PASSWORD = "".toCharArray();
    private static final String ALIAS = "";

    private KeyStore createKeyStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(null);
        return keystore;
    }

    @Test
    void testShouldRaiseNullPointerFoundExceptionIfKeyStoreLocationIsNull() throws Exception {
        assertThrows(NullPointerException.class, () -> { generateChecksum(SALT, KEY_STORE_TYPE, null, KEY_STORE_PASSWORD, ALIAS);
        });
    }


    @Test
    void testShouldRaiseFileNotFoundException() throws Exception {
        assertThrows(FileNotFoundException.class, () -> { generateChecksum(SALT, KEY_STORE_TYPE, KEY_STORE_LOCATION, KEY_STORE_PASSWORD, ALIAS);
        });
    }

    @Test
    @DisplayName("Checksum test successful")
    void testGenerateChecksum() {
        try {
            final String checksum = generateChecksum(SALT, KEY_STORE_TYPE, KEY_STORE_LOCATION, KEY_STORE_PASSWORD, ALIAS);
            Assertions.assertNotNull(checksum);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final KeyStoreException e) {
            e.printStackTrace();
        } catch (final CertificateException e) {
            e.printStackTrace();
        } catch (final InvalidKeyException e) {
            e.printStackTrace();
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final SignatureException e) {
            e.printStackTrace();
        } catch (final UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Disabled("Not implemented yet")
    public void testTrustStoreWithMultipleEntries() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
       /* File caPem = getTempFile("ca/cert-multi.pem");
        KeyStore keystore = createKeyStore();

        KeyStoreUtil.updateWithCaPem(keystore, caPem);

        List<String> aliases = asList(keystore.aliases());
        assertEquals(aliases.size(), 2);
        final Map<String, String> expectedAliases = new HashMap<>();
        expectedAliases.put("ca.test.samsoftx.org",CA_CERT_SUBJECT_DN_CN);
        expectedAliases.put("another.test.samsoftx.org","CN=another.test.samsoftx.org");

        for (String alias : aliases) {

            final String key = findMatchingKeyAsSubstring(expectedAliases, alias);
            assertNotNull(key);
            String expectedSubjectDN = expectedAliases.remove(key);
            assertNotNull(expectedSubjectDN);

            X509Certificate cert = (X509Certificate) keystore.getCertificate(alias);
            assertTrue(cert.getSubjectDN().getName().contains(expectedSubjectDN));
            RSAPublicKey certPublicKey = (RSAPublicKey) cert.getPublicKey();
            assertEquals(certPublicKey.getAlgorithm(),"RSA");
        }
        assertEquals(expectedAliases.size(),0);*/
    }

    private String findMatchingKeyAsSubstring(final Map<String, String> map,
                                              final String fullValue) {
        for (String key : map.keySet()) {
            if (fullValue.contains(key)) {
                return key;
            }
        }
        return null;
    }

}