package Sogong.IMS.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Device {
    String deviceID;
    String facilityPropertyID;
    String registrantID;
    String deviceName;
    String instruction;
    int instructionCost;

    public Device(String deviceID,String facilityPropertyID , String registrantID, String deviceName, String instruction, int instructionCost){
        this.facilityPropertyID = facilityPropertyID;
        this.deviceID = deviceID;
        this.registrantID = registrantID;
        this.deviceName = deviceName;
        this.instruction = instruction;
        this.instructionCost = instructionCost;
    }
}