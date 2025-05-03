package homeworks.mapper;

import homeworks.dto.PersonResponseDto;
import homeworks.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonResponseMapper {
    Person toEntity(PersonResponseDto personResponseDto);

    PersonResponseDto toPersonResponseDto(Person person);
}