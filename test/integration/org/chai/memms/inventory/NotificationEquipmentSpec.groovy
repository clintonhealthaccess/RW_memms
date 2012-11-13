package org.chai.memms.inventory

import java.util.Date;

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.corrective.maintenance.NotificationWorkOrder;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.corrective.maintenance.WorkOrder.Criticality;
import org.chai.memms.corrective.maintenance.WorkOrder.FailureReason;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus;
import org.chai.memms.security.User;
import org.chai.location.DataLocation;
import org.chai.location.Location;

class NotificationEquipmentSpec  extends IntegrationTests{
	def "can create a notification"(){
		setup:
		setupLocationTree()
		def sender = newOtherUser("sender", "sender", DataLocation.findByCode(KIVUYE))
		def receiver = newOtherUser("receiver", "receiver", DataLocation.findByCode(BUTARO))
		
		when:
		new NotificationEquipment(sender:sender, receiver:receiver, writtenOn: Initializer.now(), content:"I have a new equipment in my health center.",read:true,dataLocation:sender.location).save(failOnError:true)
		then:
		NotificationEquipment.count() == 1
	}

	def "all required fields needed on notification"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def sender = newUser("user", "user")
		def receiver = newUser("receiver", "receiver")
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def workOrder = Initializer.newWorkOrder(equipment, "Nothing yet", Criticality.NORMAL,sender, Initializer.now(),FailureReason.NOTSPECIFIED,OrderStatus.OPENATFOSA)
		def notificationWithErrors = new NotificationWorkOrder()
		def expectedFieldErrors = ["sender","receiver","writtenOn","content","workOrder"]

		when://All required fields in
		notificationWithErrors.save()
		then:
		notificationWithErrors.errors.fieldErrorCount == 5
		expectedFieldErrors.each{notificationWithErrors.errors.hasFieldErrors(it)}
		NotificationWorkOrder.count() == 0

		when://notification date should be before or equal to  today
		notificationWithErrors = new NotificationWorkOrder(sender:sender, receiver:receiver, writtenOn: Initializer.now()+1, content:" check this out",read:true,workOrder:workOrder)
		notificationWithErrors.save()
		then:
		notificationWithErrors.errors.hasFieldErrors("writtenOn") == true
		NotificationWorkOrder.count() == 0
	}
}
