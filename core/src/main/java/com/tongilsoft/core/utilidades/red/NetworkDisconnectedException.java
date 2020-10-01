package com.tongilsoft.core.utilidades.red;

import java.io.IOException;

public class NetworkDisconnectedException extends IOException {
    public static final String DEFAULT_MESSAGE = "Su dispositivo esta desconectado de la red de la empresa.\n" +
            "Por favor verifique si esta conectado...";
    /**
     * Constructs an {@code NetworkDisconnectedException} with {@code null}
     * as its error detail message.
     */
    public NetworkDisconnectedException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs an {@code NetworkDisconnectedException} with the specified detail message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
    public NetworkDisconnectedException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code NetworkDisconnectedException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     *
     * @since 1.6
     */
    public NetworkDisconnectedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code NetworkDisconnectedException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for IO exceptions that are little more
     * than wrappers for other throwables.
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     *
     * @since 1.6
     */
    public NetworkDisconnectedException(Throwable cause) {
        super(cause);
    }
}
