package li.mock;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import li.util.Log;
import li.util.ThreadUtil;

/**
 * MockRequestDispatcher
 * 
 * @author li (limingwei@mail.com)
 * @version 0.1.1 (2012-09-27)
 */
class MockRequestDispatcher implements RequestDispatcher {
    private static final Log log = Log.init();

    private String path;

    public MockRequestDispatcher(String path) {
        this.path = path;
    }

    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.debug("forward to : " + path + " calling by " + ThreadUtil.stackTrace());
    }

    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.debug("include : " + path + " calling by " + ThreadUtil.stackTrace());
    }
}
