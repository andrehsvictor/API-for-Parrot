package andrehsvictor.parrot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.application.usecase.comment.CreateCommentUseCase;
import andrehsvictor.parrot.application.usecase.comment.DeleteCommentUseCase;
import andrehsvictor.parrot.application.usecase.comment.FindCommentUseCase;
import andrehsvictor.parrot.infrastructure.persistence.comment.CommentGatewayImpl;

@Configuration
public class CommentConfig {
    @Bean
    CreateCommentUseCase createCommentUseCase(AuthenticatedUserProvider authenticatedUserProvider) {
        return new CreateCommentUseCase(commentGateway(), authenticatedUserProvider);
    }

    @Bean
    CommentGateway commentGateway() {
        return new CommentGatewayImpl();
    }

    @Bean
    FindCommentUseCase findCommentUseCase() {
        return new FindCommentUseCase(commentGateway());
    }

    @Bean
    DeleteCommentUseCase deleteCommentUseCase(AuthenticatedUserProvider authenticatedUserProvider) {
        return new DeleteCommentUseCase(commentGateway(), authenticatedUserProvider);
    }

    // @Bean
    // UpdateCommentUseCase updateCommentUseCase() {
    //     return new UpdateCommentUseCase(commentGateway(), authenticatedUserProvider());
    // }

}
