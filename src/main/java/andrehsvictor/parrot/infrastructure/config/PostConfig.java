package andrehsvictor.parrot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.application.usecase.post.CreatePostUseCase;
import andrehsvictor.parrot.application.usecase.post.DeletePostUseCase;
import andrehsvictor.parrot.application.usecase.post.FindAllPostsUseCase;
import andrehsvictor.parrot.application.usecase.post.FindPostUseCase;
import andrehsvictor.parrot.application.usecase.post.UpdatePostUseCase;
import andrehsvictor.parrot.infrastructure.persistence.post.PostGatewayImpl;
import andrehsvictor.parrot.infrastructure.util.AuthenticatedUserProviderImpl;

@Configuration
public class PostConfig {

    @Bean
    CreatePostUseCase createPostUseCase() {
        return new CreatePostUseCase(postGateway(), authenticatedUserProvider());
    }

    @Bean
    FindAllPostsUseCase findAllPostsUseCase() {
        return new FindAllPostsUseCase(postGateway());
    }

    @Bean
    DeletePostUseCase deletePostUseCase() {
        return new DeletePostUseCase(postGateway(), authenticatedUserProvider());
    }

    @Bean
    FindPostUseCase findPostUseCase() {
        return new FindPostUseCase(postGateway());
    }

    @Bean
    UpdatePostUseCase updatePostUseCase() {
        return new UpdatePostUseCase(postGateway(), authenticatedUserProvider());
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
