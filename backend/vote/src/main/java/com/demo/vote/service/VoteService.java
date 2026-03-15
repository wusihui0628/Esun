package com.demo.vote.service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.vote.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Map<String, Object> castVote(Map<String, Object> request) {
        Map<String, Object> result = new HashMap<>();

        String userName = request.get("userName") == null
                ? ""
                : request.get("userName").toString().trim();

        Object voteItemIdsObj = request.get("voteItemIds");

        if (userName.isEmpty()) {
            result.put("success", false);
            result.put("message", "使用者名稱不可為空");
            return result;
        }

        if (!(voteItemIdsObj instanceof List<?> rawList) || rawList.isEmpty()) {
            result.put("success", false);
            result.put("message", "請至少選擇一個投票項目");
            return result;
        }

        if (voteRepository.hasUserVoted(userName)) {
            result.put("success", false);
            result.put("message", "此使用者已投過票");
            return result;
        }

        // 去重複，避免同一請求內選到重複項目
        Set<Integer> voteItemIds = new LinkedHashSet<>();
        try {
            for (Object obj : rawList) {
                if (obj != null && !obj.toString().trim().isEmpty()) {
                    voteItemIds.add(Integer.valueOf(obj.toString()));
                }
            }
        } catch (NumberFormatException e) {
            result.put("success", false);
            result.put("message", "投票項目格式錯誤");
            return result;
        }

        if (voteItemIds.isEmpty()) {
            result.put("success", false);
            result.put("message", "請至少選擇一個投票項目");
            return result;
        }

        // 先驗證所有選項都存在
        for (Integer voteItemId : voteItemIds) {
            if (!voteRepository.existsVoteItem(voteItemId)) {
                result.put("success", false);
                result.put("message", "投票項目不存在，ItemId：" + voteItemId);
                return result;
            }
        }

        Integer personId = voteRepository.getNextPersonId();

        int insertCount = 0;
        int updateCount = 0;

        for (Integer voteItemId : voteItemIds) {
            Map<String, Object> voteItem = voteRepository.findVoteItemById(voteItemId);
            String voteItemName = voteItem.get("Items").toString();

            insertCount += voteRepository.insertVoter(personId, userName, voteItemId, voteItemName);
            updateCount += voteRepository.increaseVoteCount(voteItemId);
        }

        boolean success = insertCount == voteItemIds.size() && updateCount == voteItemIds.size();

        result.put("success", success);
        result.put("message", success ? "投票成功" : "投票失敗");
        result.put("selectedCount", voteItemIds.size());

        return result;
    }
}
