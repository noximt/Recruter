package by.yauheni.entity.errors;

import by.yauheni.entity.question.Question;

public class QuestionDoesNotExitsError extends RuntimeException {
    public QuestionDoesNotExitsError() {
        super();
    }

    public QuestionDoesNotExitsError(String message) {
        super(message);
    }

    public QuestionDoesNotExitsError(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionDoesNotExitsError(Throwable cause) {
        super(cause);
    }

    protected QuestionDoesNotExitsError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
