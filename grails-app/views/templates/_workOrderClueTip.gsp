Facility: <span>${workOrder.equipment.dataLocation.names_en}</span><br/>
Department: <span>${workOrder.equipment.department.names_en}</span> <br/>
Room: <span>${workOrder.equipment.room}</span> <br/>
Status: <span>${workOrder.equipment.getCurrentState()?.status}</span> <br/>
<a href='${createLinkWithTargetURI(controller:'equipment', action:'edit', params:[id: workOrder.equipment.id])}'> Edit </a>