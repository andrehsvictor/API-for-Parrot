package andrehsvictor.parrot.infrastructure.persistence.user;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import andrehsvictor.parrot.infrastructure.persistence.comment.CommentEntity;
import andrehsvictor.parrot.infrastructure.persistence.post.PostEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "User")
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "posts", "comments", "password" })
public class UserEntity implements UserDetails {
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "author")
    private Set<PostEntity> posts;
    
    @OneToMany(mappedBy = "author")
    private Set<CommentEntity> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
