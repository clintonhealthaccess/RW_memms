package org.chai.memms.maintenance

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.security.User;

class NotificationServiceSpec  extends IntegrationTests{
	def notificationService
	def "can send notifications"() {
		setup:
		Initializer.createDummyStructure()
		Initializer.createUsers()
		Initializer.createInventoryStructure()
		Initializer.createCorrectiveMaintenanceStructure()
		when:
		notificationService.newNotification(WorkOrder.findByDescription("First order"), "Send for rapair",User.findByUsername("user"))
		then:
		Notification.count() == 1
		WorkOrder.findByDescription("First order").notificationGroup.size() == 2
	}
	
	def "can escalate notifications"() {
		setup:
		Initializer.createDummyStructure()
		Initializer.createUsers()
		Initializer.createInventoryStructure()
		Initializer.createCorrectiveMaintenanceStructure()
		when:
		notificationService.newNotification(WorkOrder.findByDescription("First order"), "Send for rapair",User.findByUsername("user"))
		notificationService.newNotification(WorkOrder.findByDescription("First order"), "Send for rapair, higher",User.findByUsername("techf"))
		then:
		Notification.count() == 3
		WorkOrder.findByDescription("First order").notificationGroup.size() == 3
	}
}
