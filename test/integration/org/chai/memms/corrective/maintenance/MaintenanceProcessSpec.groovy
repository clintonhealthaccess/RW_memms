package org.chai.memms.corrective.maintenance

import java.util.Date;

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.corrective.maintenance.MaintenanceProcess;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.corrective.maintenance.MaintenanceProcess.ProcessType;
import org.chai.memms.corrective.maintenance.WorkOrder.Criticality;
import org.chai.memms.corrective.maintenance.WorkOrder.FailureReason;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus;
import org.chai.memms.security.User;

class MaintenanceProcessSpec  extends IntegrationTests{
	def "can create a maintenance process"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def workOrder = Initializer.newWorkOrder(equipment, "Nothing yet", Criticality.NORMAL,user, Initializer.now(),FailureReason.NOTSPECIFIED,OrderStatus.OPENATFOSA)
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
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def workOrder = Initializer.newWorkOrder(equipment, "Nothing yet", Criticality.NORMAL,user, Initializer.now(),FailureReason.NOTSPECIFIED,OrderStatus.OPENATFOSA)
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
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def workOrder = Initializer.newWorkOrder(equipment, "Nothing yet", Criticality.NORMAL,user, Initializer.now(),FailureReason.NOTSPECIFIED,OrderStatus.OPENATFOSA)
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
