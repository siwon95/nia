package egovframework.injeinc.common.push.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.common.push.vo.GCMVo;
import egovframework.injeinc.common.social.service.SocialSvc;
import egovframework.injeinc.common.social.vo.PushVo;
import egovframework.injeinc.common.util.EgovFileScrty;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public class APNSUtil {
	
	@Resource(name = "SocialSvc")
	private SocialSvc socialsvc;
	
	public static int RUN_MODE_DEVELOPMENT = 1;
	public static int RUN_MODE_PRODUCTION = 2;		
	
	public void sendApns(GCMVo gcmVo) throws Exception {
		PushNotificationPayload payload = PushNotificationPayload.complex();
		payload.addAlert(gcmVo.getMsg());	
		payload.addCustomDictionary("Title", gcmVo.getTitle());
		payload.addCustomDictionary("link_url", gcmVo.getLink_url());
		payload.addBadge(1);
		payload.addSound("default");				

		boolean sendSingle = true; //true:싱글 : false:다중 
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		SocialSvc socialSvc = (SocialSvc)wContext.getBean("SocialSvc");
		
		List tokens = socialSvc.selectPushInfoIos();
		if(tokens.size() == 1){
			sendSingle = true;
		}else{
			sendSingle = false;
		}
		 
		PushNotificationManager pushManager = new PushNotificationManager();		
		pushManager.initializeConnection(new AppleNotificationServerBasicImpl("D:\\apnsDev.p12", "nch9512", false));
		List<PushedNotification> notifications = new ArrayList<PushedNotification>();
		if (sendSingle){
	         Device device = new BasicDevice();
	         device.setToken((String) tokens.get(0));
	         PushedNotification notification = pushManager.sendNotification(device, payload);
	         notifications.add(notification);
	    }else{
	          List<Device> device = new ArrayList<Device>();
	          for (int i = 0; i < tokens.size(); i++){	        	 
	              device.add(new BasicDevice((String)tokens.get(i)));
	          }
	          notifications = pushManager.sendNotifications(payload, device);
	    }

		List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	    List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	    int failed = failedNotifications.size();
	    int successful = successfulNotifications.size();
	}	
	
	
	static String url;

	public static void main(String[] args) {
		url = "jdbc:oracle:thin:@106.251.236.2:1521:testdb"; // url 형식
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		    Class.forName("oracle.jdbc.driver.OracleDriver"); // JDBC 드라이버 로드
		    conn = DriverManager.getConnection(url, "injeinc", "injeinc"); // 데이터베이스 연결(id/pw)
		    if(conn==null){
			System.out.println("연결실패");
		    }else{
			System.out.println("연결성공");
			// 출력 준비
			StringBuffer sb = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			sb.append(" SELECT CU_ID,CU_PWD ");
			sb.append(" FROM EZ_CMS_USER ");
			sb.append(" WHERE	 ");			
			sb.append(" P_HOMEPAGE = 'L' ");
			String sql = sb.toString();			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 출력
			String isql = "UPDATE EZ_CMS_USER SET CU_PWD = ? WHERE CU_ID = ? AND P_HOMEPAGE = 'L'";
			
			while(rs.next()){ // boolean 값을 던짐
			    String cu_id = rs.getString(1); // 바인딩
			    String cu_pwd = rs.getString(2);
			    System.out.println(cu_id+", "+cu_pwd);			    			    
												
				pstmt = conn.prepareStatement(isql);        
				pstmt.setString(1,EgovFileScrty.encryptPassword(cu_pwd));
				pstmt.setString(2,cu_id); 			
				pstmt.executeUpdate();
				pstmt.close();
			}						
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
		    	if(rs!=null){        rs.close();            }
		    	if(pstmt!=null){    pstmt.close();        }
		    	if(stmt!=null){    stmt.close();        }
		    	if(conn!=null){    conn.close();        }
		    }catch(SQLException se2){
		    	se2.printStackTrace();
		    }            
		}
	}
}
