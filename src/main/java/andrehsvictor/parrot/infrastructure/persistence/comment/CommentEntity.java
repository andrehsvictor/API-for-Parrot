package andrehsvictor.parrot.infrastructure.persistence.comment;

import java.time.LocalDateTime;

import andrehsvictor.parrot.infrastructure.persistence.post.PostEntity;
import andrehsvictor.parrot.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
    private LocalDateTime createdAt;
}
