package egovframework.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class Image2PaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

	public Image2PaginationRenderer() {

	}

	public void initVariables() {
		firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/adm/first.gif\" alt=\"처음\" /></a>&#160;";
		previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/adm/prev.gif\" alt=\"이전\" /></a>&#160;";
		currentPageLabel  = "<span class=\"num current\">{0}</span>&#160;";
		otherPageLabel    = "<a href=\"?pageIndex={1}\" class=\"num\" onclick=\"{0}({1});return false; \"><span>{2}</span></a>&#160;";
		nextPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/adm/next.gif\" alt=\"다음\" /></a>&#160;";
		lastPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/adm/last.gif\" alt=\"마지막\" /></a>&#160;";
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}