package andrehsvictor.parrot.application.use_case;

import andrehsvictor.parrot.application.dto.PostRequest;
import andrehsvictor.parrot.application.dto.PostResponse;
import andrehsvictor.parrot.application.dto.PostAuthorDetails;
import andrehsvictor.parrot.application.gateway.CurrentUserProvider;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.post.PostFactory;
import andrehsvictor.parrot.domain.user.User;

public class PostService {
    private final PostGateway postGateway;
    private final CurrentUserProvider currentUserProvider;

    public PostService(PostGateway postGateway, CurrentUserProvider currentUserProvider) {
        this.postGateway = postGateway;
        this.currentUserProvider = currentUserProvider;
    }

    public PostResponse createPost(PostRequest postRequest) {
        User currentUser = currentUserProvider.getCurrentUser();
        Post post = PostFactory.createPost(postRequest.title(), postRequest.content(), currentUser);

        PostAuthorDetails author = new PostAuthorDetails(currentUser.getId(),
                currentUser.getName());

        if (post.isValid()) {
            throw new RuntimeException("Post is not valid");
        }
        Post createdPost = postGateway.save(post);
        return PostResponse.builder()
                .id(createdPost.getId())
                .title(createdPost.getTitle())
                .content(createdPost.getContent())
                .author(author)
                .createdAt(createdPost.getCreatedAt())
                .build();
    }

    public PostResponse getPost(Long id) {
        Post post = postGateway.findById(id);
        PostAuthorDetails author = new PostAuthorDetails(post.getAuthor().getId(),
                post.getAuthor().getName());
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(author)
                .createdAt(post.getCreatedAt())
                .build();
    }

    public PostResponse updatePost(Long id, PostRequest postRequest) {
        Post post = postGateway.findById(id);
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
        User currentUser = currentUserProvider.getCurrentUser();
        if (!currentUser.equals(post.getAuthor())) {
            throw new RuntimeException("User is not the author of the post");
        }
        if (!post.isValid()) {
            throw new RuntimeException("Post is not valid");
        }
        Post updatedPost = postGateway.update(id, post);
        PostAuthorDetails author = new PostAuthorDetails(updatedPost.getAuthor().getId(),
                updatedPost.getAuthor().getName());
        return PostResponse.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .author(author)
                .createdAt(updatedPost.getCreatedAt())
                .build();
    }

    public void deletePost(Long id) {
        Post post = postGateway.findById(id);
        User currentUser = currentUserProvider.getCurrentUser();
        if (!currentUser.equals(post.getAuthor())) {
            throw new RuntimeException("User is not the author of the post");
        }
        postGateway.delete(id);
    }
}
