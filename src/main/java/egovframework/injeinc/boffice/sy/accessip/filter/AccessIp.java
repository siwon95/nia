package egovframework.injeinc.boffice.sy.accessip.filter;


public interface AccessIp {
    boolean accept(String remoteAddr);

    void reload();
}
