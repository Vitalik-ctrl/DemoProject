package com.example.demowithtests.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NameIsEmptyException extends RuntimeException {
}
