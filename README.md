# ChallengeUp API

realized with HTTP requests<br/>
data in json format<br/>
return json

[add new user](#add_new_user)


##### add_new_user
**input data:**<br/>
&nbsp;method: "POST"<br/>
&nbsp;url: https://us-central1-challengeup-49057.cloudfunctions.net/add_user<br/>
&nbsp;data: {<br/>
&nbsp;&nbsp;"name":"name",<br/>
&nbsp;&nbsp;"email":"email"<br/>
&nbsp;}<br/>
**return:**<br/>
&nbsp;response = {<br/>
&nbsp;&nbsp;"status": 200,<br/>
&nbsp;&nbsp;"message": "user created",<br/>
&nbsp;&nbsp;"id": id_of_created_user<br/>
&nbsp;}<br/>


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

