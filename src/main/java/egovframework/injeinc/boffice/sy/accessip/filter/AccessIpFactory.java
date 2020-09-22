package egovframework.injeinc.boffice.sy.accessip.filter;

import java.util.Map;

import egovframework.injeinc.boffice.sy.accessip.filter.impl.AccessIpFactoryImpl;

public abstract class AccessIpFactory {
    public static AccessIpFactory getInstance() {
        return new AccessIpFactoryImpl();
    }

    abstract public AccessIp create(Map<String, String> config) throws AccessIpCreationException;
    
}
