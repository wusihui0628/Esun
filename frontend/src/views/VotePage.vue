<template>

<div>

<h2>Vote Items</h2>

<VoteTable :items="voteItems"/>

<VoteForm :items="voteItems" @vote-submitted="loadItems"/>

</div>

</template>

<script>

import { getVoteItems } from "../api/voteApi"
import VoteTable from "../components/VoteTable.vue"
import VoteForm from "../components/VoteForm.vue"

export default {

components:{
VoteTable,
VoteForm
},

data(){
return{
voteItems:[]
}
},

mounted(){
this.loadItems()
},

methods:{

async loadItems(){

this.voteItems = await getVoteItems()

}

}

}

</script>

<!-- load Vote page for displaying and submitting votes -->