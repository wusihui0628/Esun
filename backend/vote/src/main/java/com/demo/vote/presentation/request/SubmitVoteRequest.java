package com.demo.vote.presentation.request;

public class SubmitVoteRequest {

    private Integer personId;
    private String userName;
    private Integer voteItemId;
    private String voteItemName;

    public SubmitVoteRequest() {
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getVoteItemId() {
        return voteItemId;
    }

    public void setVoteItemId(Integer voteItemId) {
        this.voteItemId = voteItemId;
    }

    public String getVoteItemName() {
        return voteItemName;
    }

    public void setVoteItemName(String voteItemName) {
        this.voteItemName = voteItemName;
    }
}