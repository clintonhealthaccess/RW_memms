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
package org.chai.memms.reports.dashboard
import org.chai.memms.util.Utils;
import org.chai.memms.util.Utils.ReportType;
/**
 * @author Antoine Nzeyi, Donatien Masengesho, Pivot Access Ltd
 *
 */
class DashboardInitializer {

    //Indicator category codes
    public static final String CORRECTIVE_MAINTENANCE = ReportType.CORRECTIVE.reportType //"corrective"
    public static final String PREVENTIVE_MAINTENANCE = ReportType.PREVENTIVE.reportType //"preventive"
    public static final String MANAGEMENT_SPAREPARTS = ReportType.SPAREPARTS.reportType //"spareParts"
    public static final String MANAGEMENT_EQUIPMENT = ReportType.EQUIPMENT.reportType //"equipment"
    public static final String MONITORING_MEMMS_USE = ReportType.MEMMS.reportType //"monitoring"

    //Indicator computation scripts
    //Slid 7:Share of operational equipment
    public static final String SHARE_OPERATIONAL_SIMPLE_SLD7="select 1.0*count(equ.code)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='OPERATIONAL' and equ.dataLocation @DATA_LOCATION"
    public static final String SHARE_OPERATIONAL_GROUP_SLD7="select equ.type.names_en,1.0*count(equ.code)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='OPERATIONAL' and equ.dataLocation @DATA_LOCATION group by equ.type"
    //public static final String SHARE_OPERATIONAL_GROUP_SLD7="select "+Utils.buildSubQueryLanguages("equ.type.names")+",1.0*count(equ.code)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='OPERATIONAL' and equ.dataLocation @DATA_LOCATION group by equ.type"
   
    //Slide 12:Share of obsolete equipment
    public static final String SHARE_OBSOLETE_SIMPLE_SLD12="select 1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equ.obsolete=1 and equ.dataLocation @DATA_LOCATION"
    public static final String SHARE_OBSOLETE_GROUP_SLD12="select equ.type.names_en,1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equ.obsolete=1 and equ.dataLocation @DATA_LOCATION group by equ.type"
    //public static final String SHARE_OBSOLETE_GROUP_SLD12="select "+Utils.buildSubQueryLanguages("equ.type.names")+",1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equ.obsolete=1 and equ.dataLocation @DATA_LOCATION group by equ.type"
    
    //Slide 14:Share of equipment with lifetime expiring in less than 2 years
    public static final String SHARE_LIFETIME_LESS_SIMPLE_SLD14="select 1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and ((dateDiff(NOW(),equ.purchaseDate)/12)>(equ.expectedLifeTime-24)) and equ.dataLocation @DATA_LOCATION"
    public static final String SHARE_LIFETIME_LESS_GROUP_SLD14="select equ.type.names_en, 1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and ((dateDiff(NOW(),equ.purchaseDate)/12)>(equ.expectedLifeTime-24)) and equ.dataLocation @DATA_LOCATION group by equ.type"


    //Slie 15:Share of equipments for which a work order was generated=>rev ok
    public static final String SHARE_WORK_ORDER_GEN_SIMPLE_SLD15="select 1.0*count(equ.code)/(select count(eq.code) from Equipment eq where eq.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and eq.dataLocation @DATA_LOCATION)  from WorkOrder wOrder join wOrder.equipment equ where wOrder.equipment.id is not null and equ.dataLocation @DATA_LOCATION"
    public static final String SHARE_WORK_ORDER_GEN_GROUP_SLD15="select wOrder.equipment.type.names_en,1.0*count(wOrder.equipment.id)/(select count(eq.code) from Equipment eq where eq.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and eq.dataLocation @DATA_LOCATION)  from WorkOrder wOrder  where wOrder.equipment.id is not null and wOrder.equipment.dataLocation @DATA_LOCATION group by  wOrder.equipment.type"
    //public static final String SHARE_WORK_ORDER_GEN_GROUP_SLD15="select "+Utils.buildSubQueryLanguages("wOrder.equipment.type.names")+",1.0*count(wOrder.equipment.id)/(select count(eq.code) from Equipment eq where eq.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and eq.dataLocation @DATA_LOCATION)  from WorkOrder wOrder  where wOrder.equipment.id is not null and wOrder.equipment.dataLocation @DATA_LOCATION group by  wOrder.equipment.type"
    
