/**
 * 
 */
package org.chai.memms.equipment

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.chai.memms.util.Utils;
import org.grails.datastore.mapping.query.Query.Order;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * @author JeanKahigiso
 *
 */
class DepartmentService {
	static transactional = true
	
	def languageService;
	def sessionFactory;
	def dbFieldNames = 'names_'+languageService.getCurrentLanguagePrefix();
	def dbFieldDescriptions = 'descriptions_'+languageService.getCurrentLanguagePrefix();
	
	public List<Department> searchDepartment(String text, Map<String, String> params){
		def criteria = getSearchCriteria(text)
		List<Department> departments = []
		
		if (params['offset'] != null) criteria.setFirstResult(params['offset'])
		if (params['max'] != null) criteria.setMaxResults(params['max'])
		if (params['order'] != null) departments = criteria.addOrder(Order.asc(params['order'])).list()
		else  departments = criteria.addOrder(Order.asc(dbFieldNames)).list()
		
		StringUtils.split(text).each { chunk ->
			departments.retainAll { department ->
				Utils.matches(chunk, department.code) ||
				Utils.matches(chunk, department.getNames(languageService.getCurrentLanguage())) ||
				Utils.matches(chunk, department.getDescriptions(languageService.getCurrentLanguage()))	
			}
		}
		return departments
	}
	
	public Criteria getSearchCriteria(String text) {
		
		def criteria = sessionFactory.getCurrentSession().createCriteria(Department.class)
		
		def textRestrictions = Restrictions.conjunction()
		StringUtils.split(text).each { chunk ->
			def disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.ilike("code", chunk, MatchMode.ANYWHERE))
			disjunction.add(Restrictions.ilike(dbFieldNames, chunk, MatchMode.ANYWHERE))
			disjunction.add(Restrictions.ilike(dbFieldDescriptions, chunk, MatchMode.ANYWHERE))
			
			textRestrictions.add(disjunction)
		}
		criteria.add(textRestrictions)
		return criteria
		
	}
}
