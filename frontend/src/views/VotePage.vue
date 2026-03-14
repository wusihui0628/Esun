<template>
  <div>
    <h2>投票頁面</h2>

    <div style="margin-bottom: 12px;">
      <label>使用者名稱：</label>
      <input v-model="userName" placeholder="請輸入使用者名稱" />
    </div>

    <div style="margin-bottom: 12px;">
      <label>請選擇投票項目：</label>
      <select v-model="selectedItemId">
        <option value="">請選擇</option>
        <option
          v-for="item in voteItems"
          :key="item.ItemId"
          :value="item.ItemId"
        >
          {{ item.Items }}（目前 {{ item.Votes }} 票）
        </option>
      </select>
    </div>

    <div style="margin-bottom: 12px;">
      <button @click="submitVote">送出投票</button>
    </div>

    <hr />

    <h3>目前投票項目</h3>
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
const selectedItemId = ref("");

const loadVoteItems = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/vote-items");
    const result = await response.json();

    if (result.success) {
      voteItems.value = result.data;
    } else {
      alert(result.message || "查詢失敗");
    }
  } catch (error) {
    console.error(error);
    alert("查詢失敗");
  }
};

const submitVote = async () => {
  const name = userName.value.trim();
  const itemId = selectedItemId.value;

  if (!name) {
    alert("請輸入使用者名稱");
    return;
  }

  if (!itemId) {
    alert("請選擇投票項目");
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/api/votes/cast", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        userName: name,
        voteItemId: itemId
      })
    });

    const result = await response.json();

    if (result.success) {
      alert("投票成功");
      userName.value = "";
      selectedItemId.value = "";
      await loadVoteItems();
    } else {
      alert(result.message || "投票失敗");
    }
  } catch (error) {
    console.error(error);
    alert("投票失敗");
  }
};

onMounted(() => {
  loadVoteItems();
});
</script>
