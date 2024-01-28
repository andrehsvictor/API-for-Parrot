package andrehsvictor.parrot.infrastructure.persistence.user;

import org.modelmapper.ModelMapper;

import andrehsvictor.parrot.domain.user.User;

public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserEntity toEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public static User toDomain(UserEntity userEntity) {
        return modelMapper.map(userEntity, User.class);
    }
}
