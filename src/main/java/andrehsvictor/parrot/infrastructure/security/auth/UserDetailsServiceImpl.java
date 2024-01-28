package andrehsvictor.parrot.infrastructure.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", username);
        return (UserDetails) query.getSingleResult();
    }

    public UserDetails loadUserById(Long id) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id").setParameter("id", id);
        return (UserDetails) query.getSingleResult();
    }

}
