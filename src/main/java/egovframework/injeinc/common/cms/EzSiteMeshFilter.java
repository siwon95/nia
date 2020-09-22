package egovframework.injeinc.common.cms;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

/**
 * Servlet Filter implementation class EzSiteMeshFilter
 */
@WebFilter("/EzSiteMeshFilter")
public class EzSiteMeshFilter extends SiteMeshFilter{

    /**
     * Default constructor. 
     */
    public EzSiteMeshFilter() {
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain) throws ServletException, IOException {
    	/*//아이피가 127.0.0.1이랑 111.224 로 시작하는 아이피만 허용한다는 뜻
    	String allowIPScope = "127.0.0.1;";
    	String allowDomainScope = "localhost;106.251.236.2;172.21.163.50;200.4.10.73;98.15.14.174;98.15.14.176;98.15.14.172;200.4.10.81;200.4.10.83;200.4.10.83;211.42.168.58;200.4.10.70;easyframe.injeinc.co.kr";


    	InetAddress ip;
    	String strUrl = ((HttpServletRequest) servletRequest).getServerName();
    	strUrl = strUrl.substring(strUrl.indexOf(".")+1);
    	String requestIP1 = "";
    	
    	try {

    		ip = InetAddress.getLocalHost();
    		requestIP1 = ip.getHostAddress();
    		
    		//System.out.println("Current IP address : " + requestIP1);
    		//System.out.println("Current Domain address : " + strUrl);

    	  } catch (UnknownHostException e) {

    		e.printStackTrace();

    	  }

    	 if(
    			 requestIP1.indexOf(allowIPScope) == -1 
    			 && allowDomainScope.indexOf(strUrl) == -1
    	 ){


    		    //여기 들어왔다면 접근 불가
    		    PrintWriter out = servletResponse.getWriter();
    		    servletResponse.setContentType("text/html; charset=utf-8");

    				  out.println("<HTML>");
    				  out.println("<HEAD><TITLE>Homepage Not Connect!</TITLE>");
    				  out.println("<meta charset=\"utf-8\">");
    				   out.println("<meta http-equiv=\"Cache-Control\" content=\"No-Cache\" />");
    				   out.println("<meta http-equiv=\"Expires\" content=\"0\" /> ");
    				   out.println("<meta http-equiv=\"Pragma\" content=\"No-Cache\" />");
    				 out.println("</HEAD>");
    		      out.println("<BODY>");
    		      out.println("<H3>Homepage Not Connect!</H3>");
    		      out.println("</BODY>");
    		      out.println("</HTML>");

    		      out.flush();
    		      out.close();

    	 }
    	 else
    	 {*/
    		 super.doFilter(servletRequest, servletResponse, filterChain);
    	 //}
    }
		
}
