/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.service.curnit.CurnitService;
import net.sf.sail.webapp.service.jnlp.JnlpService;
import net.sf.sail.webapp.service.offering.OfferingService;
import net.sf.sail.webapp.spring.SpringConfiguration;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A disposable class that is used to create default curnits, jnlp(s), and
 * offerings in the data store.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class CreateDefaultOfferings {

    private CurnitService curnitService;

    private JnlpService jnlpService;

    private OfferingService offeringService;

    private static final Map<String, String> CURNITS;

    private static final Map<String, String> JNLPS;

    static {
        Map<String, String> hashmap = new HashMap<String, String>();
        hashmap
                .put(
                        "Two Kinds of Processes",
                        "http://www.encorewiki.org/download/attachments/2113/converted-wise-dev.berkeley.edu-16704.jar");
        hashmap
                .put(
                        "Direct and Emergent Processes for Engineering Science",
                        "http://www.encorewiki.org/download/attachments/2113/converted-wise-dev.berkeley.edu-24500.jar");
        CURNITS = Collections.unmodifiableMap(hashmap);

        hashmap = new HashMap<String, String>();
        hashmap
                .put(
                        "PLR Everything JDIC snapshot 20070125-0811",
                        "http://www.encorewiki.org/download/attachments/2114/plr-everything-jdic-snapshot-20070125-0811.jnlp");
        JNLPS = Collections.unmodifiableMap(hashmap);
    }

    /**
     * Stand alone application that initializes the database as well as the
     * external SDS with predefined curnits, jnlp(s), and offerings that are
     * known to work.
     * 
     * @param args
     *            args[0] - spring-configuration-classname
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out
                    .println("Usage: CreateDefaultOfferings <spring-configuration-classname>");
            System.exit(1);
        }
        ConfigurableApplicationContext applicationContext = null;
        try {
            SpringConfiguration springConfig = (SpringConfiguration) BeanUtils
                    .instantiateClass(Class.forName(args[0]));
            applicationContext = new ClassPathXmlApplicationContext(
                    springConfig.getRootApplicationContextConfigLocations());

            CreateDefaultOfferings createDefaultOfferings = new CreateDefaultOfferings(
                    applicationContext);
            Curnit[] curnits = createDefaultOfferings
                    .createDefaultCurnits(applicationContext);
            Jnlp[] jnlps = createDefaultOfferings
                    .createDefaultJnlps(applicationContext);
            createDefaultOfferings.createDefaultOfferings(applicationContext,
                    curnits, jnlps);
        } catch (Exception all) {
            System.err.println(all.getLocalizedMessage());
            all.printStackTrace(System.out);
            System.exit(2);
        } finally {
            applicationContext.close();
        }
    }

    public Offering[] createDefaultOfferings(
            ApplicationContext applicationContext, Curnit[] curnits,
            Jnlp[] jnlps) {
        Offering[] offerings = new Offering[curnits.length * jnlps.length];
        int offeringsIndex = 0;
        for (int c = 0, cLength = curnits.length; c < cLength; c++) {
            for (int j = 0, jLength = jnlps.length; j < jLength; j++) {
                Offering offering = (Offering) applicationContext
                        .getBean("offering");
                SdsOffering sdsOffering = (SdsOffering) applicationContext
                        .getBean("sdsOffering");
                offering.setSdsOffering(sdsOffering);
                sdsOffering.setSdsCurnit(curnits[c].getSdsCurnit());
                sdsOffering.setSdsJnlp(jnlps[j].getSdsJnlp());
                sdsOffering.setName(curnits[c].getSdsCurnit().getName());
                this.offeringService.createOffering(offering);
                offerings[offeringsIndex] = offering;
                offeringsIndex++;
            }
        }
        return offerings;
    }

    public Curnit[] createDefaultCurnits(ApplicationContext applicationContext) {
        Set<String> keys = CURNITS.keySet();
        Curnit[] curnits = new Curnit[keys.size()];
        int i = 0;
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); i++) {
            Curnit curnit = (Curnit) applicationContext.getBean("curnit");
            SdsCurnit sdsCurnit = (SdsCurnit) applicationContext
                    .getBean("sdsCurnit");
            curnit.setSdsCurnit(sdsCurnit);
            String name = iterator.next();
            sdsCurnit.setName(name);
            sdsCurnit.setUrl(CURNITS.get(name));
            this.curnitService.createCurnit(curnit);
            curnits[i] = curnit;
        }

        return curnits;
    }

    public Jnlp[] createDefaultJnlps(ApplicationContext applicationContext) {
        Set<String> keys = JNLPS.keySet();
        Jnlp[] jnlps = new Jnlp[keys.size()];
        int i = 0;
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); i++) {
            Jnlp jnlp = (Jnlp) applicationContext.getBean("jnlp");
            SdsJnlp sdsJnlp = (SdsJnlp) applicationContext.getBean("sdsJnlp");
            jnlp.setSdsJnlp(sdsJnlp);
            String name = iterator.next();
            sdsJnlp.setName(name);
            sdsJnlp.setUrl(JNLPS.get(name));
            this.jnlpService.createJnlp(jnlp);
            jnlps[i] = jnlp;
        }
        return jnlps;
    }

    /**
     * @param applicationContext
     */
    public CreateDefaultOfferings(
            ConfigurableApplicationContext applicationContext) {
        init(applicationContext);
    }

    private void init(ApplicationContext applicationContext) {
        this.setCurnitService((CurnitService) applicationContext
                .getBean("curnitService"));
        this.setJnlpService((JnlpService) applicationContext
                .getBean("jnlpService"));
        this.setOfferingService((OfferingService) applicationContext
                .getBean("offeringService"));
    }

    /**
     * @param curnitService
     *            the curnitService to set
     */
    @Required
    public void setCurnitService(CurnitService curnitService) {
        this.curnitService = curnitService;
    }

    /**
     * @param jnlpService
     *            the jnlpService to set
     */
    @Required
    public void setJnlpService(JnlpService jnlpService) {
        this.jnlpService = jnlpService;
    }

    /**
     * @param offeringService
     *            the offeringService to set
     */
    @Required
    public void setOfferingService(OfferingService offeringService) {
        this.offeringService = offeringService;
    }
}