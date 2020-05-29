# ChallengeUp API


## [Java API](#Java_API)
## [Cloud Functions](#Cloud_Functions)

# Java_API

**classes:**<br/>
[User](#User)<br/>
[Challenge](#Challenge)<br/>
[Utilities](#Utilities)<br/>

### User
**Fields:**<br/>
Modifier|Type|Name|Description|Has getter|Has setter
--------|------|-----|-------|--------|-----------
private|String|id|user id|+|-
private|String|tag|unique tag|+|+
private|String|nick|user name|+|+
private|String|email|user email|+|+
private|String|password|user password|+|+
private|ArrayList<String>|categories|favourite categories|+|+
private|ArrayList<String>|subscriptions|ids of user`s subscriptions|+|+
private|ArrayList<String>|undone|ids of user`s undone challenges|+|+
private|ArrayList<String>|done|ids of user`s done challenges|+|+

**Constructors:** <br/>
Constructor | Description
-------------|-------------
User(String tag, String nick, String email, String password) | create User, id == null until addNewUser(User user) is used
User(String tag, String nick, String email, String password, ArrayList<String> categories) | create User, id == null until addNewUser(User user) is used
  
**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
String|id of new User in db|addNewUser(User user)|add User to db with all of it parameters, change id of user to relevant
String|id of new User in db|addNewUser(String tag, String nick, String email, String password, ArrayList<String> categories)|creaate User in db.
ArrayList<User>|all users|getAllUsers()|get all users from db
User|user with certain id or null if there is no user with id|getUserById(String id)|get User with id from db 

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite User params in db with this object
ArrayList<Challenge>|all done challenges of this user|getDoneChallenges()|get all done challenges
ArrayList<Challenge>|all undone challenges of this user|getUnDoneChallenges()|get all undone challenges
ArrayList<Challenge>|all created challenges of this user|getAllCreatedChallenges()|get all created challenges
ArrayList<User>|all subscriptions of this user|getSubscriptionsAsUsers()|get all subscriptions
ArrayList<User>|all subscribers of this user|getSubscribersAsUsers()|get all subscribers

### Challenge
**Constructors:** <br/>
Constructor | Description
**Static methods:** <br/>
Return | Method | Description
**Methods** <br/>
Return | Method | Description
### Utilities
**Static methods:** <br/>
Return | Method | Description

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
