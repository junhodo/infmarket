package infMarket.sevelets.member;

import infMarket.models.mybatis.dao.Member.MemberMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            response.setContentType("text/html;charset=UTF-8");
            Member member = parseRequestToMember(request);
            PrintWriter out = response.getWriter();
            MemberMapperExecutor memberMapperExecutor = new MemberMapperExecutor();
            if(memberMapperExecutor.insertMember(member)){
                request.getSession().setAttribute(MEMBER_SESSION_ID,member);
                out.println("<script>alert('환영합니다!'); location.href='/';</script>");
            }else{
                out.println("<script>alert('회원 가입에 실패했습니다, 입력을 확인해주세요'); location.href='/register';</script>");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private Member parseRequestToMember(HttpServletRequest request) {
        try{
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String birth_date = request.getParameter("birth_date");
            String phone_number = request.getParameter("phone_number");
            String email = request.getParameter("email");
            String member_id = request.getParameter("member_id");
            String password = request.getParameter("password");
            System.out.println(member_id);
            Member member = new Member();
            if(name != null)
                member.setName(name);
            if(gender != null) {
                member.setGender(Integer.parseInt(gender));
            }
            if(birth_date != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(birth_date);
                Timestamp timestamp = new Timestamp(parsedDate.getTime());
                member.setBirth_date(timestamp);
            }
            if(phone_number != null)
                member.setPhone_number(phone_number);
            if(email != null)
                member.setEmail(email);
            if(member_id != null)
                member.setMember_id(member_id);
            if(password != null)
                member.setPassword(password);
            return member;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
