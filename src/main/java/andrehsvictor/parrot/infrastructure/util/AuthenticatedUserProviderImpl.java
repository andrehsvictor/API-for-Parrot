package andrehsvictor.parrot.infrastructure.util;

import org.springframework.security.core.context.SecurityContextHolder;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.domain.user.User;
import andrehsvictor.parrot.infrastructure.persistence.user.UserEntity;
import andrehsvictor.parrot.infrastructure.persistence.user.UserMapper;

public class AuthenticatedUserProviderImpl implements AuthenticatedUserProvider {

    @Override
    public User getUser() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserMapper.toDomain(user);
    }

}
