package cybersoft.javabackend.java18.obajuecommerce.security.authorization;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.PermissionException;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AuthorizationAspect {
    private final OperationRepository operationRepository;

    public AuthorizationAspect(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Before("@annotation(securityOperation)")
    public void authorizeOperation(SecurityOperation securityOperation) {
        log.info("Pointcut has been activated operation = " + securityOperation.name() + ", fetch = " + securityOperation.type());
        String username = getCurrentUser();
        if (!isPermitted(username, securityOperation.name()))
            throw new PermissionException("User is not permitted to use this operation. Please contact for Administrator for permission");
    }

    private boolean isPermitted(String username, String operationName) {
        return operationRepository.isPermittedOperation(operationName, username);
    }

    private String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null)
            return null;
        if(auth.getPrincipal() instanceof String principal) {
            return principal;
        }
        UserDetails currentUser = (UserDetails) auth.getPrincipal();
        return currentUser.getUsername();
    }
}
