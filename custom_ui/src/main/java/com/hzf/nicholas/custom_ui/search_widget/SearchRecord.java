package com.hzf.nicholas.custom_ui.search_widget;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class SearchRecord extends LitePalSupport {

    @Column(nullable = false)
    private Integer type;//0 历史记录 1 热门记录
    @Column(nullable = false)
    private String content;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
