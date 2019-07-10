package com.xkjsj.go.util.tag;

import com.xkjsj.go.util.common.GoConstants;

public class PageModel {
	private int recordCount;
	/** 褰撳墠椤甸潰 */
	private int pageIndex ;
	/** 姣忛〉鍒嗗灏戞潯鏁版嵁   */
	private int pageSize = GoConstants.PAGE_DEFAULT_SIZE = 4;
	
	/** 鎬婚〉鏁� */
	private int totalSize;

	public int getRecordCount() {
		this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageIndex() {
		this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
		/** 鍒ゆ柇褰撳墠椤甸潰鏄惁瓒呰繃浜嗘�椤垫暟:濡傛灉瓒呰繃浜嗛粯璁ょ粰鏈�悗涓�〉浣滀负褰撳墠椤� */
		this.pageIndex = this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;
		
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		this.pageSize = this.pageSize <= GoConstants.PAGE_DEFAULT_SIZE?GoConstants.PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalSize() {
		if(this.getRecordCount() <=0){
			totalSize = 0 ;
		}else{
			totalSize = (this.getRecordCount() -1)/this.getPageSize() + 1;
		}
		return totalSize;
	}
	
	
	public int getFirstLimitParam(){
		return (this.getPageIndex()-1)*this.getPageSize() ;
	}
}
