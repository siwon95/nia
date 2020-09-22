<%@ page pageEncoding = "UTF-8" %>
<%@ include file="./api/WNDefine.jsp" %>
<%!

	//static String SEARCH_IP="211.42.168.58"; //개발
 	static String SEARCH_IP="211.42.168.72";  //운영
	static int SEARCH_PORT=7000;
	static String MANAGER_IP=SEARCH_IP;
	static int MANAGER_PORT=7800;

	public String[] COLLECTIONS = new String[]{"RESERVATION","BBS","CONTENTS","EMPLOYEE","MENU","MINWON","PHOTO","THEME"};
	public String[] COLLECTIONS_NAME = new String[]{"RESERVATION","BBS","CONTENTS","EMPLOYEE","MENU","MINWON","PHOTO","THEME"};
	public String[] MERGE_COLLECTIONS = new String[]{""};
	public class WNCollection{
	public String[][] MERGE_COLLECTION_INFO = null;
	public String[][] COLLECTION_INFO = null;
		WNCollection(){
			COLLECTION_INFO = new String[][]
			{
			{
			"RESERVATION", // set index name
			"RESERVATION", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TITLE,CONTENT,WRITER",// set search field
			"DOCID,DATE,TITLE/150,CONTENT/250,WRITER,LINK,RI_PLACE/200,RI_TYPE,RI_SUPERVISION_ORG,RI_SUPERVISION_ORG_NAME,RI_CONFIRM_YN,RI_MANAGE_DEPT,RI_PROGRESS,RI_SDATE,RI_EDATE,RI_RESERVATION_SDATE,RI_RESERVATION_EDATE,RI_RESERVATION_SDT,RI_RESERVATION_EDT,RI_IMAGE_FILE_ID,RI_MAIN_FILE_NM,RI_READ_CNT,RI_RECRUIT_YN,RI_RECRUIT_CNT,RI_LOT_USE,RI_OFF_LOT_USE,RI_PO_YN,USERCNT,LC_CATEGORY_UPR_NM,LC_TARGET_NAME,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"통합예약" // collection display name
			}
         ,
			{
			"BBS", // set index name
			"BBS", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TITLE,CONTENT,WRITER",// set search field
			"DOCID,DATE,TITLE/150,CONTENT/250,WRITER,LINK,CATEGORY,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"게시판" // collection display name
			}
         ,
			{
			"CONTENTS", // set index name
			"CONTENTS", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TITLE,CONTENT,WRITER",// set search field
			"DOCID,DATE,TITLE/150,CONTENT/250,WRITER,LINK,CATEGORY,SITE_CD,SITE_DOMAIN,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"웹페이지" // collection display name
			}
         ,
			{
			"EMPLOYEE", // set index name
			"EMPLOYEE", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TITLE,CONTENT,DEPARTMENT,WRITER,JOBLEVEL",// set search field
			"DOCID,DATE,TITLE/150,CONTENT/250,WRITER,LINK,DEPARTMENT,TEL,JOBLEVEL,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"직원/업무" // collection display name
			}
         ,
			{
			"MENU", // set index name
			"MENU", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TITLE,CONTENT,WRITER",// set search field
			"DOCID,DATE,TITLE/150,CONTENT/250,WRITER,LINK,SITE_CD,SITE_NM,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"메뉴검색" // collection display name
			}
         ,
			{
			"MINWON", // set index name
			"MINWON", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TITLE,CONTENT,WRITER",// set search field
			"DOCID,DATE,TITLE/150,CONTENT/250,WRITER,LINK,FILE_PATH,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"민원사무편람" // collection display name
			}
         ,
			{
			"PHOTO", // set index name
			"PHOTO", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TITLE,CONTENT,WRITER",// set search field
			"DOCID,DATE,TITLE/150,CONTENT/250,WRITER,LINK,FILE_PATH,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"이미지" // collection display name
			}
         ,
			{
			"THEME", // set index name
			"THEME", // set collection name
			"0,3",  // set pageinfo (start,count)
			"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
			"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
			"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
			"TM_KEYWORD,TM_KEYWORD_DOC",// set search field
			"DOCID,DATE,TITLE,CONTENT,WRITER,TM_KEYWORD,SERVICE_TYPE,TM_URL,ALIAS",// set document field
			"", // set date range
			"", // set rank range
			"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
			"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
			"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
			"", // set filter operation (<fieldname:operator:value>)
			"", // set groupby field(field, count)
			"", // set sort field group(field/order,field/order,...)
			"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
			"", // set categoryGroupBy (fieldname:value)
			"", // set categoryQuery (fieldname:value)
			"", // set property group (fieldname,min,max, groupcount)
			"ALIAS", // use check prefix query filed
			"", // set use check fast access field
			"", // set multigroupby field
			"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
			"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
			"THEME" // collection display name
			}
			};
		}
	}
%>