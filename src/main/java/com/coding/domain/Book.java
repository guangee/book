package com.coding.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author guanweiming
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "tab_book")
public class Book extends BaseDO {

    @ApiModelProperty("书名")
    private String name;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("出版社")
    private String press;

    @ApiModelProperty("ISBN")
    private String isbn;

    @ApiModelProperty("出版日期")
    private String pubDate;

    @ApiModelProperty("页码数")
    private Integer pages;

    @ApiModelProperty("状态 0-待归还 1-可借阅")
    private Integer status;


}