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

package org.acegisecurity.captcha;

import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;

import org.acegisecurity.context.SecurityContextHolder;

import org.acegisecurity.intercept.web.FilterInvocation;

import org.acegisecurity.securechannel.ChannelEntryPoint;
import org.acegisecurity.securechannel.ChannelProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.util.Assert;

import java.io.IOException;

import java.util.Iterator;

import javax.servlet.ServletException;


/**
 * <p>CaptchaChannel template : Ensures the user has enough human privileges by review of the {@link
 * CaptchaSecurityContext} and using an abstract routine {@link
 * #isContextValidConcerningHumanity(CaptchaSecurityContext)} (implemented by sub classes)</p>
 *  <P>The component uses 2 main parameters for its configuration :
 *  <ul>
 *      <li>a keyword to be mapped to urls in the {@link
 *      org.acegisecurity.securechannel.ChannelProcessingFilter} configuration<br>
 *      default value provided by sub classes.</li>
 *      <li>and a thresold : used by the routine {@link
 *      #isContextValidConcerningHumanity(CaptchaSecurityContext)} to evaluate whether the {@link
 *      CaptchaSecurityContext} is valid default value = 0</li>
 *  </ul>
 *  </p>
 *
 * @author marc antoine Garrigue
 * @version $Id: CaptchaChannelProcessorTemplate.java 1496 2006-05-23 13:38:33Z benalex $
 */
public abstract class CaptchaChannelProcessorTemplate implements ChannelProcessor, InitializingBean {
    //~ Instance fields ================================================================================================

    private ChannelEntryPoint entryPoint;
    protected Log logger = LogFactory.getLog(this.getClass());
    private String keyword = null;
    private int thresold = 0;

    //~ Methods ========================================================================================================

    /**
     * Verify if entryPoint and keyword are ok
     *
     * @throws Exception if not
     */
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(entryPoint, "entryPoint required");
        Assert.hasLength(keyword, "keyword required");
    }

    public void decide(FilterInvocation invocation, ConfigAttributeDefinition config)
        throws IOException, ServletException {
        if ((invocation == null) || (config == null)) {
            throw new IllegalArgumentException("Nulls cannot be provided");
        }

        CaptchaSecurityContext context = null;
        context = (CaptchaSecurityContext) SecurityContextHolder.getContext();

        Iterator iter = config.getConfigAttributes();

        while (iter.hasNext()) {
            ConfigAttribute attribute = (ConfigAttribute) iter.next();

            if (supports(attribute)) {
                logger.debug("supports this attribute : " + attribute);

                if (!isContextValidConcerningHumanity(context)) {
                    logger.debug("context is not allowed to access ressource, redirect to captcha entry point");
                    redirectToEntryPoint(invocation);
                } else {
                    logger.debug("has been successfully checked this keyword, increment request count");
                    context.incrementHumanRestrictedRessoucesRequestsCount();
                }
            } else {
                logger.debug("do not support this attribute");
            }
        }
    }

    public ChannelEntryPoint getEntryPoint() {
        return entryPoint;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getThresold() {
        return thresold;
    }

    abstract boolean isContextValidConcerningHumanity(CaptchaSecurityContext context);

    private void redirectToEntryPoint(FilterInvocation invocation)
        throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("context is not valid : redirecting to entry point");
        }

        entryPoint.commence(invocation.getRequest(), invocation.getResponse());
    }

    public void setEntryPoint(ChannelEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setThresold(int thresold) {
        this.thresold = thresold;
    }

    public boolean supports(ConfigAttribute attribute) {
        if ((attribute != null) && (keyword.equals(attribute.getAttribute()))) {
            return true;
        } else {
            return false;
        }
    }
}
