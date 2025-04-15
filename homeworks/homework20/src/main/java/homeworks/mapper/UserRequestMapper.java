package homeworks.mapper;

import homeworks.dto.UserRequest;
import homeworks.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRequestMapper {
    User toEntity(UserRequest userRequest);

    UserRequest toUserRequest(User user);
}