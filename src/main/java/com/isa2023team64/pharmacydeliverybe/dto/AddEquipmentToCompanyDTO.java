package com.isa2023team64.pharmacydeliverybe.dto;

public class AddEquipmentToCompanyDTO {
    private Integer companyId;
    private Integer equipmentId;

    public AddEquipmentToCompanyDTO() {
        super();
    }

    public AddEquipmentToCompanyDTO(Integer companyId, Integer equipmentId) {
        this.companyId = companyId;
        this.equipmentId = equipmentId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
    
}
