package egovframework.injeinc.boffice.sy.accessip.filter.core;

public class IpFilters {
    public static IpFilter create(Config config) {
        return new ConfigIpFilter(config);
    }

    public static IpFilter createCached(Config config) {
        return new CachedIpFilter(create(config));
    }
}
