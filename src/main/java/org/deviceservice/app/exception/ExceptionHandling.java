package org.deviceservice.app.exception;

import org.deviceservice.app.utility.Codes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem;
import org.zalando.problem.spring.web.advice.validation.Violation;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

public class ExceptionHandling implements ProblemHandling, SecurityAdviceTrait {

    public static final String ERROR_EXCEPTION = ExceptionHandling.class.getCanonicalName();

    private static final String VALIDATION_TYPE = "https://zalando.github.io/problem/constraint-violation";
    private static final String CODE_KEY = "code";
    private static final String MESSAGE_KEY = "message";
    private static final String DATA_KEY = "data";

    @ExceptionHandler(value = SecurityException.class)
    public ResponseEntity<?> securityViolation(Throwable e, NativeWebRequest request) {
        Problem problem = createBaseProblemBuilder()
                .with(CODE_KEY, Codes.INCORRECT_TOKEN)
                .with(MESSAGE_KEY, e.getMessage())
                .with(DATA_KEY, null)
                .build();
        return create(e, problem, request);
    }

    @ExceptionHandler(value = InsufficientAuthenticationException.class)
    public ResponseEntity<?> insufficientAuthenticationException(NativeWebRequest request) {
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        Throwable filterException = (Throwable) httpServletRequest.getAttribute(ERROR_EXCEPTION);
        Throwable e = filterException != null ? filterException : new SecurityException("Insufficient authentication");
        return securityViolation(e, request);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(Throwable e, NativeWebRequest request) {
        Problem problem = createBaseProblemBuilder()
                .with(CODE_KEY, Codes.USER_NOT_FOUND)
                .with(MESSAGE_KEY, e.getMessage())
                .with(DATA_KEY, null)
                .build();
        return create(e, problem, request);
    }

    @Override
    public ResponseEntity<Problem> create(@NonNull final Throwable throwable, Problem problem,
                                          @NonNull final NativeWebRequest request, @NonNull final HttpHeaders headers) {
        if (problem.getType().equals(URI.create(VALIDATION_TYPE))) {
            problem = getValidationProblem(problem);
        }

        return ProblemHandling.super.create(throwable, problem, request, headers);
    }

    private ProblemBuilder createBaseProblemBuilder() {
        return Problem.builder();
    }

    private Problem getValidationProblem(Problem problem) {
        ConstraintViolationProblem constraintViolationProblem = (ConstraintViolationProblem) problem;
        List<Violation> violations = constraintViolationProblem.getViolations();
        return createBaseProblemBuilder()
                .with(CODE_KEY, Codes.VALIDATION_ERROR)
                .with(MESSAGE_KEY, "Data validation error")
                .with(DATA_KEY, violations)
                .build();
    }
}
