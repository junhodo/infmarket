package Sogong.IMS.controller.AuthorityManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.AuthorityGroup;
import Sogong.IMS.model.Member;
import Sogong.IMS.model.MemberAuthorityGroup;

public class AuthorityModifyAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();

            String memberID = request.getParameter("inputMemberID");
            String[] authoritGroupIDs = request.getParameterValues("inputAuthorities");

            if (memberID != null & authoritGroupIDs != null) {

                ArrayList<MemberAuthorityGroup> memberAuthorityGroups = new ArrayList<>();
            
                for(String authorityGroupID : authoritGroupIDs){
                    memberAuthorityGroups.add(MemberAuthorityGroup.builder().authorityGroup(
                                            AuthorityGroup.builder().authorityGroupID(Integer.parseInt(authorityGroupID)).build())
                                            .build());
                }

                Member member = Member.builder().memberID(memberID)
                                                .memberAuthorityGroups(memberAuthorityGroups)
                                                .build();
                
                Boolean isOK = new MemberAuthorityGroupDAO().modify(member);

                if (isOK) {
                    out.println("<script>alert('성공적으로 수정되었습니다.')</script>");
                    out.println("<script>self.close()</script>");
                }

                out.flush();
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}