    //Slie 16:Degree of corrective maintenance execution according to benchmark=rev ok
    public static final String DEGREE_CORRECTIVE_EX_SIMPLE_SLD16="select 1.0*count(wo1.id)/(select count(wo.id) from WorkOrderStatus wos join wos.workOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrderStatus wos1 join wos1.workOrder wo1 join wo1.equipment as equ1 where wo1.currentStatus='CLOSEDFIXED' and (dateDiff(NOW(),wo1.returnedOn) < #DEGGREE_CORRE_MAINT_EXECUTION or dateDiff(NOW(),wos1.dateCreated) < #DEGGREE_CORRE_MAINT_EXECUTION) and equ1.dataLocation @DATA_LOCATION"
    //time frame to be decided by the user
    public static final String DEGREE_CORRECTIVE_EX_GROUP_SLD16="select equ1.type.names_en as names,1.0*count(wo1.id)/(select count(wo.id) from WorkOrderStatus wos join wos.workOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) as final_result from WorkOrderStatus wos1 join wos1.workOrder wo1 join wo1.equipment as equ1 where wo1.currentStatus='CLOSEDFIXED' and (dateDiff(NOW(),wo1.returnedOn) < #DEGGREE_CORRE_MAINT_EXECUTION or dateDiff(NOW(),wos1.dateCreated) < #DEGGREE_CORRE_MAINT_EXECUTION) and equ1.dataLocation @DATA_LOCATION group by equ1.type"
    
    //Slide 19:Share of work orders escalated to MMC =>REV OK
    public static final String SHARE_ESCLATED_MMC_SIMPLE_SLD19="select 1.0*count(wo.id)/(select count(wo.id) from WorkOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrder wo join wo.equipment as equ where wo.currentStatus='OPENATMMC' and equ.dataLocation @DATA_LOCATION"
    public static final String SHARE_ESCLATED_MMC_GROUP_SLD19="select equ.type.names_en,1.0*count(wo.id)/(select count(wo.id) from WorkOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrder wo join wo.equipment as equ where wo.currentStatus='OPENATMMC' and equ.dataLocation @DATA_LOCATION group by equ.type"

    //Slide 20:Average time to fix equipments
    public static final String AVGE_FIX_SIMPLE_SLD20="select avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wo.open_on,wos.date_created) as fromOpenToClosed from memms_work_order wo ,memms_equipment equ,memms_work_order_status wos  where wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wos.status='OPENATFOSA' and equ.data_location_id @DATA_LOCATION) temp"
    public static final String AVGE_FIX_GROUP_SLD20="select temp.typeName,avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wo.open_on,wos.date_created) as fromOpenToClosed , ty.names_en as typeName,ty.id as typeId from memms_work_order wo ,memms_equipment equ,memms_equipment_type ty,memms_work_order_status wos  where equ.type_id=ty.id and wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wos.status='OPENATFOSA' and equ.data_location_id @DATA_LOCATION) temp group by temp.typeId"

    // //Slide 26:Degree of execution of preventive maintenance
    public static final String DEGREE_EXCUTION_PREV_SIMPLE_SLD26="select 1.0*count(prevention.id)/(select count(prwo.id) FROM PreventiveOrder prwo join prwo.equipment as equ1  where equ1.dataLocation @DATA_LOCATION) FROM Prevention prevention join prevention.order as pOrder join pOrder.equipment as equ where equ.dataLocation @DATA_LOCATION"
    public static final String DEGREE_EXCUTION_PREV_GROUP_SLD26="select equ.type.names_en,1.0*count(prevention.id)/(select count(prwo.id) FROM PreventiveOrder prwo join prwo.equipment as equ1  where equ1.dataLocation @DATA_LOCATION) FROM Prevention prevention  join prevention.order as pOrder join pOrder.equipment as equ where equ.dataLocation @DATA_LOCATION group by equ.type"
    //public static final String DEGREE_EXCUTION_PREV_GROUP_SLD26="select "+Utils.buildSubQueryLanguages("equ.type.names")+",1.0*count(prevention.id)/(select count(prwo.id) FROM PreventiveOrder prwo join prwo.equipment as equ1  where equ1.dataLocation @DATA_LOCATION) FROM Prevention prevention  join prevention.order as pOrder join pOrder.equipment as equ where equ.dataLocation @DATA_LOCATION group by equ.type"
    
