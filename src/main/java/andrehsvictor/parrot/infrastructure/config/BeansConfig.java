package andrehsvictor.parrot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.application.usecase.PostService;
import andrehsvictor.parrot.infrastructure.persistence.post.PostGatewayImpl;
import andrehsvictor.parrot.infrastructure.util.AuthenticatedUserProviderImpl;

@Configuration
public class BeansConfig {

    @Bean
    PostService postService() {
        return new PostService(postGateway(), authenticatedUserProvider());
    }

    @Bean
    PostGateway postGateway() {
        return new PostGatewayImpl();
    }

    @Bean
    AuthenticatedUserProvider authenticatedUserProvider() {
        return new AuthenticatedUserProviderImpl();
    }
    
}
