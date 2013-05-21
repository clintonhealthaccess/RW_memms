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

import java.util.Date;

import groovy.transform.EqualsAndHashCode;

import org.chai.location.DataLocation;
import org.chai.memms.inventory.Provider;
import org.chai.memms.security.User;
import org.chai.memms.spare.part.SparePartType;
import i18nfields.I18nFields;

/**
 * @author Jean Kahigiso M.
 *
 */
@i18nfields.I18nFields
public class SparePart {
	
	enum StockLocation{

		NONE('none'),
		MMC("mmc"),
		FACILITY("facility")
		
		String messageCode = "stock.location"
		final String name
		StockLocation(String name){ this.name=name }
		String getKey() { return name() }
	}
	enum SparePartPurchasedBy{
		
		NONE('none'),
		BYMOH("by.moh"),
		BYMMC("by.mmc"),
		BYFACILITY("by.facility"),
		BYPARTNER("by.partner")
		
		String messageCode = "spare.part.purchased"

		final String name
		SparePartPurchasedBy(String name){ this.name=name }
		String getKey() { return name() }
		
	}
	enum SparePartStatus{
		
		NONE('none'),
		INSTOCK("in.stock"),
		PENDINGORDER("pending.order")

		String messageCode = "spare.part.status"

		final String name
		SparePartStatus(String name){ this.name=name }
		String getKey() { return name() }
		
	}
	
	String descriptions
	String currency
	String room
	String shelf

	Date purchaseDate
	Date dateCreated
	Date lastUpdated

	Double purchaseCost
	Integer initialQuantity
	Integer inStockQuantity = 0

	Provider supplier
	DataLocation dataLocation

	SparePartPurchasedBy sparePartPurchasedBy
	StockLocation stockLocation
	SparePartStatus status
	
	
	User addedBy
	User lastModified

	
	static belongsTo = [type: SparePartType]

	static i18nFields = ["descriptions"]
	
	static constraints = {

		descriptions nullable: true, blank: true
		type nullable: false
		room nullable: true, blank: true
		shelf nullable: true, blank: true

		initialQuantity nullable: false, minSize:1
		inStockQuantity nullable: false, minSize:0, validator:{ val, obj ->
			if(obj.status.equals(SparePartStatus.PENDINGORDER)) return val==0
		}

		purchaseDate nullable: true, validator:{it <= new Date()}

		currency  nullable: true, blank: true, validator:{ val, obj ->
			if(obj.purchaseCost != null) return (val != null && val in ["RWF","USD","EUR"])
		}
		purchaseCost nullable: true, validator: {val, obj ->
			if(obj.currency != null) return val != null
		}

		sparePartPurchasedBy nullable: false, inList:[SparePartPurchasedBy.BYFACILITY,SparePartPurchasedBy.BYMOH, SparePartPurchasedBy.BYMMC, SparePartPurchasedBy.BYPARTNER]

		status nullable: false, inList:[SparePartStatus.INSTOCK,SparePartStatus.PENDINGORDER]

		stockLocation  nullable: false,inList:[StockLocation.MMC, StockLocation.FACILITY]
		
		dataLocation nullable: true, validator: {val, obj ->
			if(obj.stockLocation.equals(StockLocation.FACILITY)) return val != null
			else return val == null
		}
		
		lastModified nullable:true, validator:{ val, obj ->
			if (val != null) return (obj.lastUpdated != null)
		}	
		supplier nullable: true
	}

	def getUsedQuantity () {
		if(status.equals(SparePartStatus.INSTOCK))
			return initialQuantity - inStockQuantity
	}
	//True if this spare part group is no longer inStock
	def getIsEmptyStock(){
		if(status.equals(SparePartStatus.INSTOCK) && inStockQuantity==0)
			return true
		else return false
	}
	
	static mapping = {
		table "memms_spare_part"
		version false
		cache true
	}
	
	
	String toString() {
		return "SparePart [id= " + id + " type= "+type+"]";

	}
}
