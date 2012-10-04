package org.chai.memms.maintenance

import java.util.Date;

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.MaintenanceProcess.ProcessType;
import org.chai.memms.maintenance.WorkOrder.Criticality;
import org.chai.memms.maintenance.WorkOrder.FailureReason;
import org.chai.memms.maintenance.WorkOrder.OrderStatus;
import org.chai.memms.security.User;

class MaintenanceProcessSpec  extends IntegrationTests{
	def "can create a maintenance process"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:user,openOn: new Date(),assistaceRequested:false,failureReason:FailureReason.NOTSPECIFIED).save(failOnError:true)
		when:
		new MaintenanceProcess(name:"name test", addedOn:Initializer.now().previous(), addedBy:user, type:ProcessType.ACTION, workOrder:workOrder).save(failOnError:true)
		then:
		MaintenanceProcess.count() == 1
	}

	def "all required fields needed on maintenance process"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:user,openOn: new Date(),assistaceRequested:false,failureReason:FailureReason.NOTSPECIFIED).save(failOnError:true)
		def maintenanceProcessWithErrors = new MaintenanceProcess()
		def expectedFieldErrors = ["name","addedOn","addedBy","type","workOrder"]

		when://All required fields in
		maintenanceProcessWithErrors.save()
		then:
		maintenanceProcessWithErrors.errors.fieldErrorCount == 5
		expectedFieldErrors.each{maintenanceProcessWithErrors.errors.hasFieldErrors(it)}
		MaintenanceProcess.count() == 0
	}
	
	def "maintenance process date should be before or equal to today"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:user,openOn: new Date(),assistaceRequested:false,failureReason:FailureReason.NOTSPECIFIED).save(failOnError:true)
		def maintenanceProcessWithErrors
		expect:
		MaintenanceProcess.count() == 0
		when:
		maintenanceProcessWithErrors = new MaintenanceProcess(name:"name test date error", addedOn:new Date().next(), addedBy:user, type:ProcessType.ACTION, workOrder:workOrder)
		maintenanceProcessWithErrors.save()
		then:
		MaintenanceProcess.count() == 0
		maintenanceProcessWithErrors.errors.hasFieldErrors("addedOn") == true
	}
}
