package andrehsvictor.parrot.application.gateway;

import andrehsvictor.parrot.domain.user.User;

public interface AuthenticatedUserProvider {
    User getUser();
}