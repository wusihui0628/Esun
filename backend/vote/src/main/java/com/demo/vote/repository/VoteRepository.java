package com.demo.vote.repository;

import com.demo.vote.presentation.request.SubmitVoteRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VoteRepository {

    private final JdbcTemplate jdbcTemplate;

    public VoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void submitVote(SubmitVoteRequest request) {

        String updateVoteCountSql = """
                UPDATE Votes
                SET Votes = Votes + 1
                WHERE ItemId = ?
                """;

        String insertVoterSql = """
                INSERT INTO Voters (PersonID, UserName, VoteItemId, VoteItemName)
                VALUES (?, ?, ?, ?)
                """;

        int updatedRows = jdbcTemplate.update(updateVoteCountSql, request.getVoteItemId());

        if (updatedRows == 0) {
            throw new RuntimeException("Vote item not found.");
        }

        jdbcTemplate.update(
                insertVoterSql,
                request.getPersonId(),
                request.getUserName(),
                request.getVoteItemId(),
                request.getVoteItemName()
        );
    }
}