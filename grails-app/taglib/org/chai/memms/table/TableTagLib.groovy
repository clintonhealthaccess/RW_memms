package org.chai.memms.table

class TableTagLib {

	def table = {attrs, body -> 
		def table = attrs['table']
		def nullText = attrs['nullText']
		
		out << render(template: '/tags/table/table', model:[table: table, nullText: nullText])
	}
	
	
}
