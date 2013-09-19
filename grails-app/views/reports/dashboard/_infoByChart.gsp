<div>
  <ul class="v-tabs-nested">
    <g:if test="${indicatorItem.valuesPerGroup != null && indicatorItem.valuesPerGroup.size() > 0}">
      <g:each in="${indicatorItem.valuesPerGroup}" var="itemsMap">
        <g:set var="itemKey" value="${itemsMap.key}" />
        <g:set var="itemValue" value="${itemsMap.value}" />
        <li class="v-tabs-row">
          <span class="v-tabs-name">${itemKey}</span>
          <g:if test="${indicatorItem.unit=='%'}">
            <span class="v-tabs-value"><g:formatNumber number="${itemValue}" format="0.00%"/> </span>
          </g:if>
          <g:if test="${indicatorItem.unit!='%'}">
            <span class="v-tabs-value"><g:formatNumber number="${itemValue}" format="###,##0"/> ${indicatorItem.unit} </span>
          </g:if>
        </li>
      </g:each>
    </g:if>
    <g:else>
      <li class="v-tabs-row">
        <span class="v-tabs-name">
          <g:message code="entity.list.empty.label" args="[indicatorItem.groupNames]"/>
        </span>
        <span class="v-tabs-value"></span>
      </li>
    </g:else>
  </ul>
</div>