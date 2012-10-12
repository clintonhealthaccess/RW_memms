package org.chai.memms.maintenance

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.WorkOrder.Criticality;
import org.chai.memms.maintenance.WorkOrder.FailureReason;
import org.chai.memms.maintenance.WorkOrderStatus.OrderStatus;

class CommentSpec  extends IntegrationTests{
	def "can create a comment"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def workOrder = Initializer.newWorkOrder(equipment, "Nothing yet", Criticality.NORMAL,user, Initializer.now(),FailureReason.NOTSPECIFIED,OrderStatus.OPENATFOSA)
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
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def workOrder = Initializer.newWorkOrder(equipment, "Nothing yet", Criticality.NORMAL,user, Initializer.now(),FailureReason.NOTSPECIFIED,OrderStatus.OPENATFOSA)
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
