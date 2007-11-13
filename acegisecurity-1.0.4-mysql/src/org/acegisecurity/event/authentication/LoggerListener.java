/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acegisecurity.event.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.ClassUtils;


/**
 * Outputs authentication-related application events to Commons Logging.<P>All authentication events are logged at
 * the warning level.</p>
 *
 * @author Ben Alex
 * @version $Id: LoggerListener.java 1845 2007-05-23 06:31:32Z benalex $
 */
public class LoggerListener implements ApplicationListener {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(LoggerListener.class);
    
    /** If set to true, {@link InteractiveAuthenticationSuccessEvent} will be logged (defaults to true) */
    private boolean logInteractiveAuthenticationSuccessEvents = true;
    
    //~ Methods ========================================================================================================

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof AbstractAuthenticationEvent) {
            AbstractAuthenticationEvent authEvent = (AbstractAuthenticationEvent) event;
            
            if (!logInteractiveAuthenticationSuccessEvents && authEvent instanceof InteractiveAuthenticationSuccessEvent) {
            	return;
            }

            if (logger.isWarnEnabled()) {
                String message = "Authentication event " + ClassUtils.getShortName(authEvent.getClass()) + ": "
                    + authEvent.getAuthentication().getName() + "; details: "
                    + authEvent.getAuthentication().getDetails();

                if (event instanceof AbstractAuthenticationFailureEvent) {
                    message = message + "; exception: "
                        + ((AbstractAuthenticationFailureEvent) event).getException().getMessage();
                }

                logger.warn(message);
            }
        }
    }

	public boolean isLogInteractiveAuthenticationSuccessEvents() {
		return logInteractiveAuthenticationSuccessEvents;
	}

	public void setLogInteractiveAuthenticationSuccessEvents(
			boolean logInteractiveAuthenticationSuccessEvents) {
		this.logInteractiveAuthenticationSuccessEvents = logInteractiveAuthenticationSuccessEvents;
	}
}
