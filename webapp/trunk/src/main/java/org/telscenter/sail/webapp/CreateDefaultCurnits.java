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
	
	private CurnitProxy curnit1, curnit2;
	
	private URI uri1, uri2 = null;
	
	public CreateDefaultCurnits() {
		// Create repository
		rep = new ClientCurnitRepository();

		curnit1 = createCurnit("test-curnit");
		CurnitMetadataProxy metadata1 = curnit1.getMetaData();
		metadata1.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Test Curnit"));
		metadata1.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("This is a test curnit"));
		metadata1.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("paco martinez soria"));
		metadata1.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("TELS"));
		metadata1.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("yes"));
		uri1 = curnit1.getUri();

		curnit2 = createCurnit("test-curnit2");
		CurnitMetadataProxy metadata2 = curnit2.getMetaData();
		metadata2.setMetadataValue(MetadataKeyProxy.TITLE, new MetadataValueProxy("Test Curnit 2"));
		metadata2.setMetadataValue(MetadataKeyProxy.COMMENT, new MetadataValueProxy("This is another different test curnit"));
		metadata2.setMetadataValue(MetadataKeyProxy.AUTHOR, new MetadataValueProxy("alfredo landa"));
		metadata2.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("OTHER"));
		metadata2.setMetadataValue(MetadataKeyProxy.CURRENT, new MetadataValueProxy("no"));
		uri2 = curnit2.getUri();

	}

	private CurnitProxy createCurnit(String uriName) {
		// Create a curnit
		CurnitProxy curnit = new CurnitProxy();
		// Create content
		CurnitContentProxy content = curnit.getContent();
		URL url = CreateDefaultCurnits.class.getResource("pas-test.otml");
		URL urlRes1 = CreateDefaultCurnits.class.getResource("one.txt");
		URL urlRes2 = CreateDefaultCurnits.class.getResource("two.txt");
		List<File> resources = new ArrayList<File>();
		try {
			content.setOtmlFile(new File(url.toURI()));
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
	
	public void createDefaultCurnits() {
		rep.addELO(curnit1);
		rep.addELO(curnit2);
	}
	
	/**
	 * Creates Default Curnits
	 * @param args
	 */
	public static void main(String[] args) {
		CreateDefaultCurnits cdc = new CreateDefaultCurnits();
		cdc.createDefaultCurnits();
	}
}
