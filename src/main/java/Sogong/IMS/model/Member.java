package Sogong.IMS.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class Member {
	private String memberID;
	private String memberPW;
	private String name;
	private String phoneNumber;
	private String address;
	private String email;
	private String memberType;
	private String department;
	private List<MemberAuthorityGroup> memberAuthorityGroups;
}
