package Sogong.IMS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WorkspaceProperty {
    private String corporateRegistrationNumber;
    private String representativeName;
    private String workspaceAddress;
    private String phoneNum;
    private String typeOfBusiness;
    private int workspaceMemberCount;
    private String workspaceStatus;
    private String registrantID;
    private String workspaceID;
    private String workspaceName;
}