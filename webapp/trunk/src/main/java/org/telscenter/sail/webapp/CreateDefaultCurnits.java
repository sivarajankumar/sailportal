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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.sf.sail.webapp.service.curnit.CurnitService;
import net.sf.sail.webapp.spring.SpringConfiguration;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telscenter.sail.webapp.dao.module.impl.RooloOtmlModuleDao;
import org.telscenter.sail.webapp.domain.impl.CreateRooloOtmlModuleParameters;

import roolo.api.IContent;
import roolo.api.IMetadata;
import roolo.api.IMetadataValueContainer;
import roolo.curnit.client.CurnitClientMetadataKeys;
import roolo.curnit.client.basicProxy.CurnitProxy;
import roolo.curnit.client.basicProxy.MetadataKeyProxy;
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
		IMetadata<MetadataKeyProxy> metadata2 = curnit.getMetadata();
		IMetadataValueContainer container;
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey());
		container.setValue("Airbags");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.COMMENT.getKey());
		container.setValue("Airbags otml. Converted most up to date airbags including models and draw");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.AUTHOR.getKey());
		container.setValue("Kevin McElhaney");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.FAMILYTAG.getKey());
		container.setValue("TELS");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.ISCURRENT.getKey());
		container.setValue("true");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.DESCRIPTION.getKey());
		container.setValue("Airbags. Recommended for middle school students.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.GRADELEVEL.getKey());
		container.setValue("5,6,7");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.SUBJECT.getKey());
		container.setValue("physics");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.KEYWORDS.getKey());
		container.setValue("airbags, inertia");
		
		return curnit;
	}

	private CurnitProxy createChemicalReactionsCurnit() {
		CurnitProxy curnit = createCurnit("chemicalreactions", CreateDefaultCurnits.class.getResource("Chemical_Reactions.otml"));
		IMetadata<MetadataKeyProxy> metadata2 = curnit.getMetadata();
		IMetadataValueContainer container;
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey());
		container.setValue("Chemical Reactions");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.COMMENT.getKey());
		container.setValue("Chemical Reactions otml. Fixed Challenge Question, missing Discussion steps");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.AUTHOR.getKey());
		container.setValue("Jennie Chiu");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.FAMILYTAG.getKey());
		container.setValue("TELS");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.ISCURRENT.getKey());
		container.setValue("true");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.DESCRIPTION.getKey());
		container.setValue("Chemical Reactions. Recommended for middle school students.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.GRADELEVEL.getKey());
		container.setValue("5,6,7");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.SUBJECT.getKey());
		container.setValue("Chemistry");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.KEYWORDS.getKey());
		container.setValue("experiment, heat");


		return curnit;
	}
	
	private CurnitProxy createMeiosisCurnit() {
		CurnitProxy curnit = createCurnit("meiosis", CreateDefaultCurnits.class.getResource("Meiosis.otml"));
		IMetadata<MetadataKeyProxy> metadata2 = curnit.getMetadata();
		IMetadataValueContainer container;
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey());
		container.setValue("Meiosis");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.COMMENT.getKey());
		container.setValue("Meiosis otml. Missing Self Test and Student Assessment steps.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.AUTHOR.getKey());
		container.setValue("Beat Schwendimann");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.FAMILYTAG.getKey());
		container.setValue("TELS");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.ISCURRENT.getKey());
		container.setValue("true");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.DESCRIPTION.getKey());
		container.setValue("Meiosis. Recommended for middle school students.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.GRADELEVEL.getKey());
		container.setValue("5,6,7");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.SUBJECT.getKey());
		container.setValue("Biology");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.KEYWORDS.getKey());
		container.setValue("cell division, heat");

		return curnit;
	}

	private CurnitProxy createHydrogenCarsCurnit() {
		CurnitProxy curnit = createCurnit("hydrogencellcars", CreateDefaultCurnits.class.getResource("Hydrogen_Cars.otml"));
		IMetadata<MetadataKeyProxy> metadata2 = curnit.getMetadata();
		IMetadataValueContainer container;
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey());
		container.setValue("Hydrogen Cars");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.COMMENT.getKey());
		container.setValue("Hydrogen Cars otml. All steps except Self Test ");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.AUTHOR.getKey());
		container.setValue("Helen Zhang");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.FAMILYTAG.getKey());
		container.setValue("TELS");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.ISCURRENT.getKey());
		container.setValue("true");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.DESCRIPTION.getKey());
		container.setValue("Hydrogen Cars. Recommended for middle school students.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.GRADELEVEL.getKey());
		container.setValue("5,6,7");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.SUBJECT.getKey());
		container.setValue("Chemistry");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.KEYWORDS.getKey());
		container.setValue("global warming, molecules, hydrogen");

		return curnit;
	}
	
	private CurnitProxy createGlobalWarmingCurnit() {
		CurnitProxy curnit = createCurnit("globalwarming", CreateDefaultCurnits.class.getResource("Global_Warming.otml"));
		IMetadata<MetadataKeyProxy> metadata2 = curnit.getMetadata();
		IMetadataValueContainer container;
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey());
		container.setValue("Global Warming");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.COMMENT.getKey());
		container.setValue("Global Warming otml. All steps");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.AUTHOR.getKey());
		container.setValue("Keisha Varma");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.FAMILYTAG.getKey());
		container.setValue("TELS");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.ISCURRENT.getKey());
		container.setValue("true");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.DESCRIPTION.getKey());
		container.setValue("Global Warming. Recommended for middle school students.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.GRADELEVEL.getKey());
		container.setValue("5,6,7");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.SUBJECT.getKey());
		container.setValue("Earth Science");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.KEYWORDS.getKey());
		container.setValue("global warming");

		return curnit;
	}

	private CurnitProxy createThermodynamicsCurnit() {
		CurnitProxy curnit = createCurnit("thermodynamics", CreateDefaultCurnits.class.getResource("Thermodynamics.otml"));
		IMetadata<MetadataKeyProxy> metadata2 = curnit.getMetadata();
		IMetadataValueContainer container;
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey());
		container.setValue("Thermodynamics");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.COMMENT.getKey());
		container.setValue("Thermodynamics otml, missing Student Assessment step");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.AUTHOR.getKey());
		container.setValue("Hsin-Yi Chang");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.FAMILYTAG.getKey());
		container.setValue("TELS");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.ISCURRENT.getKey());
		container.setValue("true");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.DESCRIPTION.getKey());
		container.setValue("Thermodynamics. Recommended for middle school students.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.GRADELEVEL.getKey());
		container.setValue("5,6,7");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.SUBJECT.getKey());
		container.setValue("Chemistry");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.KEYWORDS.getKey());
		container.setValue("thermodynamics, heat");

		return curnit;
	}
	
	private CurnitProxy createDiyCurnit() {
		CurnitProxy curnit = createCurnit("onedimensionalmotion", CreateDefaultCurnits.class.getResource("loops-test.otml"));
		IMetadata<MetadataKeyProxy> metadata2 = curnit.getMetadata();
		IMetadataValueContainer container;
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey());
		container.setValue("Loops One Dimensional Motion");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.COMMENT.getKey());
		container.setValue("LOOPS DIY test otml");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.AUTHOR.getKey());
		container.setValue("Carolyn Staudt");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.FAMILYTAG.getKey());
		container.setValue("TELS");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.ISCURRENT.getKey());
		container.setValue("true");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.DESCRIPTION.getKey());
		container.setValue("How to interpret graphs of position and speed versus time for one-dimensional motion.");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.GRADELEVEL.getKey());
		container.setValue("5,6,7");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.SUBJECT.getKey());
		container.setValue("physics");
		container = metadata2.getMetadataValueContainer(CurnitClientMetadataKeys.KEYWORDS.getKey());
		container.setValue("motion, graph, dimension, speed, time");

		return curnit;
	}

	
	private CurnitProxy createCurnit(String uriName, URL otmlUrl) {
		// Create a curnit
		CurnitProxy curnit = new CurnitProxy();
		// Create content
		IContent content = curnit.getContent();
		try {
			FileInputStream fis;
				fis = new FileInputStream( new File(otmlUrl.toURI()) );
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte [] buffer = new byte[4096];
			int read;
				while ( (read = fis.read(buffer))!= -1) {
					baos.write( buffer, 0, read);
				}
			
			fis.close();
			content.setBytes( baos.toByteArray());
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create metadata
		IMetadata<MetadataKeyProxy> metadata = curnit.getMetadata();
		URI uri = null;
		try {
			uri = new URI(uriName);
			IMetadataValueContainer container = metadata.getMetadataValueContainer(CurnitClientMetadataKeys.URI.getKey());
			container.setValue(uri.toString());			
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

		CurnitProxy diyCurnit = createDiyCurnit();
		rep.addELO(diyCurnit);
		saveToLocalDb(applicationContext, diyCurnit);

	}

	/**
	 * @param applicationContext 
	 * @param curnitProxy
	 */
	private void saveToLocalDb(ConfigurableApplicationContext applicationContext, CurnitProxy curnitProxy) {
		CreateRooloOtmlModuleParameters params = (CreateRooloOtmlModuleParameters) 
		    applicationContext.getBean("createRooloOtmlModuleParameters");
		params.setName(curnitProxy.getMetadata().getMetadataValueContainer(CurnitClientMetadataKeys.TITLE.getKey()).getValue().toString());
		params.setUrl(RooloOtmlModuleDao.defaultOtrunkCurnitUrl);
		//params.setUrl("http://rails.dev.concord.org/curnits/otrunk-curnit-external-diytest.jar");
		params.setRoolouri(curnitProxy.getUri().toString());
		params.setRooloRepositoryUrl(RooloOtmlModuleDao.rooloRepositoryUrl);
		params.setCurnitProxy(curnitProxy);
		curnitService.createCurnit(params);
	}
	
	/**
	 * Creates Default Curnits
	 * @param args
	 */
	public static void main(String[] args) {		
		System.setProperty("org.apache.jackrabbit.repository.conf", "repository.xml");
		System.setProperty("org.apache.jackrabbit.repository.home", "repository");

		try {
	        ConfigurableApplicationContext applicationContext = null;
			SpringConfiguration springConfig;
			springConfig = (SpringConfiguration) BeanUtils
			.instantiateClass(Class.forName("org.telscenter.sail.webapp.spring.impl.SpringConfigurationImpl"));
			applicationContext = new ClassPathXmlApplicationContext(
					springConfig.getRootApplicationContextConfigLocations());

//			CreateDefaultCurnits cdc = new CreateDefaultCurnits(applicationContext);
//			cdc.createDefaultCurnits(applicationContext);
			//applicationContext.close();
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
