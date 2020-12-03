package Sogong.IMS.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Facility {

    private String facilityID;
    private String registrantID;
    private String workspaceID;
    private String facilityName;

    public Facility(String facilityID, String registrantID, String workspaceID, String facilityName){
        this.facilityID = facilityID;
        this.registrantID = registrantID;
        this.workspaceID = workspaceID;
        this.facilityName = facilityName;
    }
}