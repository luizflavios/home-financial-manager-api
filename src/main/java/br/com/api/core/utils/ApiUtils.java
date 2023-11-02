package br.com.api.core.utils;

import br.com.api.models.entities.AuthenticationModel;
import br.com.api.models.entities.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class ApiUtils {

    public static User getLoggedUser() {
        return ((AuthenticationModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

}
