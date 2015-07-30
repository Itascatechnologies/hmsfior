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

import static in.co.itasca.hms.api.CarEdmProvider.ENTITY_SET_NAME_CARS;
import static in.co.itasca.hms.api.CarEdmProvider.ENTITY_SET_NAME_MANUFACTURERS;
import static in.co.itasca.hms.api.Constants.ENTITYSET_REGISTRATION;
import in.co.itasca.hms.api.service.IService;
import in.co.itasca.hms.api.service.RegistrationService;
import in.co.itasca.hms.api.service.ServiceFactory;

import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmEntitySetInfo;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties.ODataEntityProviderPropertiesBuilder;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.info.DeleteUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetMediaResourceUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.apache.olingo.odata2.core.edm.provider.EdmEntityTypeImplProv;
import org.apache.pdfbox.PDFBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;

public class CarODataSingleProcessor extends ODataSingleProcessor {

	@Override
	public ODataResponse deleteEntity(DeleteUriInfo uriInfo, String contentType)
			throws ODataException {
		
		String entityName = uriInfo.getTargetEntitySet().getName();
		 int key = getKeyValue(uriInfo.getKeyPredicates().get(0));
		 IService service =ServiceFactory.getInstance(null).getService(entityName);
		 boolean status =service.deleteUpdate(key);
		 if (status)
			 return ODataResponse.status(HttpStatusCodes.OK).build();
		 else
			 return ODataResponse.status(HttpStatusCodes.BAD_REQUEST).build();
		
	}

	private final CarDataStore dataStore;
	private HttpServletRequest request;

	public CarODataSingleProcessor(HttpServletRequest request) {
		this.request = request;

		dataStore = new CarDataStore();

	}

	@Override
	public ODataResponse readEntitySet(final GetEntitySetUriInfo uriInfo,
			final String contentType) throws ODataException {
		EdmEntitySet entitySet;
		if (uriInfo.getNavigationSegments().size() == 0) {
			entitySet = uriInfo.getStartEntitySet();
			String entitySetName = entitySet.getName();
			IService service = ServiceFactory.getInstance(null).getService(
					entitySetName);
			List<Map<String, Object>> result = service.getRecords();
			return EntityProvider.writeFeed(
					contentType,
					entitySet,
					result,
					EntityProviderWriteProperties.serviceRoot(
							getContext().getPathInfo().getServiceRoot())
							.build());
		} else if (uriInfo.getNavigationSegments().size() == 1) {
			// navigation first level, simplified example for illustration
			// purposes only
			entitySet = uriInfo.getTargetEntitySet();
			String name1 = entitySet.getName();
			String name = uriInfo.getNavigationSegments().get(0)
					.getNavigationProperty().getName();
			int key = getKeyValue(uriInfo.getKeyPredicates().get(0));
			String entityName = uriInfo.getStartEntitySet().getName();
			ServiceFactory sf = ServiceFactory.getInstance(null);
			IService service = sf.getService(entityName);
			List<Map<String, Object>> menu = service.getAssociation(key, name);
			return EntityProvider.writeFeed(
					contentType,
					entitySet,
					menu,
					EntityProviderWriteProperties.serviceRoot(
							getContext().getPathInfo().getServiceRoot())
							.build());
		}
		throw new ODataNotImplementedException();
	}

	@Override
	public ODataResponse readEntity(final GetEntityUriInfo uriInfo,
			final String contentType) throws ODataException {
		if (uriInfo.getNavigationSegments().size() == 0) {
			EdmEntitySet entitySet = uriInfo.getStartEntitySet();
			int id = getKeyValue(uriInfo.getKeyPredicates().get(0));
			Map<String, Object> data = ServiceFactory.getInstance(null)
					.getService(entitySet.getName()).getRecord(id);
			if (data != null) {
				URI serviceRoot = getContext().getPathInfo().getServiceRoot();
				ODataEntityProviderPropertiesBuilder propertiesBuilder = EntityProviderWriteProperties
						.serviceRoot(serviceRoot);
if(entitySet.getName().equals(ENTITYSET_REGISTRATION)){
	
	
	return EntityProvider.writeEntry(contentType, entitySet, data,
						propertiesBuilder.build());
} 

return EntityProvider.writeEntry(contentType, entitySet, data,
			propertiesBuilder.build());
			}
		}
		throw new ODataNotImplementedException();
	}

