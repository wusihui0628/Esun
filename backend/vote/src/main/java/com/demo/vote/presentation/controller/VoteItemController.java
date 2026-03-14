package com.demo.vote.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.vote.repository.VoteItemRepository;

@RestController
@RequestMapping("/api/vote-items")
@CrossOrigin
public class VoteItemController {

    private final VoteItemRepository voteItemRepository;

    public VoteItemController(VoteItemRepository voteItemRepository) {
        this.voteItemRepository = voteItemRepository;
    }

    @GetMapping
    public Map<String, Object> getAll() {
        List<Map<String, Object>> data = voteItemRepository.findAll();

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "查詢成功");
        result.put("data", data);
        return result;
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> request) {
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

    @DeleteMapping("/{itemId}")
    public Map<String, Object> delete(@PathVariable Integer itemId) {
        Map<String, Object> result = new HashMap<>();

        if (!voteItemRepository.existsById(itemId)) {
            result.put("success", false);
            result.put("message", "查無此 ItemId");
            return result;
        }

        int rows = voteItemRepository.deleteById(itemId);

        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "刪除成功" : "刪除失敗");
        return result;
    }
}