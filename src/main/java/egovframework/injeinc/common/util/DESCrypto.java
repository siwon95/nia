package egovframework.injeinc.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

import org.apache.poi.poifs.crypt.Decryptor;

import egovframework.cmm.service.EgovProperties;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * DES 기반의 대칭키 암호화 복호화
 * </p>
 *
 * <pre>
 *    1.
 *    비밀키 파일을 가지고 작업하는 방법 // DESCryptoKeyBuilder.make()
 *    함수는 최초 한번만 실행해서 비밀키를 만들고....
 *    DESCryptoKeyBuilder.make("/path/to/tdf2-secret.key"); // 이후에는
 *    아래 작업을 통해 암/복호화를 진행함 String encStr = DESCrypto.encrypt("홍길동",
 *    "/path/to/tdf2-secret.key"); String decStr =
 *    DESCrypto.decrypt(encStr, "/path/to/tdf2-secret.key"); // <br />
 *    2.
 *    비밀키 없이 작업하는 방법 // 별도의 비밀키를 지정하지 않을 경우에는 TDF2 라이브러리에 포함된 //
 *    기본키(/dev/tdf2/commons/crypto/tdf2-secret.key)를 사용함 String
 *    encStr = DESCrypto.encrypt("홍길동"); String decStr =
 *    DESCrypto.decrypt(encStr);
 * </pre>
 * @author
 * @date 2009. 10. 7. $Id: $
 */
public class DESCrypto {

	private static String keyFile = "/egovframework/egovProps/secret.key";
	private static String algorithm = "DESede/ECB/PKCS5Padding";

	/**
	 * 파일 암호화에 쓰이는 버퍼 크기 지정
	 */
	public static final int kBufferSize = 8192;

	private InputStream defaultInputStream = null;

	private DESCrypto() {
		defaultInputStream = getClass().getResourceAsStream(keyFile);

		if (defaultInputStream == null)
			defaultInputStream = getClass().getResourceAsStream(keyFile);
	}

	/**
	 * 지정된 비밀키를 가지고 오는 메서드
	 *
	 * @return Key 비밀키 클래스
	 * @exception Exception
	 */
	private static Key getKey(String keyFile) throws Exception {

		String fileURL = null;
		ObjectInputStream in = null;

		if (keyFile == null) {
			in = new ObjectInputStream(new DESCrypto().defaultInputStream);
		} else {
			fileURL = keyFile;
			in = new ObjectInputStream(new FileInputStream(fileURL));
		}

		Key key = (Key) in.readObject();
		in.close();

		return key;
	}

	/**
	 * 문자열 대칭 암호화
	 *
	 * @param ID
	 *            비밀키 암호화를 희망하는 문자열
	 * @return String 암호화된 ID
	 * @exception Exception
	 */
	public static String encrypt(String ID, String keyFile) throws Exception {

		if (ID == null || ID.length() == 0)
			return "";

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, getKey(keyFile));
		String amalgam = ID;

		byte[] inputBytes1 = amalgam.getBytes();
		byte[] outputBytes1 = cipher.doFinal(inputBytes1);
		BASE64Encoder encoder = new BASE64Encoder();
		String outputStr1 = encoder.encode(outputBytes1);

		return outputStr1;
	}

	public static String encrypt(String ID) throws Exception {

		return encrypt(ID, null);
	}

	/**
	 * 문자열 대칭 복호화
	 *
	 * @param codedID
	 *            비밀키 복호화를 희망하는 문자열
	 * @return String 복호화된 ID
	 * @exception Exception
	 */
	public static String decrypt(String codedID, String keyFile)
			throws Exception {

		if (codedID == null || codedID.length() == 0)
			return "";

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, getKey(keyFile));

		BASE64Decoder decoder = new BASE64Decoder();

		byte[] inputBytes1 = decoder.decodeBuffer(codedID);
		byte[] outputBytes2 = cipher.doFinal(inputBytes1);

		String strResult = new String(outputBytes2);

		return strResult;
	}

	public static String decrypt(String codedID) throws Exception {

		return decrypt(codedID, null);
	}

	/**
	 * 파일 대칭 암호화
	 *
	 * @param infile
	 *            암호화을 희망하는 파일명
	 * @param outfile
	 *            암호화된 파일명
	 * @exception Exception
	 */
	public static void encryptFile(String infile, String outfile, String keyFile)
			throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, getKey(keyFile));

		FileInputStream in = new FileInputStream(infile);
		FileOutputStream fileOut = new FileOutputStream(outfile);

		CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
		byte[] buffer = new byte[kBufferSize];
		int length;
		while ((length = in.read(buffer)) != -1)
			out.write(buffer, 0, length);
		in.close();
		out.close();
	}

	public static void encryptFile(String infile, String outfile)
			throws Exception {

		encryptFile(infile, outfile, null);
	}

	/**
	 * 파일 대칭 복호화
	 *
	 * @param infile
	 *            복호화을 희망하는 파일명
	 * @param outfile
	 *            복호화된 파일명
	 * @exception Exception
	 */
	public static void decryptFile(String infile, String outfile, String keyFile)
			throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, getKey(keyFile));

		FileInputStream in = new FileInputStream(infile);
		FileOutputStream fileOut = new FileOutputStream(outfile);

		CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
		byte[] buffer = new byte[kBufferSize];
		int length;
		while ((length = in.read(buffer)) != -1)
			out.write(buffer, 0, length);
		in.close();
		out.close();
	}

	public static void decryptFile(String infile, String outfile)
			throws Exception {

		decryptFile(infile, outfile, null);
	}

	public static void main(String[] ars) throws Exception {

//		if (ars.length < 2) {
//			System.out
//					.println("USE : DESCrypto [-d | -e | -fd | -fe] [text | inputfilename outputfilename]");
//			System.exit(0);
//		}
//		if (ars[0].equals("-d"))
//			System.out.println(DESCrypto.decrypt(ars[1]));
//
//		if (ars[0].equals("-e"))
//			System.out.println(DESCrypto.encrypt(ars[1]));
//
//		if (ars[0].equals("-fd"))
//			DESCrypto.decryptFile(ars[1], ars[2]);
//
//		if (ars[0].equals("-fe"))
//			DESCrypto.encryptFile(ars[1], ars[2]);
		
		//
		DESCrypto desCrypto = new DESCrypto();
		System.out.println(desCrypto.decrypt("eueXCKKdP9UBU43F+mtM/g=="));
	}
}