package homeworks.dto;

import java.time.LocalDate;

/**
 * DTO for {@link homeworks.entity.User}
 */
public record UserResponse(Long id, String name, LocalDate birthDate) {
}