package com.demo.vote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.vote.repository.VoteItemRepository;

@Service
public class VoteItemService {

    private final VoteItemRepository voteItemRepository;

    public VoteItemService(VoteItemRepository voteItemRepository) {
        this.voteItemRepository = voteItemRepository;
    }

    public Map<String, Object> getAllVoteItems() {
        List<Map<String, Object>> data = voteItemRepository.findAll();

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "查詢成功");
        result.put("data", data);
        return result;
    }

    public Map<String, Object> createVoteItem(Map<String, Object> request) {
        String items = request.get("items") == null ? "" : request.get("items").toString().trim();

        Map<String, Object> result = new HashMap<>();

        if (items.isEmpty()) {
            result.put("success", false);
            result.put("message", "items 不可為空");
            return result;
        }

        int rows = voteItemRepository.create(items);

        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "新增成功" : "新增失敗");
        return result;
    }

    public Map<String, Object> deleteVoteItem(Integer itemId) {
        Map<String, Object> result = new HashMap<>();

        if (!voteItemRepository.existsById(itemId)) {
            result.put("success", false);
            result.put("message", "查無此 ItemId");
            return result;
        }

        voteItemRepository.deleteVoterRecordsByVoteItemId(itemId);
        int deleteVoteItemRows = voteItemRepository.deleteVoteItemById(itemId);

        result.put("success", deleteVoteItemRows > 0);
        result.put("message", deleteVoteItemRows > 0 ? "刪除成功（含投票紀錄）" : "刪除失敗");

        return result;
    }
}