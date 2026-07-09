package br.com.davyson.userregistryapi.domain.exceptions.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldMessage {
    private String defaultMessage;
    private String field;

    public FieldMessage(String defaultMessage, String field) {
        this.defaultMessage = defaultMessage;
        this.field = field;
    }
    public FieldMessage(){}
}
