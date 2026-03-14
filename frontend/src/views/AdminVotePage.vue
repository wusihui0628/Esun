<template>
  <div>
    <h1>投票項目管理</h1>

    <div style="margin-bottom: 16px;">
      <input v-model="newItem" placeholder="請輸入投票項目" />
      <button @click="addItem">新增</button>
    </div>

    <table border="1" cellpadding="8" cellspacing="0">
      <thead>
        <tr>
          <th>ItemId</th>
          <th>Items</th>
          <th>Votes</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in voteItems" :key="item.ItemId">
          <td>{{ item.ItemId }}</td>
          <td>{{ item.Items }}</td>
          <td>{{ item.Votes }}</td>
          <td>
            <button @click="deleteItem(item.ItemId)">刪除</button>
          </td>
        </tr>
        <tr v-if="voteItems.length === 0">
          <td colspan="4">目前沒有資料</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const voteItems = ref([]);
const newItem = ref("");

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

const addItem = async () => {
  const items = newItem.value.trim();

  if (!items) {
    alert("請輸入投票項目");
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/api/vote-items", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ items })
    });

    const result = await response.json();

    if (result.success) {
      newItem.value = "";
      await loadVoteItems();
    } else {
      alert(result.message || "新增失敗");
    }
  } catch (error) {
    console.error(error);
    alert("新增失敗");
  }
};

const deleteItem = async (itemId) => {
  try {
    const response = await fetch(`http://localhost:8080/api/vote-items/${itemId}`, {
      method: "DELETE"
    });

    const result = await response.json();

    if (result.success) {
      await loadVoteItems();
    } else {
      alert(result.message || "刪除失敗");
    }
  } catch (error) {
    console.error(error);
    alert("刪除失敗");
  }
};

onMounted(() => {
  loadVoteItems();
});
</script>
