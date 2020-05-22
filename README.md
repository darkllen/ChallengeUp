# ChallengeUp API<br>

realized with HTTP requests
data in json format
return json

Add new:
	add new user:
		input data:
			method: "POST"
			url: https://us-central1-challengeup-49057.cloudfunctions.net/add_user
			data: {
   				"name":"name",
    			"email":"email"
			}
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

