package org.chai.memms.maintenance

import org.chai.memms.Initializer;

import org.chai.memms.IntegrationTests

class WorkOrderSpec  extends IntegrationTests{
	def "can create a workOrder"(){
		setup:
		setupLocationTree()
		setupEquipment()
		Initializer.newWorkOrder(Equipment.findBySerialNumber, "Nothing yet", def criticality, def status, def addedBy, def openOn, def closedOn, def assistaceRequested)
	}
}
