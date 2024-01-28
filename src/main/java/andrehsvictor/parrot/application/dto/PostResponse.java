package andrehsvictor.parrot.application.dto;

import java.time.LocalDateTime;
import java.util.Collection;

public record PostResponse(Long id, String title, String content, AuthorDetails author, Collection<PostCommentDetails> comments, String createdAt) {

    public static PostResponseBuilder builder() {
        return new PostResponseBuilder();
    }

    public static class PostResponseBuilder {
        private Long id;
        private String title;
        private String content;
        private AuthorDetails author;
        private Collection<PostCommentDetails> comments;
        private String createdAt;

        PostResponseBuilder() {
        }

        public PostResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PostResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostResponseBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostResponseBuilder author(AuthorDetails author) {
            this.author = author;
            return this;
        }

        public PostResponseBuilder comments(Collection<PostCommentDetails> comments) {
            this.comments = comments;
            return this;
        }

        public PostResponseBuilder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PostResponse build() {
            return new PostResponse(id, title, content, author, comments, createdAt);
        }

        public String toString() {
            return "PostResponse.PostResponseBuilder(id=" + this.id + ", title=" + this.title + ", content=" + this.content + ", author=" + this.author + ", comments=" + this.comments + ", createdAt=" + this.createdAt + ")";
        }
    }

}
