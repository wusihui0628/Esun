package com.demo.vote.presentation.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.vote.service.VoteService;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/cast")
    public Map<String, Object> castVote(@RequestBody Map<String, Object> request) {
        return voteService.castVote(request);
    }
}
