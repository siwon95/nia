package egovframework.injeinc.common.push.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import egovframework.injeinc.common.push.vo.GCMVo;
import egovframework.injeinc.common.social.service.SocialSvc;
 
/**
 * GCM UTIL
 * 
 * gcm-server.jar, json-simple-1.1.1.jar 필요
 * 
 * @author 
 *
 */
public class GCMUtil {		
	
    static final String API_KEY = "AIzaSyCNmOxRgflHGbbaRz3-3WbNDRytbKb3mDs"; // server api key
    private static final int MAX_SEND_CNT = 999; // 1회 최대 전송 가능 수
    
    // android 에서 받을 extra key (android app 과 동일해야 함)
    static final String TITLE_EXTRA_KEY = "Title";
    static final String MSG_EXTRA_KEY = "Message";
    static final String LINK_URL = "link_url";
    // android 에서 받을 extras key 
 
    List<String> resList = null;
    private Sender sender;
    private Message message;
    
    public ArrayList<GCMVo> rtnList;
 
    /**
     * GCM Util 생성자
     * RegistrationId 셋팅, sender 셋팅, message 셋팅
     * 
     * @param reslist : RegistrationId
     * @param gcmVo : msg 정보
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
    public GCMUtil(GCMVo gcmVo) throws Exception {
        sender = new Sender(API_KEY);
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		SocialSvc socialSvc = (SocialSvc)wContext.getBean("SocialSvc");
		
        this.resList = socialSvc.selectPushInfoAndroid();
        setMessage(gcmVo);
        rtnList = new ArrayList<GCMVo>();
        sendGCM();
    }
 
    /**
     * 메시지 셋팅
     * @param gcmVo
     */
    private void setMessage(GCMVo gcmVo) {
        Builder builder = new Message.Builder();
        builder.addData(TITLE_EXTRA_KEY, gcmVo.getTitle());
        builder.addData(MSG_EXTRA_KEY, gcmVo.getMsg());
        builder.addData(LINK_URL, gcmVo.getLink_url());
        message = builder.build();
    }
 
    /**
     * 메시지 전송
     */
    private void sendGCM() {
        if (resList.size() > 0) {
            if (resList.size() <= MAX_SEND_CNT) { // 한번에 1000건만 보낼 수 있음
                sendMultivastResult(resList);
            } else {
                List<String> resListTemp = new ArrayList<String>();
                for (int i = 0; i < resList.size(); i++) {
                    if ((i + 1) % MAX_SEND_CNT == 0) {
                        sendMultivastResult(resListTemp);
                        resListTemp.clear();
                    }
                    resListTemp.add(resList.get(i));
                }
 
                // 1000건씩 보내고 남은 것 보내기
                if(resListTemp.size() != 0){
                        sendMultivastResult(resListTemp);
                }
            }
        }
 
    }
 
    /**
     * 실제 멀티 메시지 전송
     * 
     * @param list
     */
    private void sendMultivastResult(List<String> list) {
        try {
            System.out.println("Message : "+message);
            for(int i = 0; i < list.size(); i++){
            	System.out.println(i+" : "+list.get(i));
            }
            MulticastResult multiResult = sender.send(message, list, 5); // 발송할 메시지, 발송할 타깃(RegistrationId), Retry 횟수
            List<Result> resultList = multiResult.getResults();
            
            
            for (int i=0; i<resultList.size(); i++){
                Result result = resultList.get(i);
 
                // 결과 셋팅
                GCMVo rtnGcmVo = new GCMVo();
                rtnGcmVo.setRegId(list.get(i));
                rtnGcmVo.setMsgId(result.getMessageId());
                rtnGcmVo.setErrorMsg(result.getErrorCodeName());
            
                if (result.getMessageId() != null) { // 전송 성공
                    rtnGcmVo.setPushSuccessOrFailure(true);
                } else { // 전송 실패
                    rtnGcmVo.setPushSuccessOrFailure(false);
                }
                
                rtnList.add(rtnGcmVo);
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
