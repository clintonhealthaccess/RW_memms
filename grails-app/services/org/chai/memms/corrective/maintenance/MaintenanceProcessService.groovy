/** 
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
package org.chai.memms.corrective.maintenance

import org.chai.memms.corrective.maintenance.CorrectiveProcess;
import org.chai.memms.corrective.maintenance.CorrectiveProcess.ProcessType;
import org.chai.memms.corrective.maintenance.WorkOrder;
import org.chai.memms.security.User;
import org.chai.memms.preventive.maintenance.Prevention;
import org.chai.memms.preventive.maintenance.PreventiveProcess;



/**
 * @author Jean Kahigiso M.
 *
 */
class MaintenanceProcessService {
	static transactional = true	

		
	WorkOrder addWorkOrderProcess(WorkOrder workOrder,ProcessType type,String name,User addedBy){
		CorrectiveProcess process = new CorrectiveProcess(type: type,name:name,addedBy:addedBy)
		workOrder.addToProcesses(process)
		workOrder.lastModifiedBy = addedBy
		return workOrder.save(failOnError:true)
	}

	WorkOrder deleteWorkOrderProcess(CorrectiveProcess process,User deletedBy){
		WorkOrder workOrder = process.workOrder		
		workOrder.removeFromProcesses(process)
		workOrder.lastModifiedBy = deletedBy
		process.delete()
		return workOrder.save(failOnError:true)
	}

	Prevention addPreventionProcess(Prevention prevention,String name,User addedBy){
		PreventiveProcess process = new PreventiveProcess(name:name,addedBy:addedBy)
		prevention.addToProcesses(process)
		return prevention.save(failOnError:true)

	}
	Prevention deletePreventionProcess(PreventiveProcess process,User deletedBy){
		Prevention prevention = process.prevention		
		prevention.removeFromProcesses(process)
		process.delete()
		return prevention.save(failOnError:true)
	}

}
