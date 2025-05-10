package homeworks.mapper;

import homeworks.dto.UserResponse;
import homeworks.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserResponseMapper {
    User toEntity(UserResponse userResponse);

    UserResponse toUserResponse(User user);
}