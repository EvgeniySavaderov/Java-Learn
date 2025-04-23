package homeworks.dto;

/**
 * DTO for {@link homeworks.model.TV}
 */
public record TVRequest(Long id, String model, Integer sizeInches, Boolean isSmartTV, Boolean isEnabled, Integer currChannel,
                        Integer volume) {
}