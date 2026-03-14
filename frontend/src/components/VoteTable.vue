<template>

<table border="1">

<tr>
<th>ID</th>
<th>Item</th>
<th>Votes</th>
</tr>

<tr v-for="item in items" :key="item.itemId">

<td>{{item.itemId}}</td>
<td>{{item.items}}</td>
<td>{{item.votes}}</td>

</tr>

</table>

</template>

<script>

export default{

props:["items"]

}

</script>

<!-- display vote items in a table -->