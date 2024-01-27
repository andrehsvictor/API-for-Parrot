package andrehsvictor.parrot.domain.post;

import andrehsvictor.parrot.domain.user.User;

public class PostFactory {

    public static Post createPost(String title, String content, User author) {
        return new Post(title, content, author);
    }

    public static Post createPost(String title, String content) {
        return new Post(title, content);
    }

    public static Post createPost(String title) {
        return new Post(title);
    }

    public static Post createPost() {
        return new Post();
    }
}
