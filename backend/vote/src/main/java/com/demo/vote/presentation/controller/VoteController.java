package com.demo.vote.presentation.controller;

import com.demo.vote.presentation.request.SubmitVoteRequest;
import com.demo.vote.presentation.response.SubmitVoteResponse;
import com.demo.vote.service.VoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/api/voters")
    public SubmitVoteResponse submitVote(@RequestBody SubmitVoteRequest request) {
        return voteService.submitVote(request);
    }
}