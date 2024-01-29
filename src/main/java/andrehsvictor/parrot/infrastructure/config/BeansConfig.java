package andrehsvictor.parrot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.infrastructure.util.AuthenticatedUserProviderImpl;

@Configuration
public class BeansConfig {
    @Bean
    AuthenticatedUserProvider authenticatedUserProvider() {
        return new AuthenticatedUserProviderImpl();
    }
}
