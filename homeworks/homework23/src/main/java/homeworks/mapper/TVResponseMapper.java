package homeworks.mapper;

import homeworks.dto.TVResponse;
import homeworks.model.TV;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TVResponseMapper {
    TV toEntity(TVResponse TVResponse);

    TVResponse toTVResponse(TV TV);
}