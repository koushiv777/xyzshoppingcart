package com.xyz.shopping.online.productservice.dto;

import javax.persistence.*;
import java.time.LocalDateTime;

public class ProductSearchLog {

    private String userId;
    private String searchText;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
