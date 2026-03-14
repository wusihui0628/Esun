<template>

<div>

<h3>Submit Vote</h3>

<input v-model="personId" placeholder="Person ID">

<input v-model="userName" placeholder="User Name">

<select v-model="voteItemId">

<option v-for="item in items" :value="item.itemId">

{{item.items}}

</option>

</select>

<button @click="submit">Vote</button>

</div>

</template>

<script>

import { submitVote } from "../api/voteApi"

export default{

props:["items"],

data(){
return{

personId:"",
userName:"",
voteItemId:""

}
},

methods:{

async submit(){

await submitVote({

personId:this.personId,
userName:this.userName,
voteItemId:this.voteItemId

})

this.$emit("vote-submitted")

}

}

}

</script>

<!-- submit vote form -->