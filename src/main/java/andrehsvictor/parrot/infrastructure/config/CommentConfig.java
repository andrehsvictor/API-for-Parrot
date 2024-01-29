package andrehsvictor.parrot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.application.usecase.comment.CreateCommentUseCase;
import andrehsvictor.parrot.application.usecase.comment.DeleteCommentUseCase;
import andrehsvictor.parrot.application.usecase.comment.FindCommentUseCase;
import andrehsvictor.parrot.infrastructure.persistence.comment.CommentGatewayImpl;
import andrehsvictor.parrot.infrastructure.util.AuthenticatedUserProviderImpl;

@Configuration
public class CommentConfig {
    @Bean
    CreateCommentUseCase createCommentUseCase() {
        return new CreateCommentUseCase(commentGateway(), authenticatedUserProvider());
    }

    @Bean
    CommentGateway commentGateway() {
        return new CommentGatewayImpl();
    }

    @Bean
    AuthenticatedUserProvider authenticatedUserProvider() {
        return new AuthenticatedUserProviderImpl();
    }

    @Bean
    FindCommentUseCase findCommentUseCase() {
        return new FindCommentUseCase(commentGateway());
    }

    @Bean
    DeleteCommentUseCase deleteCommentUseCase() {
        return new DeleteCommentUseCase(commentGateway(), authenticatedUserProvider());
    }

    // @Bean
    // UpdateCommentUseCase updateCommentUseCase() {
    //     return new UpdateCommentUseCase(commentGateway(), authenticatedUserProvider());
    // }

}
