package org.chai.memms.corrective.maintenance

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.corrective.maintenance.Comment;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.corrective.maintenance.WorkOrder.Criticality;
import org.chai.memms.corrective.maintenance.WorkOrder.FailureReason;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus;

class CommentServiceSpec  extends IntegrationTests{
	def commentService
	def "can create a comment by calling createComment"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def user = newUser("user", "user")
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def workOrder = Initializer.newWorkOrder(equipment, "Nothing yet", Criticality.NORMAL,user, Initializer.now(),FailureReason.NOTSPECIFIED,OrderStatus.OPENATFOSA)
		when:
		commentService.createComment(workOrder,user,Initializer.now(),"Content")
		then:
		Comment.count() == 1
	}
}