	private int getKeyValue(final KeyPredicate key) throws ODataException {
		EdmProperty property = key.getProperty();
		EdmSimpleType type = (EdmSimpleType) property.getType();
		return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT,
				property.getFacets(), Integer.class);
	}

	@Override
	public ODataResponse createEntity(PostUriInfo uriInfo, InputStream content,
			String requestContentType, String contentType)
			throws ODataException {
		//No support for creating and linking a new entry
		  if (uriInfo.getNavigationSegments().size() > 0) {
		  throw new ODataNotImplementedException();
		  }

		  //No support for media resources
		  if (uriInfo.getStartEntitySet().getEntityType().hasStream()) {
		  throw new ODataNotImplementedException();
		  }

		  EntityProviderReadProperties properties = EntityProviderReadProperties.init().mergeSemantic(false).build();

		  ODataEntry entry = EntityProvider.readEntry(requestContentType, uriInfo.getStartEntitySet(), content, properties);
		  //if something goes wrong in deserialization this is managed via the ExceptionMapper
		  //no need for an application to do exception handling here an convert the exceptions in HTTP exceptions

		  Map<String, Object> data = entry.getProperties();
		  //now one can use the data to create the entry in the backend ...
		  //retrieve the key value after creation, if the key is generated by the server
		  EdmEntitySet entitySet = uriInfo.getStartEntitySet();
		  String entityName = entitySet.getName();
		  IService service  =ServiceFactory.getInstance(null).getService(entityName);
		  Map<String, Object> record =service.createNewRecord(data);
		  

		 return EntityProvider.writeEntry(contentType, uriInfo.getStartEntitySet(), record, EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
		  //	return super.createEntity(uriInfo, content, requestContentType, contentType);
	}

	@Override
	public ODataResponse readEntityMedia(GetMediaResourceUriInfo uriInfo,
			String contentType) throws ODataException {
//		  if(entityName.equalsIgnoreCase(ENTITYSET_REGISTRATION)){
		
		if (uriInfo.getNavigationSegments().size() == 0) {
			EdmEntitySet entitySet = uriInfo.getStartEntitySet();
			int id = getKeyValue(uriInfo.getKeyPredicates().get(0));
			  try {
				
			
			  // Create a document and add a page to it
			PDDocument document ;
			IService service = ServiceFactory.getInstance(null).getService(ENTITYSET_REGISTRATION);
			RegistrationService reg= (RegistrationService) service; 
			// Save the results and ensure that the document is properly closed:
			  ByteArrayOutputStream out = new ByteArrayOutputStream();
			document =reg.getPDF(id);
			
			document.save("10.pdf");
			  document.save( out);
			  
			  document.close();
			   
			  String mimeType = "image/pdf";
			    return ODataResponse.fromResponse(EntityProvider.writeBinary(mimeType, out.toByteArray())).build();
			  } catch (Exception e) {
					// TODO: handle exception
				}  
			  
 		  }
		return super.readEntityMedia(uriInfo, contentType);
	}

	@Override
	public ODataResponse updateEntity(PutMergePatchUriInfo uriInfo,
			InputStream content, String requestContentType, boolean merge,
			String contentType) throws ODataException {
		EntityProviderReadProperties properties = EntityProviderReadProperties.init().mergeSemantic(false).build();

		  ODataEntry entry = EntityProvider.readEntry(requestContentType, uriInfo.getTargetEntitySet(), content, properties);
		  //if something goes wrong in deserialization this is managed via the ExceptionMapper,
		  //no need for an application to do exception handling here an convert the exceptions in HTTP exceptions

		  Map<String, Object> data = entry.getProperties();
		  String entityName = uriInfo.getTargetEntitySet().getName();

		  int key = getKeyValue(uriInfo.getKeyPredicates().get(0));
		  
		  IService service= ServiceFactory.getInstance(null).getService(entityName);
		data = service.updateRecord(key, data);
		  return ODataResponse.status(HttpStatusCodes.OK).build();
		  // TODO Auto-generated method stub
//		return super.updateEntity(uriInfo, content, requestContentType, merge,
//				contentType);
	}
}
