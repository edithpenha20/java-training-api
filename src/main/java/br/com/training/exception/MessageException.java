package br.com.training.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MessageException{

    private HttpStatus status;
    private LocalDateTime dateHour;
    private String title;
    private List<String> typeError;

    public MessageException(HttpStatus status, LocalDateTime dateHour, String title, List<String> typeError) {
        this.status = status;
        this.dateHour = dateHour;
        this.title = title;
        this.typeError = typeError;
    }

    public MessageException(HttpStatus status, LocalDateTime dateHour, String title) {

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(LocalDateTime dateHour) {
        this.dateHour = dateHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTypeError() {
        return typeError;
    }

    public void setTypeError(List<String> typeError) {
        this.typeError = typeError;
    }
}
