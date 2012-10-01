package org.chai.memms.maintenance

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.WorkOrder.Criticality;
import org.chai.memms.maintenance.WorkOrder.OrderStatus;

class CommentSpec  extends IntegrationTests{
	def "can create a comment"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:user,openOn: new Date(),assistaceRequested:false).save(failOnError:true)
		when:
		new Comment(content:"comment test", writtenOn:new Date().previous(), writtenBy:user, workOrder:workOrder).save(failOnError:true)
		then:
		Comment.count() == 1
	}

	def "all required fields needed on a comment"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:user,openOn: new Date(),assistaceRequested:false).save(failOnError:true)
		def commentWithErrors = new Comment()
		def expectedFieldErrors = ["content","writtenOn","writtenBy","workOrder"]

		when://All required fields in
		commentWithErrors.save()
		then:
		commentWithErrors.errors.fieldErrorCount == 4
		expectedFieldErrors.each{commentWithErrors.errors.hasFieldErrors(it)}
		Comment.count() == 0

		when://comment date should be before or equal to today
		commentWithErrors = new Comment(content:"comment test", writtenOn:new Date().next(), writtenBy:user, workOrder:workOrder)
		commentWithErrors.save()
		then:
		commentWithErrors.errors.hasFieldErrors("writtenOn") == true
		Comment.count() == 0
	}
}
