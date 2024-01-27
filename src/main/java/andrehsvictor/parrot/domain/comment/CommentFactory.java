package andrehsvictor.parrot.domain.comment;

import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.user.User;

public class CommentFactory {

    public static Comment createComment(String content, User author, Post post) {
        return new Comment(content, author, post);
    }

    public static Comment createComment(String content, User author) {
        return new Comment(content, author);
    }

    public static Comment createComment(String content) {
        return new Comment(content);
    }

    public static Comment createComment() {
        return new Comment();
    }
}
