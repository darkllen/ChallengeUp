# ChallengeUp API


## [Java API](#Java_API)


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
**Fields:**<br/>
Modifier|Type|Name|Description|Has getter|Has setter
--------|------|-----|-------|--------|-----------
private|String|
private|String|
private|String|
private|String|
private|int|
private|int|
private|ArrayList<String>|
private|ArrayList<String>|

**Constructors:** <br/>
Constructor | Description
**Static methods:** <br/>
Return | Method | Description
**Methods** <br/>
Return | Method | Description
### Utilities
**Static methods:** <br/>
Return | Method | Description

