package infMarket.sevelets.member;

import infMarket.models.mybatis.dao.Member.MemberMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class AttendanceAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    private static int ATTENDANCE_POINT = 10000;
    private static int ATTENDANCE_LEVEL_UP = 1;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            MemberMapperExecutor memberMapperExecutor = new MemberMapperExecutor();
            Member member = (Member) request.getSession().getAttribute(MEMBER_SESSION_ID);
            Member memberInDB = memberMapperExecutor.selectMember(member.getMember_id());
            if(!isAttendedToday(memberInDB)){
                memberInDB.setLast_attendance_date(new Timestamp(System.currentTimeMillis()));
                memberInDB.setPoint(memberInDB.getPoint() + ATTENDANCE_POINT);
                memberInDB.setLevel(memberInDB.getLevel()+ATTENDANCE_LEVEL_UP);
                if(memberMapperExecutor.updateMember(memberInDB)) {
                    out.println("<script>alert('출석 완료!'); location.href='/';</script>");
                    request.getSession().removeAttribute(MEMBER_SESSION_ID);
                    request.getSession().setAttribute(MEMBER_SESSION_ID, memberInDB);
                }
                else
                    out.println("<script>alert('출석에 실패했습니다!'); location.href='/';</script>");
            }else{
                out.println("<script>alert('오늘은 이미 출석 하셨습니다!'); location.href='/';</script>");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean isAttendedToday(Member memberInDB) {
        Timestamp lastAttended = memberInDB.getLast_attendance_date();
        if(lastAttended != null){
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if(lastAttended.getYear() == now.getYear() && lastAttended.getMonth() == now.getMonth() && lastAttended.getDay() == now.getDay());
                return true;
        }
        return false;
    }
}
