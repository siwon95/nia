package egovframework.injeinc.boffice.sy.accessip.filter.impl;

import java.util.Map;

import egovframework.injeinc.boffice.sy.accessip.filter.AccessIp;
import egovframework.injeinc.boffice.sy.accessip.filter.AccessIpCreationException;
import egovframework.injeinc.boffice.sy.accessip.filter.AccessIpFactory;

public class AccessIpFactoryImpl extends AccessIpFactory {
    @Override
    public AccessIp create(Map<String, String> config) {
        try {
            AccessIpImpl ipBlocker = new AccessIpImpl();
            ipBlocker.init(config);
            return ipBlocker;
        } catch (Exception ex) {
            throw new AccessIpCreationException(ex);
        }
    }
}
