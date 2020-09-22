package egovframework.injeinc.boffice.sy.userstatistics.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.userstatistics.vo.UserStatisticsVo;

@Repository("UserStatisticsDao")
public class UserStatisticsDao extends EgovAbstractMapper{
	
	public List selectListUserStatisticsYear(UserStatisticsVo userStatisticsVo) throws Exception {
		return selectList("UserStatisticsDao.selectListUserStatisticsYear", userStatisticsVo);
	}
	
	public List selectListUserStatisticsMonth(UserStatisticsVo userStatisticsVo) throws Exception {
		return selectList("UserStatisticsDao.selectListUserStatisticsMonth", userStatisticsVo);
	}
	
	public List selectListUserStatisticsDay(UserStatisticsVo userStatisticsVo) throws Exception {
		return selectList("UserStatisticsDao.selectListUserStatisticsDay", userStatisticsVo);
	}
	
}
