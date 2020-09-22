package egovframework.injeinc.common.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.vo.UmsVo;

public class UmsUtil extends CmmLogCtr {
	
	public int send(UmsVo umsVo) {
		int result = -1;
		String url = "jdbc:oracle:thin:@211.42.168.74:1521:YCDB1"; // url 형식
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		    Class.forName("oracle.jdbc.driver.OracleDriver"); // JDBC 드라이버 로드
		    conn = DriverManager.getConnection(url, "inje", "inje"); // 데이터베이스 연결(id/pw)
		    if(conn==null){
		    	System.out.println("연결실패");
		    }else{
		    	System.out.println("연결성공");
			// 출력 준비
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				sb.append(" insert into em_tran ( tran_pr, tran_refkey, tran_id, tran_phone, ");
				sb.append(" tran_callback, tran_status, tran_date, tran_msg, tran_type, tran_order_date");
				sb.append(" ) values (");
				sb.append(" (select nvl(max(tran_pr),0)+1 from em_tran),'9','1000',?,?,'1',sysdate,?,0,to_char(sysdate, 'yyyymmdd')) ");
				
				String sql = sb.toString();			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, umsVo.getSent());
				pstmt.setString(2, umsVo.getCallbackNo());
				pstmt.setString(3, umsVo.getContent());
				result = pstmt.executeUpdate();						
		    }
		}catch(ClassNotFoundException ce){
		    ce.printStackTrace();            
		}catch(SQLException se){
		    se.printStackTrace();    
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
		    System.out.println("나 finally");
		    try{ // 연결 해제(한정돼 있으므로)		    	
		    	if(pstmt!=null){    pstmt.close();        }		    	
		    	if(conn!=null){    conn.close();        }
		    }catch(SQLException se2){
		    	se2.printStackTrace();
		    }            
		}
		
		return result;
	}

}
