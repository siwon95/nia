package egovframework.injeinc.boffice.hot.stat.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.hot.stat.vo.HotStatVo;

@Repository("HotStatDao")
public class HotStatDao extends EgovAbstractMapper {
	

    /** 월별 리스트 조회한다.*/
    public List selectListHotStatMonth(HotStatVo hotStatVO) throws Exception {
        return selectList("HotStatDao.selectListHotStatMonth", hotStatVO);
    }
    
    /** 일별 리스트 조회한다.*/
    public List selectListHotStatDay(HotStatVo hotStatVO) throws Exception {
        return selectList("HotStatDao.selectListHotStatDay", hotStatVO);
    }
    
    /** 목록갯수 조회한다.*/
    public int selectPagHotStat(HotStatVo hotStatVO) throws Exception {
        return (Integer)selectOne("HotStatDao.selectPagHotStat", hotStatVO);
    }
    
    /** 빠른서비스 조회수 증가 */
   	public void updateHotViewCnt(String hlIdx) throws Exception {
   		
   	
   		//오늘 날짜에 있을경우 카운트+1
  		Calendar cal = Calendar.getInstance();
  		HashMap<String, String> param = new HashMap<String, String>();
  		
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

  		String strdate = sdf.format(cal.getTime());
  		
  		param.put("year", strdate.substring(0, 4)); 
  		param.put("month", strdate.substring(5, 7));
  		param.put("day", strdate.substring(8, 10));
  		param.put("hlIdx", hlIdx);
  		
  		int cnt = (Integer)selectOne("HotStatDao.selectHotViewCnt", param);		//해당날짜로 조회
  		
  		if(cnt > 0){
  			update("HotStatDao.updateHotViewCnt",param);	//오늘 날짜에 있을경우 update
  		}else{
  			insert("HotStatDao.insertHotViewCnt",param);	//오늘 날짜에 없을경우 insert
  		}
  			
   	}
}