    //Slie 30:Share of types of spare parts about to stock out in a given time period
    public static final String SHARE_TYPE_SP_PART_STOCK_OUT_SIMPLE_SLD30="select count(temp.spTypeTimount)/(SELECT count(mmm.id)  FROM memms_spare_part mmm where mmm.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.type_id)/(SELECT ROUND((count(m.id)/12),0)  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as spTypeTimount   FROM memms_spare_part mm where mm.data_location_id @DATA_LOCATION group by mm.type_id) temp where temp.spTypeTimount<#TRASHHOLD_MIN_STOCT_OUT"
    public static final String SHARE_TYPE_SP_PART_STOCK_OUT_GROUP_SLD30="select temp.descen,temp.spTypeTimount/(SELECT count(mmm.id)  FROM memms_spare_part mmm where mmm.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.type_id)/(SELECT ROUND(count(m.id)/12)  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as spTypeTimount,spt.names_en as descen   FROM memms_spare_part mm,memms_spare_part_type spt where mm.type_id=spt.id and mm.data_location_id @DATA_LOCATION group by mm.type_id) temp where temp.spTypeTimount<#TRASHHOLD_MIN_STOCT_OUT"
   
    //Slide 32:Average stock time
    public static final String AVG_STOCK_TIME_SIMPLE_SLD32="select sum(temp.usageeach)/(SELECT count(m.id)  FROM memms_spare_part m where m.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.id)/(SELECT ROUND((count(m.id)/12),0)  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as usageeach FROM memms_spare_part mm where mm.data_location_id @DATA_LOCATION group by mm.type_id) temp"
    public static final String AVG_STOCK_TIME_GROUP_SLD32="select temp.namesEn,temp.usageeach/(SELECT count(m.id)  FROM memms_spare_part m where m.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.id)/(SELECT count(m.id)/12  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as usageeach,spt.names_en as namesEn FROM memms_spare_part mm,memms_spare_part_type spt where mm.type_id=spt.id and mm.data_location_id @DATA_LOCATION group by mm.type_id) temp"

    public static createDashboardStructure() {
        createIndicatorCategories()
        createUserDefinedVariables()
        createIndicators()
    }

