<a href='${createLinkWithTargetURI(controller:'equipment', action:'edit', params:[id: workOrder.equipment.id])}'> Edit </a>
<ul>
<li>Facility:${workOrder.equipment.dataLocation.names_en}<li>
<li>Department:${workOrder.equipment.department.names_en}<li>
<li>Room:${workOrder.equipment.room}<li>
<li>Status:${workOrder.equipment.getCurrentState()?.status}<li>
</ul>