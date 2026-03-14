<template>
  <div>

    <h1>Voting System</h1>

    <button @click="page='vote'">Vote Page</button>
    <button @click="page='admin'">Admin Page</button>

    <VotePage v-if="page==='vote'" />
    <AdminVotePage v-if="page==='admin'" />

  </div>
</template>

<script>
import VotePage from './views/VotePage.vue'
import AdminVotePage from './views/AdminVotePage.vue'

export default {

  components:{
    VotePage,
    AdminVotePage
  },

  data(){
    return{
      page:'vote'
    }
  }

}
</script>

<!-- main page -->