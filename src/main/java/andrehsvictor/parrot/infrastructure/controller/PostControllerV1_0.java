package andrehsvictor.parrot.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.parrot.application.dto.PostRequest;
import andrehsvictor.parrot.application.dto.PostResponse;
import andrehsvictor.parrot.application.usecase.PostService;

@RestController
@RequestMapping("/api/v1.0/posts")
public class PostControllerV1_0 {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.createPost(postRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostResponse postResponse = postService.getPost(id);
        if (postResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok(postService.getAllPostsByCreatedAtDesc());
    }
}