    public static createIndicators() {

        def equipementManagment = IndicatorCategory.findByCode(MANAGEMENT_EQUIPMENT)
        if(equipementManagment != null) {
            //Slide 7:Share of operational equipment
            if(!Indicator.findByCode("SHARE_OPE_EQUIPMENT"))
            newIndicator(
                equipementManagment,
                "SHARE_OPE_EQUIPMENT", 
                ["en":"Share of operational equipment","fr":"Share of operational equipment fr"],
                ["en":"Share of operational equipment","fr":"Share of operational equipment fr"],
                [
                    "en":"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})",
                    "fr":"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance}) fr"
                ],
                "%",0.8,0.9,Indicator.HistoricalPeriod.QUARTERLY,8, 
                SHARE_OPERATIONAL_SIMPLE_SLD7, 
                ["en":"Type of Equipment","fr":"Type d'equipment fr"],
                SHARE_OPERATIONAL_GROUP_SLD7,
                false,true
            )

            //Slide 14:Share of equipment with lifetime expiring in less than 2 years
            if(!Indicator.findByCode("SHARE_LIFETIME_EXPIRING_IN2Y_EQUIPEMENT"))
            newIndicator(
                equipementManagment, 
                "SHARE_LIFETIME_EXPIRING_IN2Y_EQUIPEMENT", 
                ["en":"Share of equipment with lifetime expiring in less than 2 years","fr":"Share of equipment with lifetime expiring in less than 2 years fr"],
                ["en":"Share of equipment with lifetime expiring in less than 2 years","fr":"Share of equipment with lifetime expiring in less than 2 years fr"], 
                [
                    "en":"(total number equipment with STATUS={“Operational”; “Partially operational”, “Under maintenance”} and {Current Date – date of first inventory updationor DATE OF PURCHASE} >{Expected life time – 2years)/", 
                    "fr":"(total number equipment with STATUS={“Operational”; “Partially operational”, “Under maintenance”} and {Current Date – date of first inventory updationor DATE OF PURCHASE} >{Expected life time – 2years)/ fr"
                ],
                "%", 0.30, 0.10, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                SHARE_LIFETIME_LESS_SIMPLE_SLD14, 
                ["en":"Type of Equipment", "fr":"Type of Equipment"], 
                SHARE_LIFETIME_LESS_GROUP_SLD14,
                false,true
                )
            //Slide 12:Share of obsolete equipment
            if(!Indicator.findByCode("SHARE_OBSOLETE_EQUIPMENT"))
            newIndicator(
                equipementManagment, 
                "SHARE_OBSOLETE_EQUIPMENT", 
                ["en":"Share of obsolete equipment","fr":"Share of obsolete equipment fr"],
                ["en":"Share of obsolete equipment","fr":"Share of obsolete equipment fr"],
                [
                    "en":"(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and OBSELETE BOX CHECKED)/(total number equipment with  STATUS = {Operational; Partially operational, Under maintenance})",
                    "fr":"(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and OBSELETE BOX CHECKED)/(total number equipment with  STATUS = {Operational; Partially operational, Under maintenance}) fr"
                ],
                "%",0.30,0.10,Indicator.HistoricalPeriod.MONTHLY,5, 
                SHARE_OBSOLETE_SIMPLE_SLD12, 
                ["en":"Type of Equipment","fr":"Type d'equipment fr"],
                SHARE_OBSOLETE_GROUP_SLD12,
                false,true
            )
        }
    
        def correctiveMaintenance = IndicatorCategory.findByCode(CORRECTIVE_MAINTENANCE)
        if(correctiveMaintenance != null) {
           //Slie 15:Share of equipments for which a work order was generated
           if(!Indicator.findByCode("SHARE_WORK_ORDER_CORR_MAINTENANCE"))
            newIndicator(
                correctiveMaintenance, 
                "SHARE_WORK_ORDER_CORR_MAINTENANCE", 
                ["en":"Share of equipments for which a work order was generated", "fr":"Share of equipments for which a work order was generated fr"],
                ["en":"Share of equipments for which a work order was generated", "fr":"Share of equipments for which a work order was generated fr"], 
                [
                    "en":"Total number of equipments for which work order was generated / total number of equipment with status ={ “Operational”, “Partially operational”,”Under maintenance”}", 
                    "fr":"Total number of equipments for which work order was generated / total number of equipment with status ={ “Operational”, “Partially operational”,”Under maintenance fr”}"
                ],
                "%", 0.15, 0.20, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                SHARE_WORK_ORDER_GEN_SIMPLE_SLD15, 
                ["en":"Type of Equipment", "fr":"Type of Equipment fr"], 
                SHARE_WORK_ORDER_GEN_GROUP_SLD15,
                false, true
            )
           // Slie 16:Degree of corrective maintenance execution according to benchmark - sql not compatible with in memory db
            if(!Indicator.findByCode("DEGREE_CORR_EXEC_BENCHNARK_MAINTENANCE"))
            newIndicator(
                correctiveMaintenance, 
                "DEGREE_CORR_EXEC_BENCHNARK_MAINTENANCE", 
                ["en":"Degree of corrective maintenance execution according to benchmark","fr":"Degree of corrective maintenance execution according to benchmark fr"],
                ["en":"Degree of corrective maintenance execution according to benchmark","fr":"Degree of corrective maintenance execution according to benchmark fr"], 
                [
                    "en":"Total no. of work orders with status changed from “open at facility” or “open at MM” to “Closed fixed”/ total no. of work orders generated in a given time frame (time frame to be decided by the user)", 
                    "fr":"Total no. of work orders with status changed from “open at facility” or “open at MM” to “Closed fixed”/ total no. of work orders generated in a given time frame (time frame to be decided by the user) fr"
                ],
                "%", 0.85, 0.95, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                DEGREE_CORRECTIVE_EX_SIMPLE_SLD16, 
                ["en":"Type of Equipment","fr":"Type of Equipment fr"], 
                DEGREE_CORRECTIVE_EX_GROUP_SLD16,
                false, true
            )

            //Slide 19:Share of work orders escalated to MMC
            if(!Indicator.findByCode("SHARE_ESCLATED_TO_MMC_MAINTENANCE"))
            newIndicator(
                correctiveMaintenance, 
                "SHARE_ESCLATED_TO_MMC_MAINTENANCE", 
                ["en":"Share of work orders escalated to MMC","fr":"Share of work orders escalated to MMC fr"],
                ["en":"Share of work orders escalated to MMC","fr":"Share of work orders escalated to MMC fr"], 
                [
                    "en":"Total work orders with status change from “open at facility” to :open at MMC” / total work orders", 
                    "fr":"Total work orders with status change from “open at facility” to :open at MMC” / total work orders fr"
                ],
                "%", 0.3, 0.1, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                SHARE_ESCLATED_MMC_SIMPLE_SLD19, 
                ["en":"Type of Equipment", "fr":"Type of Equipment fr"], 
                SHARE_ESCLATED_MMC_GROUP_SLD19,
                false,true
            )

            //Slide 20:Average time to fix equipments
            if(!Indicator.findByCode("AVERAGE_TIME_TO_FIX_EQUI_MAINTENAMCE"))
            newIndicator(
                correctiveMaintenance, 
                "AVERAGE_TIME_TO_FIX_EQUI_MAINTENAMCE", 
                ["en":"Average time to fix equipments","fr":"Average time to fix equipments fr"],
                ["en":"Average time to fix equipments","fr":"Average time to fix equipments fr"], 
                ["en":"Average time to fix equipments","fr":"Average time to fix equipments fr"],
                "day(s)", 3, 1, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                AVGE_FIX_SIMPLE_SLD20, 
                ["en":"Type of Equipment", "fr":"Type of Equipment"], 
                AVGE_FIX_GROUP_SLD20,
                true,true
            )
        }

        def preventiveMaintenance = IndicatorCategory.findByCode(PREVENTIVE_MAINTENANCE)
         if(preventiveMaintenance != null) {
            //Slide 26:Degree of execution of preventive maintenance
            if(!Indicator.findByCode("DEGREE_EXECUTION_PREV_MAINTENANCE"))
            newIndicator(
                preventiveMaintenance, 
                "DEGREE_EXECUTION_PREV_MAINTENANCE", 
                ["en":"Degree of execution of preventive maintenance","fr":"Degree of execution of preventive maintenance fr"],
                ["en":"Degree of execution of preventive maintenance","fr":"Degree of execution of preventive maintenance fr"], 
                [
                    "en":"Number of preventive maintenance deadlines met / total  number of preventive maintenance deadlines",
                    "fr":"Number of preventive maintenance deadlines met / total  number of preventive maintenance deadlines fr"
                ],
                "%",0.80,0.90, Indicator.HistoricalPeriod.QUARTERLY,8,
                DEGREE_EXCUTION_PREV_SIMPLE_SLD26,
                ["en":"Type of Equipment","fr":"Type of Equipment fr"],
                DEGREE_EXCUTION_PREV_GROUP_SLD26,
                false,true
            )
         }

         def sparePartsManagment = IndicatorCategory.findByCode(MANAGEMENT_SPAREPARTS)
         if(sparePartsManagment != null) {
            //Slie 30:Share of types of spare parts about to stock out in a given time period - sql not compatible with in memory db
            if(!Indicator.findByCode("SHARE_TYPES_SP_PRT_STOC_OUT_LESS"))
            newIndicator(
                sparePartsManagment, 
                "SHARE_TYPES_SP_PRT_STOC_OUT_LESS", 
                ["en":"Share of types of spare parts about to stock out in a given time period","fr":"Share of types of spare parts about to stock out in a given time period fr"],
                ["en":"Share of types of spare parts about to stock out in a given time period","fr":"Share of types of spare parts about to stock out in a given time period fr"], 
                [
                    "en":"No. Of spare part types at a facility with stock out time less than a certain threshold (to be defined by the administrator) / total number of spare part types at the facility", 
                    "fr":"No. Of spare part types at a facility with stock out time less than a certain threshold (to be defined by the administrator) / total number of spare part types at the facility fr"
                ],
                "%", 0.20, 0.10, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                SHARE_TYPE_SP_PART_STOCK_OUT_SIMPLE_SLD30, 
                ["en":"Spare Part", "fr":"Spare Part  fr"], 
                SHARE_TYPE_SP_PART_STOCK_OUT_GROUP_SLD30,
                true,true
            )

            //Slide 32:Average stock time
            if(!Indicator.findByCode("AVERAGE_STOCK_TIME_SPAREPART"))
            newIndicator(
                sparePartsManagment, 
                "AVERAGE_STOCK_TIME_SPAREPART", 
                ["en":"Average stock time","fr":"Average stock time fr"],
                ["en":"Average stock time","fr":"Average stock time fr"], 
                [
                    "en":"(Number of spare parts of type 1 x use rate of spare part of type 1+number of spare part of type 2 x use rate of spare part of type 2.....)  /  (Total number of spare parts of all types)", 
                    "fr":"(Number of spare parts of type 1 x use rate of spare part of type 1+number of spare part of type 2 x use rate of spare part of type 2.....)  /  (Total number of spare parts of all types) fr",
                ],
                "month(s)",6.0,2.0, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                AVG_STOCK_TIME_SIMPLE_SLD32, 
                ["en":"Spare Part Type", "fr":"Spare Part Type fr"], 
                AVG_STOCK_TIME_GROUP_SLD32,
                true,true
            )
        }
    }

