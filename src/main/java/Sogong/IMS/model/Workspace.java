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

public class Workspace {
    private String workspaceID;
    private String workspaceName;
    private String registrantID;       
}