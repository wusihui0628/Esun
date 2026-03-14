package com.demo.vote.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.vote.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Map<String, Object> castVote(Map<String, Object> request) {
        Map<String, Object> result = new HashMap<>();

        String userName = request.get("userName") == null ? "" : request.get("userName").toString().trim();
        Object voteItemIdObj = request.get("voteItemId");

        if (userName.isEmpty()) {
            result.put("success", false);
            result.put("message", "使用者名稱不可為空");
            return result;
        }

        if (voteItemIdObj == null || voteItemIdObj.toString().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "請選擇投票項目");
            return result;
        }

        Integer voteItemId = Integer.valueOf(voteItemIdObj.toString());

        if (!voteRepository.existsVoteItem(voteItemId)) {
            result.put("success", false);
            result.put("message", "投票項目不存在");
            return result;
        }

        if (voteRepository.hasUserVoted(userName)) {
            result.put("success", false);
            result.put("message", "此使用者已投過票");
            return result;
        }

        Map<String, Object> voteItem = voteRepository.findVoteItemById(voteItemId);
        String voteItemName = voteItem.get("Items").toString();

        Integer personId = voteRepository.getNextPersonId();

        int insertRows = voteRepository.insertVoter(personId, userName, voteItemId, voteItemName);
        int updateRows = voteRepository.increaseVoteCount(voteItemId);

        result.put("success", insertRows > 0 && updateRows > 0);
        result.put("message", insertRows > 0 && updateRows > 0 ? "投票成功" : "投票失敗");

        return result;
    }
}
