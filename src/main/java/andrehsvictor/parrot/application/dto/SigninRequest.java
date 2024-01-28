package andrehsvictor.parrot.application.dto;

public record SigninRequest(String email, String password) {
    public SigninRequest {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password must not be null");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email must be valid");
        }
    }
}
