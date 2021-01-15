package com.samsoftx.utilities.checksumutil.exception;

/**
 * CheckSumGenerationException, the exception thrown when there
 * is a problem generating the message checksum
 *
 * @author Samuel Gwokuda <samuel@poscloud.co.zw>
 * @version v1.0.2
 * @since v1.0.0 codename Chamakuvangu
 */

public class CheckSumGenerationException extends RuntimeException {

    private static final long serialVersionUID = 8247610319171014183L;

    public CheckSumGenerationException(final String message) {
        super(message);
    }
}
