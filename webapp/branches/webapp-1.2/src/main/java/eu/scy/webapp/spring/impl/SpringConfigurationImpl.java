package eu.scy.webapp.spring.impl;

import net.sf.sail.webapp.spring.SpringConfiguration;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 04.sep.2009
 * Time: 05:52:51
 * To change this template use File | Settings | File Templates.
 */
public class SpringConfigurationImpl implements SpringConfiguration {

    private static final String[] ROOT_APPLICATION_CONTEXT_CONFIG_LOCATIONS;

    private static final String[] DISPATCHER_SERVLET_CONTEXT_CONFIG_LOCATIONS = new String[] {
            "classpath:configurations/dispatcherServlet/pas/config.xml",
            "classpath:configurations/dispatcherServlet/pas/controllers.xml",
            "classpath:configurations/dispatcherServlet/tels/controllers.xml",
            "classpath:configurations/dispatcherServlet/tels/extensions.xml",
            "classpath:configurations/dispatcherServlet/scy/overrides.xml" };

    static {
        final SpringConfiguration baseConfig = new net.sf.sail.webapp.spring.impl.SpringConfigurationImpl();
        final List<String> configLocationsList = Collections.list(Collections
                .enumeration(Arrays.asList(baseConfig
                        .getRootApplicationContextConfigLocations())));
        configLocationsList
                .add("classpath:configurations/applicationContexts/tels/extensions.xml");
        // Keep the overrides as the last item to be added to the list to ensure
        // that the overridden bean has indeed been defined.
        configLocationsList
                .add("classpath:configurations/applicationContexts/tels/overrides.xml");
        ROOT_APPLICATION_CONTEXT_CONFIG_LOCATIONS = configLocationsList
                .toArray(new String[0]);
    }

    /**
     * @see net.sf.sail.webapp.spring.SpringConfiguration#getDispatcherServletContextConfigLocations()
     */
    public String[] getDispatcherServletContextConfigLocations() {
        return DISPATCHER_SERVLET_CONTEXT_CONFIG_LOCATIONS;
    }

    /**
     * @see net.sf.sail.webapp.spring.SpringConfiguration#getRootApplicationContextConfigLocations()
     */
    public String[] getRootApplicationContextConfigLocations() {
        return ROOT_APPLICATION_CONTEXT_CONFIG_LOCATIONS;
    }
}