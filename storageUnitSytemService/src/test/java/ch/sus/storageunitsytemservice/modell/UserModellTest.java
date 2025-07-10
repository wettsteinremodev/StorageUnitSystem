package ch.sus.storageunitsytemservice.modell;

import ch.sus.storageunitsytemservice.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
public class UserModellTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void whenEmailIsInvalid_thenValidationFails() {
        User user = new User();
        user.setEmail("invalid-email");
        user.setPassword("securePassword123"); // 👈 Add this

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        boolean emailViolationExists = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email"));
        assertEquals(true, emailViolationExists);


        assertEquals(true, emailViolationExists);
    }

}
