package egovframework.injeinc.boffice.sy.userstatistics.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.userstatistics.vo.UserStatisticsVo;

public interface UserStatisticsSvc {
	public List<UserStatisticsVo> userStatisticsList(UserStatisticsVo userStatisticsVo) throws Exception;
}
