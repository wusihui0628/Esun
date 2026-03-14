package com.demo.vote.presentation.response;

public class VoteItemResponse {

    private Long itemId;
    private String itemName;
    private Integer VoteCnt;

    public VoteItemResponse() {
    }

    public VoteItemResponse(Long itemId, String itemName, Boolean activated, Integer VoteCnt) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.VoteCnt = VoteCnt;
    }

    public Long getitemId() {
        return itemId;
    }

    public void setitemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getitemName() {
        return itemName;
    }

    public void setitemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getVoteCnt() {
        return VoteCnt;
    }

    public void setVoteCnt(Integer VoteCnt) {
        this.VoteCnt = VoteCnt;
    }
}