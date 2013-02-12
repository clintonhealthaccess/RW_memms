/**
 * 
 */
package org.chai.memms.spare.part

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.chai.memms.spare.part.SparePart;
import org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart;
import org.chai.memms.security.User;

/**
 * @author Aphrodice Rwagaju
 *
 */
class SparePartStatusService {
	static transactional = true
	
	List<SparePart> getSparePartStatusBySparePart(SparePart sparePart, Map<String,String> params){
		def criteria = SparePartStatus.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"dateCreated",order: params.order ?:"desc"){
			eq("sparePart",sparePart)
		}
	}
	
	SparePart createSparePartStatus(User changedBy,StatusOfSparePart value, SparePart sparePart,Date dateOfEvent, Map<String,String> reasons){
		def status = new SparePartStatus(dateOfEvent:dateOfEvent,changedBy:changedBy,statusOfSparePart:value)
		Utils.setLocaleValueInMap(status,reasons,"Reasons")
		sparePart.statusOfSparePart = value
		if(sparePart.id){
			//When updating an sparePart
			sparePart.lastModified = changedBy
		}
		sparePart.addToStatus(status)
		if(!sparePart.statusOfSparePart.equals(StatusOfSparePart.DISPOSED))
			sparePart.save(failOnError:true, flush:true)
		return sparePart
	}

}
