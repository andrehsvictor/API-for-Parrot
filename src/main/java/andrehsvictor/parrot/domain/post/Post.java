package andrehsvictor.parrot.domain.post;

import java.time.LocalDateTime;
import java.util.Collection;

import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.domain.user.User;

public class Post {
    private Long id;
    private String title;
    private String content;
    private User author;
    private LocalDateTime createdAt;
    private Collection<Comment> comments;

    public Post() {
    }

    public Post(Long id, String title, String content, User author, LocalDateTime createdAt,
            Collection<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    public Post(Long id, String title, String content, User author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Post(String title, String content, User author, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(String title) {
        this.title = title;
    }

    public Post(Long id, String title, String content, User author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public boolean titleIsValid() {
        if (this.title == null ||
                this.title.isEmpty() ||
                this.title.isBlank() ||
                this.title.length() > 100 ||
                this.title.length() < 5 ||
                this.title.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    public boolean contentIsValid() {
        if (this.content == null ||
                this.content.isEmpty() ||
                this.content.isBlank() ||
                this.content.length() > 500 ||
                this.content.length() < 5 ||
                this.content.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    public boolean isValid() {
        return this.titleIsValid() && this.contentIsValid();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
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
        Post other = (Post) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", createdAt="
                + createdAt + "]";
    }
}