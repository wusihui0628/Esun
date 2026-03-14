package com.demo.vote.service;

import com.demo.vote.repository.VoteItemRepository;
import com.demo.vote.presentation.response.VoteItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteItemService {

    private final VoteItemRepository voteItemRepository;

    public VoteItemService(VoteItemRepository voteItemRepository) {
        this.voteItemRepository = voteItemRepository;
    }

    public List<VoteItemResponse> getVoteItems() {
        return voteItemRepository.getVoteItems();
    }
}