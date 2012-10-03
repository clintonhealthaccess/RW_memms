package org.chai.memms.equipment

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.EquipmentStatus.Status;


class EquipmentStatusServiceSpec extends IntegrationTests{
	
  def equipmentStatusService
  def "can create and save an equipment status"(){
		setup:
		setupLocationTree()
		setupEquipment()
		setupSystemUser()
		when:
		def user  = newUser("admin", "Admin UID")
		def equipment = Equipment.findBySerialNumber(CODE(123))
		def status = equipmentStatusService.createEquipmentStatus(Initializer.now(),user,Status.OPERATIONAL,equipment,true,Initializer.now(),[:])
		status.save(flush:true)
		then:
		EquipmentStatus.count()==1
	}
  
  def "search equipmentStatus by equipment"(){
	  setup:
	  setupLocationTree()
	  setupEquipment()
	  setupSystemUser()
	  def user  = newUser("admin", "Admin UID")
	  def equipment = Equipment.findBySerialNumber(CODE(123))
	  def status = equipmentStatusService.createEquipmentStatus(Initializer.now(),user,Status.OPERATIONAL,equipment,true,Initializer.now(),[:])
	  status.save(flush:true)
	  when:
	  def equipments = equipmentStatusService.getEquipmentStatusByEquipment(equipment,[:])
	  then:
	  EquipmentStatus.count()==1
	  equipments.equals([equipment.status])
	  
  }
}
