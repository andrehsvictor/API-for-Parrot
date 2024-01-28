package andrehsvictor.parrot.application.dto;

public record SignupRequest(
        String name,
        String email,
        String password) {
    public SignupRequest {
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Name, email and password must not be null");
        }

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Name, email and password must not be blank");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email must be valid");
        }

        if (!name.matches("^[a-z\\s]*$")) {
            throw new IllegalArgumentException("Name must be valid");
        }
    }

}
