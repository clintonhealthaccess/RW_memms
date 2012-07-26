package org.chai.memms

/*
* Copyright (c) 2012, Clinton Health Access Initiative.
*
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the <organization> nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
//import org.codehaus.groovy.grails.commons.ConfigurationHolder;


abstract class AbstractEntityController extends AbstractController {		
	
	def entityExportService
	
	def index = {
        redirect(action: "list", params: params)
    }

	def delete = {		
		def entity = getEntity(params.int('id'));
		if (log.isInfoEnabled()) log.info("deleting entity: "+entity)
		
		if (entity != null) {
			try {
				deleteEntity(entity)
				
				if (!flash.message) flash.message = message(code: 'default.deleted.message', args: [message(code: getLabel(), default: 'entity'), params.id])
				redirect(uri: getTargetURI())
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = message(code: 'default.not.deleted.message', args: [message(code: getLabel(), default: 'entity'), params.id])
				redirect(uri: getTargetURI())
			}
		}
		else {
			flash.message = message(code: 'default.not.found.message', args: [message(code: getLabel(), default: 'entity'), params.id])
			redirect(uri: getTargetURI())
		}
	}
	
	def edit = {
		def entity = getEntity(params.int('id'));
		if (log.isInfoEnabled()) log.info("editing entity: "+entity);

		if (entity == null) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: getLabel(), default: 'entity'), params.id])
			redirect(uri: getTargetURI())
		}
		else {
			def model = getModel(entity)
			model << [template: getTemplate()]
			model << [targetURI: getTargetURI()]
			render(view: '/entity/edit', model: model)
		}
	}
	
	def create = {
		def entity = createEntity()
		bindParams(entity);
		
		def model = getModel(entity)
		model << [template: getTemplate()]
		model << [targetURI: getTargetURI()]
		log.debug("abstract entity model : " + model.each{it})
		render(view: '/entity/edit', model: model)
	}
	
	def save = {
//		 TODO uncomment this to enable multiple send check
//		withForm {
			saveWithoutTokenCheck()
//		}.invalidToken {
//			log.warn("clicked twice");
//		}
	}
			
	def saveWithoutTokenCheck = {
		if (log.isDebugEnabled()) log.debug ('saving entity with params:'+params)		
		
		def entity = getEntity(params.int('id'));
		if (entity == null) {
			entity = createEntity()
		}
		
		bindParams(entity)
		if (log.isDebugEnabled()) log.debug('bound params, entity: '+entity)
		
		// TODO this should be not necessary once the unique 
		// validation constraints is fixed in grails
		def codeIsValid = true
		if (params.containsKey("code")){
			String code = (String) params.get("code");
			codeIsValid = validateCode(entity, code)
			if(!codeIsValid){
				entity.errors.rejectValue("code","entity.code.notunique", "Code needs to be unique.}");
			}
		}
					
		if (!codeIsValid || !validateEntity(entity)) {
			if (log.isInfoEnabled()) log.info ("validation error in ${entity}: ${entity.errors}}")
			
			def model = getModel(entity)
			model << [template: getTemplate()]
			model << [targetURI: getTargetURI()]
			render(view: '/entity/edit', model: model)
		}
		else {
			saveEntity(entity);
			
			flash.message = message(code: 'default.saved.message', args: [message(code: getLabel(), default: 'entity'), params.id])
			redirect(url: getTargetURI())
		}
	}	
	
	def validateEntity(def entity) {
		return entity.validate()
	}

	def validateCode(def entity, def code){
		if(code != null){			
			def entityWithCode = entity.getClass().findByCode(code);
			if(entityWithCode != null){
				if(entityWithCode.id != entity.id){
					return false;
				}
			}
		}
		return true;
	}
	
	def saveEntity(def entity) {
		entity.save()
	}

	def deleteEntity(def entity) {
		entity.delete()
	}
	
	/**
	 * This binds a list of i18n fields passed in the params to the map <String, Translation>
	 * passed as parameter.
	 * The format for the field name is the following:
	 * - paramName: holds the <map_key> list
	 * - paramName[<map_key>].<language>: holds the value for that particular language
	 * 
	 * @param paramName the name of the param in the form
	 * @param map the map to fill
	 */
//	def bindTranslationMap(def paramName, def map) {
//		params.list(paramName).each { prefix ->
//			Translation translation = new Translation()
//			languageService.availableLanguages.each { language ->
//				translation[language] = params[paramName+'['+prefix+'].'+language]
//			}
//			map.put(prefix, translation)
//		}
//	}
	
	protected abstract def bindParams(def entity);
	
	protected abstract def getModel(def entity);
		
	protected abstract def getEntity(def id);
	
	protected abstract def createEntity();
	
	protected abstract def getTemplate();
	
	protected abstract def getLabel();
	
	def exporter = {
		def entityClazz = getEntityClass();
		if (entityClazz instanceof Class) entityClazz = [entityClazz]
		
		List<String> filenames = new ArrayList<String>();
		List<File> csvFiles = new ArrayList<File>();
		
		for (Class clazz : entityClazz){
			String filename = entityExportService.getExportFilename(clazz);
			filenames.add(filename);
			csvFiles.add(entityExportService.getExportFile(filename, clazz));
		}
		
		String zipFilename = StringUtils.join(filenames, "_")
		def zipFile = Utils.getZipFile(csvFiles, zipFilename)
		
		if(zipFile.exists()){
			response.setHeader("Content-disposition", "attachment; filename=" + zipFile.getName());
			response.setContentType("application/zip");
			response.setHeader("Content-length", zipFile.length().toString());
			response.outputStream << zipFile.newInputStream()
		}
	}
	
	def importer = {
		def clazz = getEntityClass();
		
		if(clazz instanceof Class){
			redirect (controller: 'entityImporter', action: 'importer', params: [entityClass: clazz.name]);
		}
	}
	
	protected abstract def getEntityClass();
	
}
