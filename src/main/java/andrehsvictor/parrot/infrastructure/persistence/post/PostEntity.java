package andrehsvictor.parrot.infrastructure.persistence.post;

import java.time.LocalDateTime;
import java.util.Set;
import andrehsvictor.parrot.infrastructure.persistence.comment.CommentEntity;
import andrehsvictor.parrot.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name = "Post")
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "comments" })
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "post")
    private Set<CommentEntity> comments;
}