    public static createIndicatorCategories() {
        if(!IndicatorCategory.findByCode(CORRECTIVE_MAINTENANCE))
            newIndicatorCategory(CORRECTIVE_MAINTENANCE, ["en":"Corrective Maintenance","fr":"Corrective Maintenance"], 0.6, 0.8)
        if(!IndicatorCategory.findByCode(PREVENTIVE_MAINTENANCE))
            newIndicatorCategory(PREVENTIVE_MAINTENANCE, ["en":"Preventive Maintenance","fr":"Preventive Maintenance"], 0.6, 0.8)
        if(!IndicatorCategory.findByCode(MANAGEMENT_EQUIPMENT))
            newIndicatorCategory(MANAGEMENT_EQUIPMENT, ["en":"Management of Medical Equipment","fr":"Management of Medical Equipment"], 0.6, 0.8)
        if(!IndicatorCategory.findByCode(MANAGEMENT_SPAREPARTS))
            newIndicatorCategory(MANAGEMENT_SPAREPARTS, ["en":"Management of Spare Parts","fr":"Management of Spare Parts"], 0.6, 0.8)
        if(!IndicatorCategory.findByCode(MONITORING_MEMMS_USE))
            newIndicatorCategory(MONITORING_MEMMS_USE, ["en":"Monitoring of MEMMS Use","fr":"Monitoring of MEMMS Use"], 0.6, 0.8)
    }

