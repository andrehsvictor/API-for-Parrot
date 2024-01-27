package andrehsvictor.parrot.domain.user;

import java.util.Collection;

import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.domain.post.Post;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Collection<Post> posts;
    private Collection<Comment> comments;

    public User() {
    }

    public User(Long id, String name, String email, String password, Collection<Post> posts,
            Collection<Comment> comments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = posts;
        this.comments = comments;
    }

    public User(Long id, String name, String email, String password, Collection<Post> posts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    public User(String name, String email, String password, Collection<Post> posts, Collection<Comment> comments) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = posts;
        this.comments = comments;
    }

    public User(String name, String email, String password, Collection<Post> posts) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
    }

}
