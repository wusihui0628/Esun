package com.demo.vote.repository;

import com.demo.vote.presentation.response.VoteItemResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public VoteItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VoteItemResponse> getVoteItems() {
        String sql = "EXEC sp_get_vote_items";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            VoteItemResponse item = new VoteItemResponse();
            item.setitemId(rs.getLong("ItemId"));
            item.setitemName(rs.getString("Items"));
            item.setVoteCnt(rs.getInt("Votes"));
            return item;
        });
    }
}