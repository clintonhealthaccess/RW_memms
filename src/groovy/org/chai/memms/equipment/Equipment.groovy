/**
 * 
 */
package org.chai.memms.equipment

/**
 * @author Jean Kahigiso M.
 *
 */
public abstract class Equipment {
	
	DataLocation dataLocation
	String itemCode;
	Date manufactureDate
	Date purchaseDate
	EquipmentWarranty warranty
	String serialNumber
	Department department
	String purchaseCost
	String observation 
	EquipmentManufacture manufacture
	EquipmentSupplier supplier
	
	
	enum EquipmentStatus{
		
		INSTOCK("inStock"),
		OPERATIONAL("operational"),
		UNDERMAINTENANCE("underMaintenance"),
		FORDISPOSAL("forDispodal"),
		DISPOSED("disposed")		
	}
	
	static constraints = {
		
	}
	 

}
