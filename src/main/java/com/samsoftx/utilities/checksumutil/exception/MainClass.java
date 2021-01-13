package com.samsoftx.utilities.checksumutil.exception;

import com.samsoftx.utilities.checksumutil.CheckSumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import static com.samsoftx.utilities.checksumutil.CheckSumUtil.generateChecksum;


public class MainClass {

    private static final Logger LOG = LoggerFactory.getLogger(MainClass.class);

    private static final String SALT = "";
    private static final String KEY_STORE_TYPE = "";
    private static final String KEY_STORE_LOCATION = "";
    private static final char[] KEY_STORE_PASSWORD = "".toCharArray();
    private static final String ALIAS = "";


    public static void main(final String[] args) {
        try {
            final String checksum = generateChecksum(SALT, KEY_STORE_TYPE, KEY_STORE_LOCATION, KEY_STORE_PASSWORD, ALIAS);
            LOG.info("Generated Checksum is {}", checksum);
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
}
