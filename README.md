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
private|ArrayList of Strings|categories|favourite categories|+|+
private|ArrayList of Strings|subscriptions|ids of user`s subscriptions|+|+
private|ArrayList of Strings|undone|ids of user`s undone challenges|+|+
private|ArrayList of Strings|done|ids of user`s done challenges|+|+

**Constructors:** <br/>
Constructor | Description
-------------|-------------
User(String tag, String nick, String email, String password) | create User, id == null until addNewUser(User user) is used
User(String tag, String nick, String email, String password, ArrayList of Strings categories) | create User, id == null until addNewUser(User user) is used
  
**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
String|id of new User in db|addNewUser(User user)|add User to db with all of it parameters, change id of user to relevant
String|id of new User in db|addNewUser(String tag, String nick, String email, String password, ArrayList of Strings categories)|creaate User in db.
ArrayList of Users|all users|getAllUsers()|get all users from db
User|user with certain id or null if there is no user with id|getUserById(String id)|get User with id from db 

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite User params in db with this object
ArrayList of Challenges|all done challenges of this user|getDoneChallenges()|get all done challenges
ArrayList of Challenges|all undone challenges of this user|getUnDoneChallenges()|get all undone challenges
ArrayList of Challenges|all created challenges of this user|getAllCreatedChallenges()|get all created challenges
ArrayList of Users|all subscriptions of this user|getSubscriptionsAsUsers()|get all subscriptions
ArrayList of Users|all subscribers of this user|getSubscribersAsUsers()|get all subscribers

### Challenge
**Fields:**<br/>

Modifier|Type|Name|Description|Has getter|Has setter
--------|------|-----|-------|--------|-----------
private|String|id|challenge id|+|-
private|String|name|challenge name|+|+
private|String|task|challenge description|+|+
private|String|creator_id|id of user, who creates challenge|+|+
private|int|likes|likes number|+|+
private|int|timesViewed|how many times, challenge was visited|+|+
private|ArrayList of Strings|tags|challenge tags|+|+
private|ArrayList of Strings|categories|challenge categories|+|+

**Constructors:** <br/>

Constructor | Description
----------|-------------
Challenge(String name, String task, String creator_id)|create challenge, id == null
Challenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList<String> categories)|create challenge, id == null

**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
String|id of new Challenge in db|addNewChallenge(Challenge challenge)|add Challenge to db with all of it parameters, change id of challenge to relevant
String|id of new Challenge in db|addNewChallenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList<String> categories)|add Challenge to db with all of it parameters, change id of challenge to relevant
ArrayList of Challenges|all challenge|getAllChallenges()|get all challenges from db
Challenge|challenge with certain id or null if there is no challenge with id|getChallengeById(String id)|get Challenge with id from db 
ArrayList of Challenges|all challenges with this category|getAllWithCategory(String category)|get all challenges with certain category
ArrayList of Challenges|all challenges with this tag|getAllWithTag(String tag)|get all challenges with certain tag
ArrayList of Challenges|all challenges with these categories|getAllWithCategories(ArrayList of Strings categories)|get all challenges with certain categories
ArrayList of Challenges|all challenges with these tags|getAllWithTags(ArrayList of Strings tags)|get all challenges with certain tags

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite Challenge params in db with this object
ArrayList of Comments|all comments belong to this challenge| getAllComments()| get all comments of this challenge

### Utilities
**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
ArrayList of Strings|all categories|getCategories()|get categories from db

### Comment
**Fields:**<br/>

Modifier|Type|Name|Description|Has getter|Has setter
--------|------|-----|-------|--------|-----------
private|String|id|comment id|+|-
private|String|message|comment text|+|-
private|String|user_id|id of comment creator|+|-
private|String|challenge_id|id of challenge, wich is commented|+|-
private|int|likes|number og likes|+|+
private|String|date|date of writing comments|+|-
private|String|reply_on_id|id of comment, on which this comment is a reply, "" if it isn`t a reply|+|-

**Constructors:** <br/>
Constructor | Description
-------------|-------------
Comment(String message, String user_id, String challenge_id, String date)| id is null untill object is added to db, reply_on_id - "".
Comment(String message, String user_id, String challenge_id, String date, String reply_on_id)|id is null untill object is added to db

**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
String|id of new Comment in db|addNewComment(Comment comment)|add Comment to db with all of it parameters, change id of comment to relevant
String|id of new Comment in db|addNewComment(String message, String user_id, String challenge_id, String date, String reply_on_id)|creaate Comment in db.
ArrayList of Comments|all comments|getAllComments()|get all comments from db

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite Comment params in db with this object


