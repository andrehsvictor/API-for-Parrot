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

@Configuration
public class PostConfig {

    @Bean
    CreatePostUseCase createPostUseCase(AuthenticatedUserProvider authenticatedUserProvider) {
        return new CreatePostUseCase(postGateway(), authenticatedUserProvider);
    }

    @Bean
    DeletePostUseCase deletePostUseCase(AuthenticatedUserProvider authenticatedUserProvider) {
        return new DeletePostUseCase(postGateway(), authenticatedUserProvider);
    }

    @Bean
    FindPostUseCase findPostUseCase() {
        return new FindPostUseCase(postGateway());
    }

    @Bean
    UpdatePostUseCase updatePostUseCase(AuthenticatedUserProvider authenticatedUserProvider) {
        return new UpdatePostUseCase(postGateway(), authenticatedUserProvider);
    }

    @Bean
    FindAllPostsUseCase findAllPostsUseCase() {
        return new FindAllPostsUseCase(postGateway());
    }

    @Bean
    PostGateway postGateway() {
        return new PostGatewayImpl();
    }
}
