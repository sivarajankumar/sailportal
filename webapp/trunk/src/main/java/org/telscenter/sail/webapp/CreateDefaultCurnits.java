/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.sail.webapp.service.curnit.CurnitService;
import net.sf.sail.webapp.spring.SpringConfiguration;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telscenter.sail.webapp.dao.module.impl.RooloOtmlModuleDao;
import org.telscenter.sail.webapp.domain.impl.CreateRooloOtmlModuleParameters;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.service.project.ProjectService;
import org.telscenter.sail.webapp.service.project.impl.ProjectServiceImpl;

import roolo.curnit.client.basicProxy.CurnitContentProxy;
import roolo.curnit.client.basicProxy.CurnitMetadataProxy;
import roolo.curnit.client.basicProxy.CurnitProxy;
import roolo.curnit.client.basicProxy.MetadataKeyProxy;
import roolo.curnit.client.basicProxy.MetadataValueProxy;
import roolo.curnit.client.impl.ClientCurnitRepository;

/**
 * Adds default otml-curnits into Roolo repository.
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class CreateDefaultCurnits {
	
	private ClientCurnitRepository rep;
	
	private CurnitService curnitService;
	
	
	public CreateDefaultCurnits(
			ConfigurableApplicationContext applicationContext) {
		this.setCurnitService((CurnitService) applicationContext.getBean("curnitService"));
	}

	private CurnitProxy createAirbagsCurnit() {
		CurnitProxy curnit = createCurnit("airbags", CreateDefaultCurnits.class.getResource("Airbags.otml"));
		CurnitMetadataProxy metadata2 = curnit.getMetaData();
		metadata2.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Airbags"));
		metadata2.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("Airbags otml. Converted most up to date airbags including models and draw"));
		metadata2.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("Kevin McElhaney"));
		metadata2.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("TELS"));
		metadata2.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("true"));
		metadata2.setMetadataValue(MetadataKeyProxy.DESCRIPTION, new MetadataValueProxy("Airbags. Recommended for middle school students."));
		metadata2.setMetadataValue(MetadataKeyProxy.GRADELEVEL, new MetadataValueProxy("5,6,7"));
		metadata2.setMetadataValue(MetadataKeyProxy.SUBJECT, new MetadataValueProxy("physics"));
		metadata2.setMetadataValue(MetadataKeyProxy.KEYWORDS, new MetadataValueProxy("airbags, inertia"));

		return curnit;
	}

	private CurnitProxy createChemicalReactionsCurnit() {
		CurnitProxy curnit = createCurnit("chemicalreactions", CreateDefaultCurnits.class.getResource("Chemical_Reactions.otml"));
		CurnitMetadataProxy metadata1 = curnit.getMetaData();
		metadata1.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Chemical Reactions"));
		metadata1.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("Chemical Reactions otml. Fixed Challenge Question, missing Discussion steps"));
		metadata1.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("Jennie Chiu"));
		metadata1.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("TELS"));
		metadata1.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("true"));
		metadata1.setMetadataValue(MetadataKeyProxy.DESCRIPTION, new MetadataValueProxy("Chemical Reactions. Recommended for middle school students."));
		metadata1.setMetadataValue(MetadataKeyProxy.GRADELEVEL, new MetadataValueProxy("5,6,7"));
		metadata1.setMetadataValue(MetadataKeyProxy.SUBJECT, new MetadataValueProxy("Chemistry"));
		metadata1.setMetadataValue(MetadataKeyProxy.KEYWORDS, new MetadataValueProxy("experiment, heat"));

		return curnit;
	}
	
	private CurnitProxy createMeiosisCurnit() {
		CurnitProxy curnit = createCurnit("meiosis", CreateDefaultCurnits.class.getResource("Meiosis.otml"));
		CurnitMetadataProxy metadata1 = curnit.getMetaData();
		metadata1.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Meiosis"));
		metadata1.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("Meiosis otml. Missing Self Test and Student Assessment steps."));
		metadata1.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("Beat Schwendimann"));
		metadata1.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("TELS"));
		metadata1.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("true"));
		metadata1.setMetadataValue(MetadataKeyProxy.DESCRIPTION, new MetadataValueProxy("Meiosis. Recommended for middle school students."));
		metadata1.setMetadataValue(MetadataKeyProxy.GRADELEVEL, new MetadataValueProxy("5,6,7"));
		metadata1.setMetadataValue(MetadataKeyProxy.SUBJECT, new MetadataValueProxy("Biology"));
		metadata1.setMetadataValue(MetadataKeyProxy.KEYWORDS, new MetadataValueProxy("cell division, heat"));

		return curnit;
	}

	private CurnitProxy createHydrogenCarsCurnit() {
		CurnitProxy curnit = createCurnit("hydrogencellcars", CreateDefaultCurnits.class.getResource("Hydrogen_Cars.otml"));
		CurnitMetadataProxy metadata1 = curnit.getMetaData();
		metadata1.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Hydrogen Cars"));
		metadata1.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("Hydrogen Cars otml. All steps except Self Test "));
		metadata1.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("Helen Zhang"));
		metadata1.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("TELS"));
		metadata1.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("true"));
		metadata1.setMetadataValue(MetadataKeyProxy.DESCRIPTION, new MetadataValueProxy("Hydrogen Cars. Recommended for middle school students."));
		metadata1.setMetadataValue(MetadataKeyProxy.GRADELEVEL, new MetadataValueProxy("5,6,7"));
		metadata1.setMetadataValue(MetadataKeyProxy.SUBJECT, new MetadataValueProxy("Chemistry"));
		metadata1.setMetadataValue(MetadataKeyProxy.KEYWORDS, new MetadataValueProxy("global warming, molecules, hydrogen"));

		return curnit;
	}
	
	private CurnitProxy createGlobalWarmingCurnit() {
		CurnitProxy curnit = createCurnit("globalwarming", CreateDefaultCurnits.class.getResource("Global_Warming.otml"));
		CurnitMetadataProxy metadata1 = curnit.getMetaData();
		metadata1.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Global Warming"));
		metadata1.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("Global Warming otml. All steps"));
		metadata1.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("Keisha Varma"));
		metadata1.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("TELS"));
		metadata1.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("true"));
		metadata1.setMetadataValue(MetadataKeyProxy.DESCRIPTION, new MetadataValueProxy("Global Warming. Recommended for middle school students."));
		metadata1.setMetadataValue(MetadataKeyProxy.GRADELEVEL, new MetadataValueProxy("5,6,7"));
		metadata1.setMetadataValue(MetadataKeyProxy.SUBJECT, new MetadataValueProxy("Earth Science"));
		metadata1.setMetadataValue(MetadataKeyProxy.KEYWORDS, new MetadataValueProxy("global warming"));

		return curnit;
	}

	private CurnitProxy createThermodynamicsCurnit() {
		CurnitProxy curnit = createCurnit("thermodynamics", CreateDefaultCurnits.class.getResource("Thermodynamics.otml"));
		CurnitMetadataProxy metadata1 = curnit.getMetaData();
		metadata1.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Thermodynamics"));
		metadata1.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("Thermodynamics otml, missing Student Assessment step"));
		metadata1.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("Hsin-Yi Chang"));
		metadata1.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("TELS"));
		metadata1.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("true"));
		metadata1.setMetadataValue(MetadataKeyProxy.DESCRIPTION, new MetadataValueProxy("Thermodynamics. Recommended for middle school students."));
		metadata1.setMetadataValue(MetadataKeyProxy.GRADELEVEL, new MetadataValueProxy("5,6,7"));
		metadata1.setMetadataValue(MetadataKeyProxy.SUBJECT, new MetadataValueProxy("Chemistry"));
		metadata1.setMetadataValue(MetadataKeyProxy.KEYWORDS, new MetadataValueProxy("thermodynamics, heat"));

		return curnit;
	}
	
	private CurnitProxy createCurnit(String uriName, URL otmlUrl) {
		// Create a curnit
		CurnitProxy curnit = new CurnitProxy();
		// Create content
		CurnitContentProxy content = curnit.getContent();
		URL urlRes1 = CreateDefaultCurnits.class.getResource("one.txt");
		URL urlRes2 = CreateDefaultCurnits.class.getResource("two.txt");
		List<File> resources = new ArrayList<File>();
		try {
			content.setOtmlFile(new File(otmlUrl.toURI()));
			// add resources
			File res1 = new File(urlRes1.toURI());
			File res2 = new File(urlRes2.toURI());
			resources.add(res1);
			resources.add(res2);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content.setOtmlResources(resources);
		// Create metadata
		CurnitMetadataProxy metadata = curnit.getMetaData();
		URI uri = null;
		try {
			uri = new URI(uriName);
			metadata.setMetadataValue(MetadataKeyProxy.URI, new MetadataValueProxy(uri.toString()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curnit;
	}
	
	public void createDefaultCurnits(ConfigurableApplicationContext applicationContext) {
		// Create repository
		rep = new ClientCurnitRepository();
		CurnitProxy airbagsCurnit = createAirbagsCurnit();
		rep.addELO(airbagsCurnit);
		saveToLocalDb(applicationContext, airbagsCurnit);
		
		CurnitProxy chemicalReactionsCurnit = createChemicalReactionsCurnit();
		rep.addELO(chemicalReactionsCurnit);
		saveToLocalDb(applicationContext, chemicalReactionsCurnit);
		
		CurnitProxy meiosisCurnit = createMeiosisCurnit();
		rep.addELO(meiosisCurnit);
		saveToLocalDb(applicationContext, meiosisCurnit);
		
		
		CurnitProxy hydrogenCarsCurnit = createHydrogenCarsCurnit();
		rep.addELO(hydrogenCarsCurnit);
		saveToLocalDb(applicationContext, hydrogenCarsCurnit);
		
		CurnitProxy globalWarmingCurnit = createGlobalWarmingCurnit();
		rep.addELO(globalWarmingCurnit);
		saveToLocalDb(applicationContext, globalWarmingCurnit);
		
		
		CurnitProxy thermodynamicsCurnit = createThermodynamicsCurnit();
		rep.addELO(thermodynamicsCurnit);
		saveToLocalDb(applicationContext, thermodynamicsCurnit);
	}

	/**
	 * @param applicationContext 
	 * @param curnitProxy
	 */
	private void saveToLocalDb(ConfigurableApplicationContext applicationContext, CurnitProxy curnitProxy) {
		CreateRooloOtmlModuleParameters params = (CreateRooloOtmlModuleParameters) 
		    applicationContext.getBean("createRooloOtmlModuleParameters");
		params.setName(curnitProxy.getMetaData().getMetadataValue(MetadataKeyProxy.TITLE).getStringValue());
		params.setUrl(RooloOtmlModuleDao.defaultOtrunkCurnitUrl);
		params.setRoolouri(curnitProxy.getUri().toString());
		params.setRooloRepositoryUrl(RooloOtmlModuleDao.rooloRepositoryUrl);
		curnitService.createCurnit(params);
	}
	
	/**
	 * Creates Default Curnits
	 * @param args
	 */
	public static void main(String[] args) {		
		System.setProperty("org.apache.jackrabbit.repository.conf", "/Users/hirokiterashima/eclipseworkspaces/telsportalworkspace3.3/webapp/repository.xml");
		System.setProperty("org.apache.jackrabbit.repository.home", "/Users/hirokiterashima/eclipseworkspaces/telsportalworkspace3.3/webapp/repository");

		try {
	        ConfigurableApplicationContext applicationContext = null;
			SpringConfiguration springConfig;
			springConfig = (SpringConfiguration) BeanUtils
			.instantiateClass(Class.forName("org.telscenter.sail.webapp.spring.impl.SpringConfigurationImpl"));
			applicationContext = new ClassPathXmlApplicationContext(
					springConfig.getRootApplicationContextConfigLocations());

			CreateDefaultCurnits cdc = new CreateDefaultCurnits(applicationContext);
			cdc.createDefaultCurnits(applicationContext);
			applicationContext.close();
		} catch (BeanInstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//cdc.createDefaultProjects();
	}

	/**
	 * @param curnitService the curnitService to set
	 */
	public void setCurnitService(CurnitService curnitService) {
		this.curnitService = curnitService;
	}
}
