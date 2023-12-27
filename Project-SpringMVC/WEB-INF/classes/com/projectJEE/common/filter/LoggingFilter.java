// Import the necessary SLF4J logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter implements Filter {
    // Declare the SLF4J logger
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic (if needed)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            logger.info("----------- Logging Filter -----------");

            // Your existing code...

            // Retrieve the User object from the session
            User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");

            if (user != null) {
                logger.info("User: {}", user);
            } else {
                // Redirect to the root ("/") URL if the user is not present
                request.getRequestDispatcher("/").forward(request, response);
                return;
            }

            // More of your existing code...

        } catch (Exception ex) {
            // Log an error if an exception occurs
            logger.error("Error in LoggingFilter: {}", ex.getMessage(), ex);

            // Forward to the root ("/") URL
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup logic (if needed)
    }
}