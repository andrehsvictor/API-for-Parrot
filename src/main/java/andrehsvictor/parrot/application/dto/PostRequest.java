package andrehsvictor.parrot.application.dto;

public record PostRequest(String title, String content) {
    public PostRequest {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank");
        }
    }

    public static PostRequestBuilder builder() {
        return new PostRequestBuilder();
    }

    public static class PostRequestBuilder {
        private String title;
        private String content;

        PostRequestBuilder() {
        }

        public PostRequestBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostRequestBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostRequest build() {
            return new PostRequest(title, content);
        }

        public String toString() {
            return "PostRequest.PostRequestBuilder(title=" + this.title + ", content=" + this.content + ")";
        }
    }
}
