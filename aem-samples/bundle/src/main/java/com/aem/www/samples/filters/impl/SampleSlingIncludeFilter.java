

package com.aem.www.samples.filters.impl;

import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.api.components.IncludeOptions;
import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@SlingFilter(
        label = "ACS AEM Samples - Sling INCLUDE Filter",
        description = "Sample implementation of a Sling Filter that remove decoration from cq:includes.",
        metatype = false,
        generateComponent = true, // True if you want to leverage activate/deactivate or manage its OSGi life-cycle
        generateService = true, // True; required for Sling Filters
        order = 0, // The smaller the number, the earlier in the Filter chain (can go negative);
                    // Defaults to Integer.MAX_VALUE which push it at the end of the chain
        scope = SlingFilterScope.INCLUDE) // REQUEST, INCLUDE, FORWARD, ERROR, COMPONENT (REQUEST, INCLUDE, COMPONENT)
public class SampleSlingIncludeFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SampleSlingIncludeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Usually, do nothing
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        final WCMMode mode = WCMMode.fromRequest(request);
        final IncludeOptions includeOptions = IncludeOptions.getOptions(request, true);

        // Only execute in Publish mode
        if (false && includeOptions != null &&  ((mode == null || WCMMode.DISABLED.equals(mode)))) {
            // Disable CQ Decoration on cq:includes or sling:includes, only in Publish mode
            includeOptions.setDecorationTagName("");
        }

        // Finally, proceed with the the Filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Usually, do Nothing
    }
}