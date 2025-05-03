package homeworks.mapper;

import homeworks.dto.PersonRequestDto;
import homeworks.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonRequestMapper {
    Person toEntity(PersonRequestDto personRequestDto);

    PersonRequestDto toPersonRequestDto(Person person);

    Person updateWithNull(PersonRequestDto personRequestDto, @MappingTarget Person person);
}