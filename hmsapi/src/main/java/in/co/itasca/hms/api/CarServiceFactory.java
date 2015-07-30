/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package in.co.itasca.hms.api;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.edm.provider.EntityType;
import org.apache.olingo.odata2.api.edm.provider.Mapping;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.core.edm.provider.EdmxProvider;

public class CarServiceFactory extends ODataServiceFactory {

  @Override
  public ODataService createService(final ODataContext ctx) throws ODataException {

//    EdmProvider edmProvider = new CarEdmProvider();

	  HttpServletRequest request=(HttpServletRequest) ctx.getParameter(ctx.HTTP_SERVLET_REQUEST_OBJECT);
	  InputStream inputstream =request.getSession().getServletContext().getResourceAsStream("/WEB-INF/carservice.xml");
	  EdmxProvider edmxProvider = new EdmxProvider();
	 
	  edmxProvider =edmxProvider.parse(inputstream, false);
	  FullQualifiedName fqReg = new FullQualifiedName("myodata", "Registration");
EntityType entReg = edmxProvider.getEntityType(fqReg);
entReg.setHasStream(true);
Mapping mapping = new Mapping();
mapping.setMediaResourceMimeTypeKey("application/pdf");
mapping.setInternalName("abc.pdf");

entReg.setMapping(mapping)	; 
	  ODataSingleProcessor singleProcessor = new CarODataSingleProcessor(request);

    return createODataSingleProcessorService(edmxProvider, singleProcessor);
  }
}
