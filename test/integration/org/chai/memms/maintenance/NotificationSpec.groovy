package org.chai.memms.maintenance

import java.util.Date;

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.WorkOrder.Criticality;
import org.chai.memms.maintenance.WorkOrder.FailureReason;
import org.chai.memms.maintenance.WorkOrder.OrderStatus;
import org.chai.memms.security.User;

class NotificationSpec  extends IntegrationTests{
	def "can create a notification"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def sender = newUser("user", "user")
		def receiver = newUser("receiver", "receiver")

		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:sender,openOn: new Date(),assistaceRequested:false,failureReason:FailureReason.NOTSPECIFIED).save(failOnError:true)

		when:
		new Notification(sender:sender, receiver:receiver, writtenOn: new Date(), content:" check this out",read:true,workOrder:workOrder).save(failOnError:true)
		then:
		Notification.count() == 1
	}

	def "all required fields needed on notification"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def sender = newUser("user", "user")
		def receiver = newUser("receiver", "receiver")

		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:sender,openOn: new Date(),assistaceRequested:false,failureReason:FailureReason.NOTSPECIFIED).save(failOnError:true)
		def notificationWithErrors = new Notification()
		def expectedFieldErrors = ["sender","receiver","writtenOn","content","read","workOrder"]

		when://All required fields in
		notificationWithErrors.save()
		then:
		notificationWithErrors.errors.fieldErrorCount == 6
		expectedFieldErrors.each{notificationWithErrors.errors.hasFieldErrors(it)}
		Notification.count() == 0

		when://notification date should be before or equal to  today
		notificationWithErrors = new Notification(sender:sender, receiver:receiver, writtenOn: new Date().next(), content:" check this out",read:true,workOrder:workOrder)
		notificationWithErrors.save()
		then:
		notificationWithErrors.errors.hasFieldErrors("writtenOn") == true
		Notification.count() == 0
	}
}
