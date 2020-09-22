package egovframework.injeinc.boffice.sy.userstatistics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.userstatistics.dao.UserStatisticsDao;
import egovframework.injeinc.boffice.sy.userstatistics.service.UserStatisticsSvc;
import egovframework.injeinc.boffice.sy.userstatistics.vo.UserStatisticsVo;

@Service("UserStatisticsSvc")
public class UserStatisticsImpl implements UserStatisticsSvc {
	@Resource(name="UserStatisticsDao")
	private UserStatisticsDao userStatisticsDao;
	
	public List<UserStatisticsVo> userStatisticsList(UserStatisticsVo userStatisticsVo) throws Exception {
		List<UserStatisticsVo> resultList = null;
		
		if(userStatisticsVo.getScYear() == null || userStatisticsVo.getScYear().equals("")) {//연도별 통계
			resultList = userStatisticsDao.selectListUserStatisticsYear(userStatisticsVo);
		}else if(userStatisticsVo.getScMonth().equals("") || userStatisticsVo.getScMonth().equals("")){//월별
			resultList = userStatisticsDao.selectListUserStatisticsMonth(userStatisticsVo);
		}else{//일별
			resultList = userStatisticsDao.selectListUserStatisticsDay(userStatisticsVo);
		}
		
		int memberCnt = 0;
		
		for(int i=0; i<resultList.size(); i++) {
			UserStatisticsVo temp = new UserStatisticsVo();
			temp = resultList.get(i);
			memberCnt +=  Integer.parseInt(temp.getMemberCnt()); 
		}
		
		UserStatisticsVo lastRow = new UserStatisticsVo();
		lastRow.setMemberCnt(String.valueOf(memberCnt));
		resultList.add(lastRow);
		return resultList;
	}

}
