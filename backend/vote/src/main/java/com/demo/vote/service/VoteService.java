package com.demo.vote.service;

import com.demo.vote.presentation.request.SubmitVoteRequest;
import com.demo.vote.presentation.response.SubmitVoteResponse;
import com.demo.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
git remote add origin https://github.com/xxx/repo.git
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public SubmitVoteResponse submitVote(SubmitVoteRequest request) {

        if (request.getPersonId() == null) {
            return new SubmitVoteResponse(false, "PersonID is required.");
        }

        if (request.getUserName() == null || request.getUserName().trim().isEmpty()) {
            return new SubmitVoteResponse(false, "UserName is required.");
        }

        if (request.getVoteItemId() == null) {
            return new SubmitVoteResponse(false, "VoteItemId is required.");
        }

        if (request.getVoteItemName() == null || request.getVoteItemName().trim().isEmpty()) {
            return new SubmitVoteResponse(false, "VoteItemName is required.");
        }

        try {
            voteRepository.submitVote(request);
            return new SubmitVoteResponse(true, "Vote submitted successfully.");
        } catch (Exception ex) {
            return new SubmitVoteResponse(false, ex.getMessage());
        }
    }
}