package andrehsvictor.parrot.infrastructure.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtils {
    public static void setAuthentication(Authentication authentication) {
        if (authentication != null)
            SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
