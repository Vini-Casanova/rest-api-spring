package br.com.casanova.apispring.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String exceptionMessage;
    private String exceptionDetails;

    public ExceptionResponse(Date timestamp, String exceptionMessage, String exceptionDetails) {
        this.timestamp = timestamp;
        this.exceptionMessage = exceptionMessage;
        this.exceptionDetails = exceptionDetails;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(String exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}