    public static createUserDefinedVariables() {
        if(!UserDefinedVariable.findByCode("WO_REINCIDENCE_DAYS"))
            newUserDefinedVariable("WO_REINCIDENCE_DAYS",["en":"Work order re-incidence period(days)","fr":"Work order re-incidence period(days) fr"],365.0)
        if(!UserDefinedVariable.findByCode("DEGGREE_CORRE_MAINT_EXECUTION"))
            newUserDefinedVariable("DEGGREE_CORRE_MAINT_EXECUTION",["en":"Degree of corrective maintenance execution time frame","fr":"Degree of corrective maintenance execution time frame fr"],365.0)
        if(!UserDefinedVariable.findByCode("TRASHHOLD_MIN_STOCT_OUT"))
            newUserDefinedVariable("TRASHHOLD_MIN_STOCT_OUT",["en":"Minimum Stock-Out time threshold(days)","fr":"Minimum Stock-Out time threshold(days) fr"],365.0)
        if(!UserDefinedVariable.findByCode("TRASHHOLD_MAX_STOCT_OUT"))
            newUserDefinedVariable("TRASHHOLD_MAX_STOCT_OUT",["en":"Maximum Stock-Out time threshold(days)","fr":"Maximum Stock-Out time threshold(days) fr"],30.0)
    }

