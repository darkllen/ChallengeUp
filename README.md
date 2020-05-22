# ChallengeUp API

realized with HTTP requests<br/>
data in json format<br/>
return json

Add new:
&nbsp;&nbsp;&nbsp;&nbsp;add new user:
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;input data:
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;method: "POST"
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;url: https://us-central1-challengeup-49057.cloudfunctions.net/add_user
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data: {
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name":"name",
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"email":"email"
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
		return:
			response = {
        		"status": 200,
        		"message": "user created",
        		"id": id_of_created_user
    		}
	add new challenge:
		input data:
			method: "POST"
			url: https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge
			data: {
				"name":"name",
   				"task":"task",
   				"tags":["tag", "tag"],
   				"categories":[],
   				"creator_id": "creator_id"
			}
		return:
			response = {
        		"status": 200,
        		"message": "user created",
        		"id": id_of_created_user
    		}

