package com.demo.vote.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VoteItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public VoteItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAll() {
        String sql = """
                SELECT
                    ItemId,
                    Items,
                    Votes
                FROM Votes
                ORDER BY ItemId
                """;

        return jdbcTemplate.queryForList(sql);
    }

    public int create(String items) {
        String sql = """
                INSERT INTO Votes (ItemId, Items, Votes)
                VALUES (
                    (SELECT COALESCE(MAX(ItemId), 0) + 1 FROM Votes),
                    ?,
                    0
                )
                """;

        return jdbcTemplate.update(sql, items);
    }

    public int deleteById(Integer itemId) {
        String sql = """
                DELETE FROM Votes
                WHERE ItemId = ?
                """;

        return jdbcTemplate.update(sql, itemId);
    }

    public boolean existsById(Integer itemId) {
        String sql = """
                SELECT COUNT(1)
                FROM Votes
                WHERE ItemId = ?
                """;

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, itemId);
        return count != null && count > 0;
    }
}