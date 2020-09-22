package egovframework.injeinc.boffice.sy.accessip.filter.impl;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import egovframework.injeinc.boffice.sy.accessip.filter.AccessIp;
import egovframework.injeinc.boffice.sy.accessip.filter.core.IpFilter;
import egovframework.injeinc.boffice.sy.accessip.filter.core.IpFilters;

public class AccessIpImpl implements AccessIp {

    private AtomicReference<IpFilter> ipFilter = new AtomicReference<IpFilter>();
    
    private ConfigFactory configFactory;
    private String value;

    public void init(Map<String, String> config) {
        configFactory = ConfigFactory.getInstance(config.get("type"));
        value = config.get("value");
        createIpFilterFromConfig();
    }

    private void createIpFilterFromConfig() {
        ipFilter.set(IpFilters.createCached(configFactory.create(value)));
    }

    
    public boolean accept(String remoteAddr) {
        return ipFilter.get().accept(remoteAddr);
    }

    
    public void reload() {
        if (!configFactory.isReloadSupported()){
        	throw new UnsupportedOperationException();
        }
        createIpFilterFromConfig();
    }
}
