

package com.aem.www.samples.servlets.impl;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.OptingServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SlingServlet(
        label = "Samples - Sling Safe Methods Servlet",
        description = "Sample implementation of a Sling All Methods Servlet.",
        paths = { "/services/safe-sample" },
        methods = { "GET" }, // Ignored if paths is set - Defaults to GET if not specified
        resourceTypes = { }, // Ignored if paths is set
        selectors = { "print.a4", "print" }, // Ignored if paths is set
        extensions = { "html" } // Ignored if paths is set
)
public class SampleSafeMethodsServlet extends SlingSafeMethodsServlet implements OptingServlet {
    private static final Logger log = LoggerFactory.getLogger(SampleSafeMethodsServlet.class);
    private static final int FOUR_KB = 4096;

    /**
     * Add overrides for other SlingSafeMethodsServlet here (doGeneric, doHead, doOptions, doTrace) *
     */

    @Override
    protected final void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws
            ServletException, IOException {

        // Implement custom handling of GET requests
        boolean responseIsText = true;

        if (responseIsText) {

            // Write a standard text/html response
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("<html><body>Text to write to response</body></html>");

        } else {
            // Write some binary data to the response; Such as sending back an Image or PDF
            InputStream input = new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            };

            response.setContentType(getServletContext().getMimeType(request.getPathInfo()));
            OutputStream output = response.getOutputStream();

            byte[] buffer = new byte[FOUR_KB];

            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
                output.flush();
            }
        }
    }

    /** OptingServlet Acceptance Method **/

    @Override
    public final boolean accepts(SlingHttpServletRequest request) {
        /*
         * Add logic which inspects the request which determines if this servlet
         * should handle the request. This will only be executed if the
         * Service Configuration's paths/resourcesTypes/selectors accept the request.
         */
        return true;
    }
}