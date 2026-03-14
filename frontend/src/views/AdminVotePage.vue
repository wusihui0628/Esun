<template>
  <div>
    <button @click="page='vote'">Vote Page</button>
    <button @click="page='admin'">Admin Page</button>

    <VotePage v-if="page==='vote'" />
    <AdminVotePage v-if="page==='admin'" />
  </div>
</template>