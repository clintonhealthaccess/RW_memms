package org.chai.memms.maintenance

import java.util.Date;
import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.WorkOrder.Criticality;
import org.chai.memms.maintenance.WorkOrder.OrderStatus;
import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;

class NotificationControllerSpec  extends IntegrationTests{
	
	def notificationController
	def notificationService
	
	def "can save a notification"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def sender = newUser("sender", true,true)
		sender.userType = UserType.DATACLERK
		sender.location = DataLocation.findByCode(KIVUYE)
		sender.save(failOnError:true)
		
		def receiverOne = newUser("receiverOne", true,true)
		receiverOne.userType = UserType.TECHNICIANFACILITY
		receiverOne.location = DataLocation.findByCode(KIVUYE)
		receiverOne.save(failOnError:true)

		def workOrder = new WorkOrder(equipment:Equipment.findBySerialNumber(CODE(123)),description: "test work order",criticality:Criticality.NORMAL,status:OrderStatus.OPEN,
				addedBy:sender,openOn: new Date(),assistaceRequested:false).save(flush:true,failOnError:true)
		notificationController = new NotificationController()
		setupSecurityManager(sender)
		when:
		notificationController.params.content = " check this out"
		notificationController.params.workOrder = workOrder
		notificationController.save()
		then:
		notificationController.response.redirectUrl == "/notification/list/${workOrder.id}"
		Notification.count() == 1
	}
	
	def "can't save a notification without a work order"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def sender = newUser("sender", true,true)
		sender.userType = UserType.DATACLERK
		sender.location = DataLocation.findByCode(KIVUYE)
		sender.save(failOnError:true)
		
		def receiverOne = newUser("receiverOne", true,true)
		receiverOne.userType = UserType.TECHNICIANFACILITY
		receiverOne.location = DataLocation.findByCode(KIVUYE)
		receiverOne.save(failOnError:true)

		notificationController = new NotificationController()
		setupSecurityManager(sender)
		when:
		notificationController.params.content = " check this out"
		notificationController.save()
		then:
		notificationController.modelAndView.viewName == "/entity/edit"
		Notification.count() == 0
	}
	
	def "reading a notification sets it's read status to read"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def senderOne = newUser("senderOne", true,true)
		senderOne.userType = UserType.DATACLERK
		senderOne.location = DataLocation.findByCode(KIVUYE)
		senderOne.save(failOnError:true)
		
		def senderTwo = newUser("senderTwo", true,true)
		senderTwo.userType = UserType.DATACLERK
		senderTwo.location = DataLocation.findByCode(KIVUYE)
		senderTwo.save(failOnError:true)
		
		def receiverFacility = newUser("receiverFacility", true,true)
		receiverFacility.userType = UserType.TECHNICIANFACILITY
		receiverFacility.location = DataLocation.findByCode(KIVUYE)
		receiverFacility.save(failOnError:true)
		
		def receiverMoH = newUser("receiverMoH", true,true)
		receiverMoH.userType = UserType.TECHNICIANMOH
		receiverMoH.location = Location.findByCode(RWANDA)
		receiverMoH.save(failOnError:true)
		
		
		def workOrderOne = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderOne, new Date())
		def workOrderTwo = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderTwo, new Date())
		
		notificationService.newNotification(workOrderOne, "Send for rapair, one",senderOne)
		notificationService.newNotification(workOrderOne, "Send for rapair, higher",receiverFacility)
		notificationService.newNotification(workOrderTwo, "Send for rapair, two",senderTwo)
		setupSecurityManager(receiverFacility)
		def notification = notificationService.searchNotificition("one",receiverFacility,workOrderOne,null,[:])[0]
		notificationController = new NotificationController()
		expect:
		notification.read == false
		when://Get only those that are unread
		notificationController.params.id = notification.id
		notificationController.read()
		
		then:
		notification.read == true
	}
	
	def "can list notification - from a work order"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def senderOne = newUser("senderOne", true,true)
		senderOne.userType = UserType.DATACLERK
		senderOne.location = DataLocation.findByCode(KIVUYE)
		senderOne.save(failOnError:true)
		
		def senderTwo = newUser("senderTwo", true,true)
		senderTwo.userType = UserType.DATACLERK
		senderTwo.location = DataLocation.findByCode(KIVUYE)
		senderTwo.save(failOnError:true)
		
		def receiverFacility = newUser("receiverFacility", true,true)
		receiverFacility.userType = UserType.TECHNICIANFACILITY
		receiverFacility.location = DataLocation.findByCode(KIVUYE)
		receiverFacility.save(failOnError:true)
		
		def receiverMoH = newUser("receiverMoH", true,true)
		receiverMoH.userType = UserType.TECHNICIANMOH
		receiverMoH.location = Location.findByCode(RWANDA)
		receiverMoH.save(failOnError:true)
		
		
		def workOrderOne = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderOne, new Date())
		def workOrderTwo = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderTwo, new Date())
		
		notificationService.newNotification(workOrderOne, "Send for rapair, one",senderOne)
		notificationService.newNotification(workOrderOne, "Send for rapair, higher",receiverFacility)
		notificationService.newNotification(workOrderTwo, "Send for rapair, two",senderTwo)
		setupSecurityManager(receiverFacility)
		def notifications = notificationService.searchNotificition("one",receiverFacility,workOrderOne,null,[:])
		notificationService.readNotification(notifications[0].id)
		notificationController = new NotificationController()
		
		when://Get only those that are unread
		notificationController.params.read = "false"
		notificationController.params.id = workOrderTwo.id
		notificationController.list()
		
		then:
		notificationController.modelAndView.model.entities.size() == 1
		Notification.count() == 4
	}
	
	def "can list notification - all of them for the current user"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def senderOne = newUser("senderOne", true,true)
		senderOne.userType = UserType.DATACLERK
		senderOne.location = DataLocation.findByCode(KIVUYE)
		senderOne.save(failOnError:true)
		
		def senderTwo = newUser("senderTwo", true,true)
		senderTwo.userType = UserType.DATACLERK
		senderTwo.location = DataLocation.findByCode(KIVUYE)
		senderTwo.save(failOnError:true)
		
		def receiverFacility = newUser("receiverFacility", true,true)
		receiverFacility.userType = UserType.TECHNICIANFACILITY
		receiverFacility.location = DataLocation.findByCode(KIVUYE)
		receiverFacility.save(failOnError:true)
		
		def receiverMoH = newUser("receiverMoH", true,true)
		receiverMoH.userType = UserType.TECHNICIANMOH
		receiverMoH.location = Location.findByCode(RWANDA)
		receiverMoH.save(failOnError:true)
		
		
		def workOrderOne = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderOne, new Date())
		def workOrderTwo = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderTwo, new Date())
		
		notificationService.newNotification(workOrderOne, "Send for rapair, one",senderOne)
		notificationService.newNotification(workOrderOne, "Send for rapair, higher",receiverFacility)
		notificationService.newNotification(workOrderTwo, "Send for rapair, two",senderTwo)
		setupSecurityManager(receiverFacility)
		def notifications = notificationService.searchNotificition("one",receiverFacility,workOrderOne,null,[:])
		notificationService.readNotification(notifications[0].id)
		notificationController = new NotificationController()
		
		when://list all for a given user
		notificationController = new NotificationController()
		notificationController.list()
		
		then:
		notificationController.modelAndView.model.entities.size() == 2
		Notification.count() == 4
	}
	
	def "can filter notifications"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def senderOne = newUser("senderOne", true,true)
		senderOne.userType = UserType.DATACLERK
		senderOne.location = DataLocation.findByCode(KIVUYE)
		senderOne.save(failOnError:true)
		
		def senderTwo = newUser("senderTwo", true,true)
		senderTwo.userType = UserType.DATACLERK
		senderTwo.location = DataLocation.findByCode(KIVUYE)
		senderTwo.save(failOnError:true)
		
		def receiverFacility = newUser("receiverFacility", true,true)
		receiverFacility.userType = UserType.TECHNICIANFACILITY
		receiverFacility.location = DataLocation.findByCode(KIVUYE)
		receiverFacility.save(failOnError:true)
		
		def receiverMoH = newUser("receiverMoH", true,true)
		receiverMoH.userType = UserType.TECHNICIANMOH
		receiverMoH.location = Location.findByCode(RWANDA)
		receiverMoH.save(failOnError:true)
		
		
		def workOrderOne = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderOne, new Date())
		def workOrderTwo = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderTwo, new Date())
		
		notificationService.newNotification(workOrderOne, "Send for rapair, one",senderOne)
		notificationService.newNotification(workOrderOne, "Send for rapair, higher",receiverFacility)
		notificationService.newNotification(workOrderTwo, "Send for rapair, two",senderTwo)
		setupSecurityManager(receiverFacility)
		notificationController = new NotificationController()
		
		when://Get only those that are unread
		notificationController.params.read = "false"
		notificationController.params.to = new Date()
		notificationController.filter()
		
		then:
		notificationController.modelAndView.model.entities.size() == 2
	}
	
	def "can search notifications"(){
		setup:
		setupLocationTree()
		setupEquipment()
		def senderOne = newUser("senderOne", true,true)
		senderOne.userType = UserType.DATACLERK
		senderOne.location = DataLocation.findByCode(KIVUYE)
		senderOne.save(failOnError:true)
		
		def senderTwo = newUser("senderTwo", true,true)
		senderTwo.userType = UserType.DATACLERK
		senderTwo.location = DataLocation.findByCode(KIVUYE)
		senderTwo.save(failOnError:true)
		
		def receiverFacility = newUser("receiverFacility", true,true)
		receiverFacility.userType = UserType.TECHNICIANFACILITY
		receiverFacility.location = DataLocation.findByCode(KIVUYE)
		receiverFacility.save(failOnError:true)
		
		def receiverMoH = newUser("receiverMoH", true,true)
		receiverMoH.userType = UserType.TECHNICIANMOH
		receiverMoH.location = Location.findByCode(RWANDA)
		receiverMoH.save(failOnError:true)
		
		
		def workOrderOne = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderOne, new Date())
		def workOrderTwo = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, senderTwo, new Date())
		
		notificationService.newNotification(workOrderOne, "Send for rapair, one",senderOne)
		notificationService.newNotification(workOrderOne, "Send for rapair, higher",receiverFacility)
		notificationService.newNotification(workOrderTwo, "Send for rapair, two",senderTwo)
		setupSecurityManager(receiverFacility)
		notificationController = new NotificationController()
		
		when://Get only those that are unread
		notificationController.params.q = "one"
		notificationController.search()
		
		then:
		notificationController.modelAndView.model.entities.size() == 1
	}
}