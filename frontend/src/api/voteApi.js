const API = "http://localhost:8080"

export async function getVoteItems() {

  const response = await fetch(API + "/api/vote-items")

  return response.json()
}

export async function submitVote(voteData) {

  const response = await fetch(API + "/api/votes", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(voteData)
  })

  return response.json()
}

// connect to backend API, get vote items and submit votes