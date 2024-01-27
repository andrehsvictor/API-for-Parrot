package andrehsvictor.parrot.domain.comment;

import java.time.LocalDateTime;

import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.user.User;

public class Comment {
    private Long id;
    private String content;
    private User author;
    private Post post;
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Long id, String content, User author, Post post, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.post = post;
        this.createdAt = createdAt;
    }

    public Comment(String content, User author, Post post, LocalDateTime createdAt) {
        this.content = content;
        this.author = author;
        this.post = post;
        this.createdAt = createdAt;
    }

    public Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
    }

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
    }

    public Comment(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", content=" + content + ", author=" + author + ", post=" + post + ", createdAt="
                + createdAt + "]";
    }
}
