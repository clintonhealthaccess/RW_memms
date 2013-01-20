package org.chai.memms.preventive.maintenance

import org.chai.memms.IntegrationTests
import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.inventory.Equipment;
import org.chai.memms.preventive.maintenance.DurationBasedOrder.OccurencyType;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventionResponsible;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventiveOrderStatus;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventiveOrderType;
import org.chai.memms.security.User.UserType;
import org.chai.memms.util.Utils;
import org.chai.location.DataLocation

class DurationBasedOrderControllerSpec extends IntegrationTests {
	def durationBasedOrderController
	def "can create and save a durration based order"() {
		setup:
		setupLocationTree()
		setupEquipment()
		def addedBy = newUser("addedBy", CODE(123))
		def equipment = Equipment.findBySerialNumber(CODE(123))
		durationBasedOrderController = new DurationBasedOrderController()
		when:
		durationBasedOrderController.params.equipment = equipment
		durationBasedOrderController.params.addedBy = addedBy
		durationBasedOrderController.params.type = PreventiveOrderType.DURATIONBASED
		durationBasedOrderController.params.status = PreventiveOrderStatus.CLOSED
		durationBasedOrderController.params.description = "test"
		durationBasedOrderController.params.preventionResponsible = PreventionResponsible.SERVIDEPROVIDER
		durationBasedOrderController.params.firstOccurenceOn = Initializer.newTimeDate(Initializer.now(),"24:60:60")
		durationBasedOrderController.params.occurency = OccurencyType.DAILY
		durationBasedOrderController.save()
		then:
		DurationBasedOrder.count() == 1
	}
}
