package egovframework.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
/**
 * ImagePaginationRenderer.java 클래스
 *
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2011. 9. 16.   서준식       이미지 경로에 ContextPath추가
 * </pre>
 */
public class ImageDrpPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

	public ImageDrpPaginationRenderer() {

	}

	public void initVariables(){
		/*firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"prev\">&lt;&lt;</a>\n";
		previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"back\">&lt;</a>\n\n";
		currentPageLabel  = "<span>{0}</span>\n";
		otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">{2}</a>\n";
		nextPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"forward\">&gt;</a>";
		lastPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"next\">&gt;&gt;</a>";*/
		
		firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"first\">&lt;&lt;</a>\n";
		previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"prev\">&lt;</a>\n\n";
		currentPageLabel  = "<a class='active'>{0}</a>\n";
		otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">{2}</a>\n";
		nextPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"next\">&gt;</a>";
		lastPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" class=\"last\">&gt;&gt;</a>";
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}