    public static newGroupIndicatorValue(def generatedAt, def names, def value, def indicatorValue){
        def groupIndicatorValue = new GroupIndicatorValue(generatedAt:generatedAt,value:value)
         Utils.setLocaleValueInMap(groupIndicatorValue,names,'Names')
        indicatorValue.addToGroupIndicatorValues(groupIndicatorValue)
        indicatorValue.save(failOnError: true, flush:true)
        return groupIndicatorValue
    }

    public static newIndicatorValue(def computedAt, def locationReport, def indicator, def computedValue){
        def indicatorValue = new IndicatorValue(computedAt:computedAt, computedValue:computedValue)
        indicator.addToValues(indicatorValue)
        locationReport.addToIndicatorValues(indicatorValue)
        locationReport.save(failOnError: true, flush:true)
        return indicatorValue
    }
    

    public static newIndicatorCategory(def code, def names, def redToYellowThreshold, def yellowToGreenThreshold){
        def category = new IndicatorCategory(
            code:code,
            redToYellowThreshold:redToYellowThreshold,
            yellowToGreenThreshold:yellowToGreenThreshold
        )
        Utils.setLocaleValueInMap(category,names,"Names")
        return category.save(failOnError: true, flush:true)
    }

    public static newUserDefinedVariable(def code,def names, def currentValue){
        def userDefinedVariable = new UserDefinedVariable(
            code:code,
            currentValue:currentValue
        )
        Utils.setLocaleValueInMap(userDefinedVariable,names,"Names")
        return userDefinedVariable.save(failOnError: true, flush:true)
    }

    public static newMemmsReport(def eventDate){
        def memmsReport = new MemmsReport(eventDate:eventDate)
        return memmsReport.save(failOnError: true, flush:true)
    }

    public static newLocationReport(def memmsReport,def eventDate, def location){
        def locationReport = new LocationReport(eventDate:eventDate,location:location)
        memmsReport.addToLocationReports(locationReport)
        memmsReport.save(failOnError: true, flush:true)
        return locationReport
    }
           
    public static newIndicator(def category, def code, def names, def descriptions, def formulas, def unit, def redToYellowThreshold, def yellowToGreenThreshold, def historicalPeriod, def historyItems, def queryScript, def groupNames, def groupQueryScript, def sqlQuery, def active){
        def indicator = new Indicator(
            category:category, 
            code:code,
            unit:unit,
            redToYellowThreshold:redToYellowThreshold,
            yellowToGreenThreshold:yellowToGreenThreshold, 
            historicalPeriod:historicalPeriod, 
            historyItems:historyItems, 
            queryScript:queryScript, 
            groupQueryScript:groupQueryScript,
            sqlQuery:sqlQuery, 
            active:active
        )
        Utils.setLocaleValueInMap(indicator,names,"Names")
        Utils.setLocaleValueInMap(indicator,descriptions,"Descriptions")
        Utils.setLocaleValueInMap(indicator,formulas,"Formulas")
        Utils.setLocaleValueInMap(indicator,groupNames,"GroupNames")
        return indicator.save(failOnError: true, flush:true)
    }

}