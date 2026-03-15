<template>
  <div class="vote-page">
    <h2>投票頁面</h2>

    <div class="form-block">
      <label for="userName">使用者名稱：</label>
      <input
        id="userName"
        v-model="userName"
        type="text"
        placeholder="請輸入使用者名稱"
      />
    </div>

    <div class="form-block">
      <div class="label-title">請選擇投票項目（可多選）：</div>

      <div v-if="voteItems.length > 0" class="checkbox-list">
        <label
          v-for="item in voteItems"
          :key="item.ItemId"
          class="checkbox-item"
        >
          <input
            v-model="selectedItemIds"
            type="checkbox"
            :value="item.ItemId"
          />
          <span>{{ item.Items }}（目前 {{ item.Votes }} 票）</span>
        </label>
      </div>

      <div v-else class="empty-text">
        目前沒有可投票的項目
      </div>
    </div>

    <div class="form-block">
      <button @click="submitVote" :disabled="isSubmitting">
        {{ isSubmitting ? "送出中..." : "送出投票" }}
      </button>
    </div>

    <hr />

    <h3>目前投票結果</h3>

    <table border="1" cellpadding="8" cellspacing="0">
      <thead>
        <tr>
          <th>ItemId</th>
          <th>Items</th>
          <th>Votes</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in voteItems" :key="item.ItemId">
          <td>{{ item.ItemId }}</td>
          <td>{{ item.Items }}</td>
          <td>{{ item.Votes }}</td>
        </tr>
        <tr v-if="voteItems.length === 0">
          <td colspan="3">目前沒有資料</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const voteItems = ref([]);
const userName = ref("");
const selectedItemIds = ref([]);
const isSubmitting = ref(false);

const loadVoteItems = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/vote-items");
    const result = await response.json();

    console.log("loadVoteItems result =", result);

    if (result.success) {
      voteItems.value = result.data || [];
    } else {
      alert(result.message || "查詢投票項目失敗");
    }
  } catch (error) {
    console.error("loadVoteItems error:", error);
    alert("查詢投票項目失敗");
  }
};

const submitVote = async () => {
  const name = userName.value.trim();

  if (!name) {
    alert("請輸入使用者名稱");
    return;
  }

  if (!selectedItemIds.value || selectedItemIds.value.length === 0) {
    alert("請至少勾選一個投票項目");
    return;
  }

  isSubmitting.value = true;

  try {
    const payload = {
      userName: name,
      voteItemIds: selectedItemIds.value
    };

    console.log("submitVote payload =", payload);

    const response = await fetch("http://localhost:8080/api/votes/cast", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(payload)
    });

    const result = await response.json();

    console.log("submitVote result =", result);

    if (result.success) {
      alert(result.message || "投票成功");
      userName.value = "";
      selectedItemIds.value = [];
      await loadVoteItems();
    } else {
      alert(result.message || "投票失敗");
    }
  } catch (error) {
    console.error("submitVote error:", error);
    alert("投票失敗：" + error.message);
  } finally {
    isSubmitting.value = false;
  }
};

onMounted(() => {
  loadVoteItems();
});
</script>

<style scoped>
.vote-page {
  padding: 16px;
}

.form-block {
  margin-bottom: 16px;
}

.label-title {
  margin-bottom: 8px;
  font-weight: bold;
}

.checkbox-list {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.checkbox-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.empty-text {
  color: gray;
}
</style>