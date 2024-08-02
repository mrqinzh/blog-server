package com.mrqinzh.common.exception;

public class FileServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileServiceException() {
        super();
    }

    public FileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileServiceException(String message) {
        super(message);
    }

    public FileServiceException(Throwable cause) {
        super(cause);
    }
}
