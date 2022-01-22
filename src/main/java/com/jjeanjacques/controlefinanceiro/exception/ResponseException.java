package com.jjeanjacques.controlefinanceiro.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;


@Getter
@Setter
@NoArgsConstructor
public class ResponseException {

    private String method;
    private String path;
    private String errorMessage;
    private Object errorDetail;

    public ResponseException(HttpServletRequest request, String errorMessage, Object errorDetail) {
        super();
        this.method = request.getMethod();
        this.path = request.getRequestURI();
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }
}
