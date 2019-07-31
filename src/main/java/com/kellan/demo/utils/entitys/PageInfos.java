package com.kellan.demo.utils.entitys;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页信息值对象类.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageInfos {
	/**
	 * 单页条目数.
	 */
	@ApiModelProperty(value="单页条数")
	private Integer pageSize;

	/**
	 * 当前页码.
	 */
	@ApiModelProperty(value="当前页码")
	private Integer pageNumber;

	/**
	 * 总条目数.
	 */
	@ApiModelProperty(hidden = true)
	private Integer total;


	public PageInfos() {
		super();
	}

	public PageInfos(Integer pageNumber, Integer pageSize, Integer total) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.total = total;
	}

	public Integer getPageSize() {
		if (pageSize == null) return 10;
		return pageSize;
	}

	public PageInfos setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getPageNumber() {
		if (pageNumber == null) return 1;
		return pageNumber;
	}

	public PageInfos setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}

	public Integer getTotal() {
		return total;
	}

	public PageInfos setTotal(Integer total) {
		this.total = total;
		return this;
	}


	
}
