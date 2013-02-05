/**
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.chai.memms.spare.part

import org.chai.memms.IntegrationTests;
import org.chai.memms.spare.part.SparePartController;

/**
 * @author Aphrodice Rwagaju
 *
 */
class SparePartControllerSpec extends IntegrationTests{
	def sparePartController
	
		def "create sparePart with correct required data in fields - for english input"(){
	
			/*setup:
			setupLocationTree()
			setupSystemUser()
	
			def sparePartType = Initializer.newSparePartType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now())
	
			def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
			def supplierContact = Initializer.newContact(['en':'Address Descriptions '],"Supplier","jk@yahoo.com","0768-888-787","Street 1654","6353")
	
			def manufacture = Initializer.newProvider(CODE(123), Type.MANUFACTURER,manufactureContact)
			def supplier = Initializer.newProvider(CODE(124), Type.SUPPLIER,supplierContact)
	
	
			sparePartController = new SparePartController();
			when:
			sparePartController.params.serialNumber = 'SERIAL12129'
			sparePartController.params.purchaseCost = "32000"
			sparePartController.params.currency = "USD"
			sparePartController.params.model = "model one"
			sparePartController.params.room = "ROOM A1"
			sparePartController.params.purchaser = SparePartPurchasedBy.BYFACILITY
			sparePartController.params.obsolete = false
			sparePartController.params.descriptions_en = "test_english_descriptions"
			sparePartController.params.manufactureDate = Initializer.getDate(1,1,2012)
			sparePartController.params.purchaseDate = Initializer.getDate(2,1,2012)
			sparePartController.params.department = department
			sparePartController.params.type = sparePartType
	
			sparePartController.params."warranty.startDate" = Initializer.getDate(10,1,2012)
			sparePartController.params."warranty.sameAsSupplier" = true
			sparePartController.params."warranty.descriptions_en" = "new warranty for testing"
			sparePartController.params.warrantyPeriod = "struct"
			sparePartController.params.warrantyPeriod_years = "1"
			sparePartController.params.warrantyPeriod_months = "4"
	
			sparePartController.params."manufacturer.id" = manufacture.id
			sparePartController.params."supplier.id" = supplier.id
			sparePartController.params.expectedLifeTime = "struct"
			sparePartController.params.expectedLifeTime_years = "1"
			sparePartController.params.expectedLifeTime_months = "3"
			sparePartController.params.dataLocation = DataLocation.list().first()
			sparePartController.params.status="INSTOCK"
			sparePartController.params.dateOfEvent=Initializer.now()
			sparePartController.save()
	
			then:
			SparePart.count() == 1;
			SparePart.findBySerialNumber("SERIAL12129").serialNumber.equals("SERIAL12129")
			SparePart.findBySerialNumber("SERIAL12129").expectedLifeTime.numberOfMonths == 15
			SparePart.findBySerialNumber("SERIAL12129").warrantyPeriod.numberOfMonths == 16
			SparePart.findBySerialNumber("SERIAL12129").serviceContractPeriod.numberOfMonths == 3
			SparePart.findByDescriptions_en("test_english_descriptions").getDescriptions(new Locale("en")).equals("test_english_descriptions")*/
		}

}
