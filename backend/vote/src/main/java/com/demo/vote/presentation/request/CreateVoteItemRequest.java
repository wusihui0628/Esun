package com.demo.vote.presentation.request;

public class CreateVoteItemRequest {

    private Integer itemId;
    private String items;

    public CreateVoteItemRequest() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}