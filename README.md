# ChallengeUp API

realized with HTTP requests<br/>
data in json format<br/>
return json<br/>

Add new:<br/>
	add new user:<br/>
		input data:<br/>
			method: "POST"<br/>
			url: https://us-central1-challengeup-49057.cloudfunctions.net/add_user<br>
			data: {<br/>
   				"name":"name",<br/>
    			"email":"email"<br/>
			}<br/>
		return:<br/>
			response = {<br/>
        		"status": 200,<br/>
        		"message": "user created",<br/>
        		"id": id_of_created_user<br/>
    		}<br/>
	add new challenge:<br/>
		input data:<br/>
			method: "POST"<br/>
			url: https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge<br/>
			data: {<br/>
				"name":"name",<br/>
   				"task":"task",<br/>
   				"tags":["tag", "tag"],<br/>
   				"categories":[],<br/>
   				"creator_id": "creator_id"<br/>
			}<br/>
		return:<br/>
			response = {<br/>
        		"status": 200,<br/>
        		"message": "user created",<br/>
        		"id": id_of_created_user<br/>
    		}<br/>

