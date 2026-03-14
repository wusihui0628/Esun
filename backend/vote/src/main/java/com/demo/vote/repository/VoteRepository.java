package com.demo.vote.repository;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VoteRepository {

    private final JdbcTemplate jdbcTemplate;

    public VoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> findVoteItemById(Integer voteItemId) {
        String sql = """
                SELECT ItemId, Items, Votes
                FROM Votes
                WHERE ItemId = ?
                """;

        return jdbcTemplate.queryForMap(sql, voteItemId);
    }

    public boolean existsVoteItem(Integer voteItemId) {
        String sql = """
                SELECT COUNT(1)
                FROM Votes
                WHERE ItemId = ?
                """;

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, voteItemId);
        return count != null && count > 0;
    }

    public int getNextPersonId() {
        String sql = """
                SELECT COALESCE(MAX(PersonID), 0) + 1
                FROM Voters
                """;

        Integer nextId = jdbcTemplate.queryForObject(sql, Integer.class);
        return nextId == null ? 1 : nextId;
    }

    public int insertVoter(Integer personId, String userName, Integer voteItemId, String voteItemName) {
        String sql = """
                INSERT INTO Voters (PersonID, UserName, VoteItemId, VoteItemName)
                VALUES (?, ?, ?, ?)
                """;

        return jdbcTemplate.update(sql, personId, userName, voteItemId, voteItemName);
    }

    public int increaseVoteCount(Integer voteItemId) {
        String sql = """
                UPDATE Votes
                SET Votes = Votes + 1
                WHERE ItemId = ?
                """;

        return jdbcTemplate.update(sql, voteItemId);
    }

    public boolean hasUserVoted(String userName) {
        String sql = """
                SELECT COUNT(1)
                FROM Voters
                WHERE UserName = ?
                """;

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        return count != null && count > 0;
    }
}
