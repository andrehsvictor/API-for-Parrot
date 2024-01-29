package andrehsvictor.parrot.infrastructure.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.parrot.application.dto.PostRequest;
import andrehsvictor.parrot.application.dto.PostResponse;
import andrehsvictor.parrot.application.usecase.post.CreatePostUseCase;
import andrehsvictor.parrot.application.usecase.post.DeletePostUseCase;
import andrehsvictor.parrot.application.usecase.post.FindAllPostsUseCase;
import andrehsvictor.parrot.application.usecase.post.FindPostUseCase;
import andrehsvictor.parrot.application.usecase.post.UpdatePostUseCase;

@RestController
@RequestMapping("/api/v1.0/posts")
public class PostControllerV1_0 {

    @Autowired
    private CreatePostUseCase createPostUseCase;

    @Autowired
    private FindAllPostsUseCase findAllPostsUseCase;

    @Autowired
    private DeletePostUseCase deletePostUseCase;

    @Autowired
    private FindPostUseCase findPostUseCase;

    @Autowired
    private UpdatePostUseCase updatePostUseCase;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse postResponse = createPostUseCase.createPost(postRequest);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping
    public ResponseEntity<Collection<PostResponse>> findAllPosts() {
        Collection<PostResponse> postResponses = findAllPostsUseCase.findAllOrderByCreatedAtDesc();
        return ResponseEntity.ok(postResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        deletePostUseCase.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findPost(@PathVariable Long id) {
        PostResponse postResponse = findPostUseCase.findPostById(id);
        if (postResponse == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        PostResponse postResponse = updatePostUseCase.updatePost(id, postRequest);
        return ResponseEntity.ok(postResponse);
    }
}
