# ChallengeUp API

## [Cloud Functions](#Cloud_Functions)

## [Java API](#Java_API)


# Cloud_Functions

realized with HTTP requests<br/>
data in json format<br/>
return json

### add new:
[add new user](#add_new_user)<br/>
[add new challenge](#add_new_challenge)


## add_new_user
**input data:**<br/>
&nbsp;&nbsp;**method:** "POST"<br/>
&nbsp;&nbsp;**url:** https://us-central1-challengeup-49057.cloudfunctions.net/add_user<br/>
&nbsp;&nbsp;**data:** {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"name":"name",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"email":"email"<br/>
&nbsp;&nbsp;}<br/>
**return:**<br/>
&nbsp;&nbsp;**response:** {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"status": 200,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"message": "user created",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"id": id_of_created_user<br/>
&nbsp;&nbsp;}<br/>


## add_new_challenge
**input data:**<br/>
&nbsp;&nbsp;**method:** "POST"<br/>
&nbsp;&nbsp;**url:** https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge<br/>
&nbsp;&nbsp;**data:** {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"name":"name",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"task":"task",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"tags":["tag", "tag"],<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"categories":[],<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"creator_id": "creator_id"<br/>
&nbsp;&nbsp;}<br/>
**return:**<br/>
&nbsp;&nbsp;**response:** {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"status": 200,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"message": "user created",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"id": id_of_created_user<br/>
&nbsp;&nbsp;}<br/>


# Java_API
asd
