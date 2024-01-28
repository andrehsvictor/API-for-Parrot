package andrehsvictor.parrot.application.dto;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, LocalDateTime createdAt, AuthorDetails author, CommentPostDetails post) {

    public static CommentResponseBuilder builder() {
        return new CommentResponseBuilder();
    }

    public static class CommentResponseBuilder {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
        private AuthorDetails author;
        private CommentPostDetails post;

        CommentResponseBuilder() {
        }

        public CommentResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CommentResponseBuilder content(String content) {
            this.content = content;
            return this;
        }

        public CommentResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CommentResponseBuilder author(AuthorDetails author) {
            this.author = author;
            return this;
        }

        public CommentResponseBuilder post(CommentPostDetails post) {
            this.post = post;
            return this;
        }

        public CommentResponse build() {
            return new CommentResponse(id, content, createdAt, author, post);
        }

        public String toString() {
            return "CommentResponse.CommentResponseBuilder(id=" + this.id + ", content=" + this.content + ", createdAt=" + this.createdAt + ", author=" + this.author + ", post=" + this.post + ")";
        }
    }
}