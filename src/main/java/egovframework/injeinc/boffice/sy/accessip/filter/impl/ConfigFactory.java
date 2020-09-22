package egovframework.injeinc.boffice.sy.accessip.filter.impl;

import org.springframework.stereotype.Component;

import egovframework.injeinc.boffice.sy.accessip.filter.core.Config;

public abstract class ConfigFactory {
    public static ConfigFactory getInstance(String type) {
//        if (type.equals("text"))
//            return new TextConfigFactory();
//
//        if (type.equals("file"))
//            return new FileConfigFactory();

        if (type.equals("xml"))
            return new XMLConfigFactory();
        
        return null;
    }

    public abstract Config create(String value);

    public abstract boolean isReloadSupported();
}
