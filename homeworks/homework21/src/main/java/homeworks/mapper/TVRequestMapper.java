package homeworks.mapper;

import homeworks.dto.TVRequest;
import homeworks.model.TV;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TVRequestMapper {
    TV toEntity(TVRequest TVRequest);

    TVRequest toTVRequest(TV TV);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    @Condition
    default boolean isNotEmpty(Integer value) {
        return value != null;
    }

    @Condition
    default boolean isNotEmpty(Boolean value) {
        return value != null;
    }
}