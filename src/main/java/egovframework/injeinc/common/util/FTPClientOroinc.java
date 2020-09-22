package egovframework.injeinc.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oroinc.net.ftp.FTP;
import com.oroinc.net.ftp.FTPClient;
import com.oroinc.net.ftp.FTPFile;
import com.oroinc.net.ftp.FTPReply;

import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.service.EgovProperties;

public class FTPClientOroinc {
	private static final Logger LOGGER = LoggerFactory.getLogger(FTPClientOroinc.class);	
	FTPClient ftpClient = new FTPClient();
	
	String server	= EgovProperties.getProperty("WebServer.FTP_SERVER");
	String id		= EgovProperties.getProperty("WebServer.FTP_USER");
	String password	= EgovProperties.getProperty("WebServer.FTP_PASS");
	int port		= Integer.valueOf(EgovProperties.getProperty("WebServer.FTP_PORT"));
	
	String platform	= EgovProperties.getProperty("Globals.PLATFORM");
	
	// 계정과 패스워드로 로그인
	public boolean login() {
		try {
			this.connect();
			return ftpClient.login(id, password);
		}catch (IOException e) {
			LOGGER.debug("IGNORED: "+e.getMessage());
		}
		return false;
	}

	// 서버로부터 로그아웃
	public boolean logout() {
		try {
			return ftpClient.logout();
		}catch (IOException e) {
			LOGGER.debug("IGNORED: "+e.getMessage());
		}
		return false;
	}

	// 서버로 연결
	public boolean connect() {
		boolean bReturn = true;
		try {
			ftpClient.connect(server, port);

			// 연결 시도후, 성공했는지 응답 코드 확인
			int reply= ftpClient.getReplyCode();
			
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.out.print("서버로부터 연결을 거부당했습니다");
			}
			
		}catch (SocketException e) {
			bReturn = false;
			if(ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch(IOException e2) {
					bReturn = false;
					LOGGER.debug("IGNORED: "+e.getMessage());
				} catch(Exception e2) {
					bReturn = false;
					LOGGER.debug("IGNORED: "+e.getMessage());
				}
			}
		}catch (Exception e) {
			bReturn = false;
			if(ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch(IOException e2) {
					bReturn = false;
					LOGGER.debug("IGNORED: "+e.getMessage());
				} catch(Exception e2) {
					bReturn = false;
					LOGGER.debug("IGNORED: "+e.getMessage());
				}
			}
		}
		return bReturn;
	}
	
	// 서버로부터 연결을 닫는다
	public void disconnect() {
		try {
			ftpClient.disconnect();
		}catch (IOException e) {
			LOGGER.debug("IGNORED: "+e.getMessage());
		}
	}

	// FTP의 ls 명령, 모든 파일 리스트를 가져온다
	public FTPFile[] list() {
		try {
			return this.ftpClient.listFiles();
		}catch (IOException e) {
			LOGGER.debug("IGNORED: "+e.getMessage());
		}
		return null;
	}

	public boolean put(String strRemotePath, String strRemoteFile, String strLocalPath, String strLocalFile) {
		boolean bReturn = true;
		
		if(platform != null){
			if(platform.equals("service")) {
				try {
					if(!connect()) return false;
					if(!login()) return false;
					
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(strLocalPath + strLocalFile));
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					ftpClient.changeWorkingDirectory(strRemotePath);
					ftpClient.storeFile(strRemoteFile, bis);
				}catch (IOException e) {
					LOGGER.debug("IGNORED: "+e.getMessage());				
				}finally {
					logout();
					disconnect();
				}
			}
		}
		
		return bReturn;
	}
	
	// 파일을 전송 받는다
	public File get(String source, String target) {
		
		OutputStream output = null;
			
		if(source == null) {
			return null;
		}
		
		try {
			File local = new File(source);
			output = new FileOutputStream(local);
		}catch (FileNotFoundException e) {			
			LOGGER.debug("IGNORED: "+e.getMessage());
			EgovResourceCloseHelper.close(output);
		}finally{
			EgovResourceCloseHelper.close(output);
		}
		
		File file = new File(source);
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			if(ftpClient.retrieveFile(source, output)) {
				return file;
			}
		}catch (IOException ioe) {
			throw new RuntimeException();
		}
		return null;
	}
	
	public boolean getFile(String strRemotePath, String strSfile,String strLocalPath, String strTfile) {
		
		boolean bReturn = true;
		
		if(!connect()) {
//			disconnect();
			return false;
		}
		
		if(!login()) {
			disconnect();
			return false;
		}
		
		if(!cd(strRemotePath)){
			return false;
		}
		
		byte b[] = null;
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		
		File downfile = get(strSfile, strLocalPath + strTfile);
		File savefile = new File(strLocalPath + strTfile);
		
		b = new byte[(int)downfile.length()];
		
		try {
			try {
				fin = new BufferedInputStream(new FileInputStream(downfile));
				outs = new BufferedOutputStream(new FileOutputStream(savefile));
				
				int read = 0;

				while((read = fin.read(b)) != -1){
					outs.write(b,0,read);
				}
				
				outs.close();
				fin.close();
			}catch (IOException e) {
				bReturn = false;
			}finally {
				if(outs!=null) outs.close();
				if(fin!=null) fin.close();
			}
		}catch (IOException ioe) {
			bReturn = false;
		}finally {
			logout();
			disconnect();
		}
		return bReturn;
	}
	
	//파일 삭제 처리
	public boolean delfile(String strPath, String source) {
		
		boolean bReturn = true;
		
		if(platform != null){
			if(platform.equals("service")) {
			
				connect();
				
				if(!login()) {
					disconnect();
					return false;
				}
		
				if(!cd(strPath)){
					return false;
				}
				
				try {
					ftpClient.deleteFile(source);
				}catch (IOException e) {
					bReturn = false;
				}finally {
					logout();
					disconnect();
				}
			}
		}
		
		return bReturn;
	}
	
	public boolean renamefile(String strPath, String strSname, String strRname) {
		
		boolean bReturn = true;
		
		connect();
		
		if(!login()) {
			disconnect();
			return false;
		}

		if(!cd(strPath)){
			return false;
		}
		
		try {
			ftpClient.rename(strSname, strRname);
		}catch (Exception e) {
			bReturn = false;
		}finally {
			logout();
			disconnect();
		}
			return bReturn;
	}
	
	// 서버 디렉토리 이동
	public boolean cd(String strPath) {
		boolean bReturn = true;
		try {
			ftpClient.changeWorkingDirectory(strPath);
		}catch (IOException ioe) {
			bReturn = false;
		}
		return bReturn;
	}

	public boolean mkdir(String strPath, String strDir) {
		boolean bReturn = true;
		
		if(platform != null){
			if(platform.equals("service")) {
			
				connect();
				
				if(!login()) {
					disconnect();
					return false;
				}
		
				if(!cd(strPath)){
					return false;
				}
				
				try {
					ftpClient.makeDirectory(strDir);
				}catch (IOException ioe) {
					bReturn = false;
				}finally {
					logout();
					disconnect();
				}
				
			}
		}
		
		return bReturn;
	}
	
	public boolean rmdir(String strPath, String strDir) {
		boolean bReturn = true;
		
		connect();
		
		if(!login()) {
			disconnect();
			return false;
		}
		
		if(!cd(strPath)){
			return false;
		}
		
		try {
			ftpClient.removeDirectory(strDir);
		}catch (IOException ioe) {
			bReturn = false;
			
		}finally {
			logout();
			disconnect();
		}
		
		return bReturn;
	}
}
