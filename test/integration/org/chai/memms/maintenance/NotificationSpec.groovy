package org.chai.memms.maintenance

import java.util.Date;

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.WorkOrder.Criticality;
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
				addedBy:sender,openOn: new Date(),assistaceRequested:false).save(failOnError:true)

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
				addedBy:sender,openOn: new Date(),assistaceRequested:false).save(failOnError:true)

		//TODO find a way to check for the errors on specific fields
		when://All required fields in
		new Notification(sender:null, receiver:receiver, writtenOn: new Date(), content:" check this out",read:true,workOrder:workOrder).save()
		new Notification(sender:sender, receiver:null, writtenOn: new Date(), content:" check this out",read:true,workOrder:workOrder).save()
		new Notification(sender:sender, receiver:receiver, writtenOn: null, content:" check this out",read:true,workOrder:workOrder).save()
		new Notification(sender:sender, receiver:receiver, writtenOn: new Date(), content:null,read:true,workOrder:workOrder).save()
		new Notification(sender:sender, receiver:receiver, writtenOn: new Date(), content:" check this out",read:null,workOrder:workOrder).save()
		new Notification(sender:sender, receiver:receiver, writtenOn: new Date(), content:" check this out",read:true,workOrder:null).save()
		then:
		Notification.count() == 0
		
		when://notification date should be before today
		new Notification(sender:null, receiver:receiver, writtenOn: new Date().next(), content:" check this out",read:true,workOrder:workOrder).save()
		then:
		Notification.count() == 0
	}
}
