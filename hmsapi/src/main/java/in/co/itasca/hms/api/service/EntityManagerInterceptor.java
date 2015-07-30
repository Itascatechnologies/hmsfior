
package in.co.itasca.hms.api.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import in.co.itasca.hms.persistence.manager.PersistenceManager;;

public class EntityManagerInterceptor implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            PersistenceManager.getInstance().initEntityManagerProvider();
            chain.doFilter(request, response);
        } finally {
            PersistenceManager.getInstance().closeEntityManager();
        }
    }

    public void init(FilterConfig config) {
    }

    public void destroy() {
        PersistenceManager.getInstance().closeAll();
    }
}
