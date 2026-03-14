package com.demo.vote.presentation.controller;

import com.demo.vote.service.VoteItemService;
import com.demo.vote.presentation.response.VoteItemResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// @RequestMapping("/api/vote-items")
public class VoteItemController {

    private final VoteItemService voteItemService;

    public VoteItemController(VoteItemService voteItemService) {
        this.voteItemService = voteItemService;
    }

    @GetMapping("/api/voters")
    public List<VoteItemResponse> getVoteItems() {
        return voteItemService.getVoteItems();
    }
}