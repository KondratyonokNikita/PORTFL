package com.portfl.repository;

import com.portfl.configuration.security.CrmUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ikatlinsky
 * @since 3/30/17
 */
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {
        CrmUserDetails crmUserDetails = (CrmUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return crmUserDetails.getId();
    }
}
