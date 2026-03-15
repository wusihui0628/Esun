package com.demo.vote.presentation.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.vote.service.VoteItemService;

@RestController
@RequestMapping("/api/vote-items")
@CrossOrigin
public class VoteItemController {

    private final VoteItemService voteItemService;

    public VoteItemController(VoteItemService voteItemService) {
        this.voteItemService = voteItemService;
    }

    @GetMapping
    public Map<String, Object> getAll() {
        return voteItemService.getAllVoteItems();
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> request) {
        return voteItemService.createVoteItem(request);
    }

    @DeleteMapping("/{itemId}")
    public Map<String, Object> delete(@PathVariable Integer itemId) {
        return voteItemService.deleteVoteItem(itemId);
    }
}