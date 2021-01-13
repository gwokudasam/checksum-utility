package com.samsoftx.utilities.checksumutil;


import org.junit.Test;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

final class CheckSumUtilTest {

    public static final String CA_CERT_SUBJECT_DN_CN = "CN=ca.test.samsoftx.org";
    public static final String SERVER_CERT_SUBJECT_DN = "CN=Server Cert signed and with extended key usage server, C=DE, ST=Franconia, L=Pegnitz, OU=Test, O=samsoftx.org";

    public static final String CA_ALIAS = "cn=ca.test.samsoftx.org,c=de,st=bavaria,l=pegnitz,1.2.840.113549.1.9.1=#1612726f6c616e64406a6f6c6f6b69612e6f7267,ou=dev,o=samsoftx";
    public static final String SERVER_ALIAS = "cn=server cert signed and with extended key usage server,c=de,st=franconia,l=pegnitz,ou=test,o=samsoftx.org";


    private KeyStore createKeyStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(null);
        return keystore;
    }

    @Test
    void generateChecksumTest() {
    }

    @Test
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