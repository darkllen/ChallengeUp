# ChallengeUp API


## [Java API](#Java_API)


# Java_API

**classes:**<br/>
[User](#User)<br/>
[Challenge](#Challenge)<br/>
[Comment](#Comment)<br/>
[Utilities](#Utilities)<br/>

if there is needed to pass arrayList, but you don\`t have it, you can pass new ArrayList() and it will be ok

### User
**Fields:**<br/>

Modifier|Type|Name|Description|Has getter|Has setter
--------|------|-----|-------|--------|-----------
private|String|id|user id|+|-
private|String|tag|unique tag|+|+
private|String|nick|user name|+|+
private|String|email|user email|+|+
private|String|password|user password|+|+
private|int|rp|current user reputation|+|+
private|it|totalRp|all user reputation|+|+
private|ArrayList\<String\>|categories|favourite categories|+|+
private|ArrayList\<String\>|subscriptions|ids of user\`s subscriptions|+|+
private|ArrayList\<String\>|undone|ids of user\`s undone challenges|+|+
private|ArrayList\<String\>|done|ids of user\`s done challenges|+|+
private|ArrayList\<String\>|saved|user\`s saved challenges|+|+
private|ArrayList\<String\>|trophies|ids of user trophies|+|+
private|HashMap\<String, String\>|links| links on other social networks (fb, insta, youtube)|-|+
private|File|photo|user avatar, for now can be setted, but can\`t be got from bd|+|+ 

**Constructors:** <br/>
Constructor | Description
-------------|-------------
User(String tag, String nick, String email, String password) | create User, id == null until addNewUser(User user) is used, all arrayLists empty, rp and totalRp == 0, links have 3 keys and empty values, have validation (tag, nick, password, email and ckeck for tag to be unique)
User(String tag, String nick, String email, String password, ArrayList of Strings categories) | create User, id == null until addNewUser(User user) is used, all arrayLists empty except categories, rp and totalRp == 0, links have 3 keys and empty values, have validation (tag, nick, password, email and ckeck for tag to be unique)
  
**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
String|id of new User in db|addNewUser(User user)|add User to db with all of it parameters, change id of user to relevant
String|id of new User in db|addNewUser(String tag, String nick, String email, String password, ArrayList of Strings categories)|creaate User in db, all other fields used as in user constructor, has validation, same as in constructor.
ArrayList of Users|all users|getAllUsers()|get all users from db
User|user with certain id or null if there is no user with id|getUserById(String id)|get User with id from db 

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite User params in db with this object
ArrayList\<Challenge\>|all done challenges of this user|getDoneChallenges()|get all done challenges
ArrayList\<Challenge\>|all undone challenges of this user|getUnDoneChallenges()|get all undone challenges
ArrayList\<Challenge\>|all created challenges of this user|getAllCreatedChallenges()|get all created challenges
ArrayList\<Challenge\>|all saved challenges of this user|getSavedChallenges()|get all saved challenges
ArrayList\<User\>|all subscriptions of this user|getSubscriptionsAsUsers()|get all subscriptions
ArrayList\<User\>|all subscribers of this user|getSubscribersAsUsers()|get all subscribers
ArrayList\<Trophy\>|all trophies of user|getAchievementsAsTrophies()|get all user trophies
void||setFacebookLink(String link)|set facebook link
void||setInstagramLink(String link)|set instagram link
void||setYoutubeLink(String link)|set youtube link

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
private|int|rewardRp|number of rp user get by completing this challenge|+|+
private|ArrayList\<String\>|tags|challenge tags|+|+
private|ArrayList\<String\>||categories|challenge categories|+|+
private|ArrayList\<String\>|rewardTrophies|ids of trophies that user get by completing this challenge|


**Constructors:** <br/>

Constructor | Description
----------|-------------
Challenge(String name, String task, String creator_id)|create challenge, id == null, all arrayLists empty, likes, timesViewed and rewardRp == 0. Has name, task validation
Challenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList<String> categories)|create challenge, id == null, all arrayLists empty, likes, timesViewed and rewardRp == 0. Has name, task validation

**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
String|id of new Challenge in db|addNewChallenge(Challenge challenge)|add Challenge to db with all of it parameters, change id of challenge to relevant
String|id of new Challenge in db|addNewChallenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList\<String\> categories)|add Challenge to db with all of it parameters, change id of challenge to relevant, validation as in constructor
ArrayList\<Challenge\>|all challenge|getAllChallenges()|get all challenges from db
Challenge|challenge with certain id or null if there is no challenge with id|getChallengeById(String id)|get Challenge with id from db 
ArrayList\<Challenge\>|all challenges with this category|getAllWithCategory(String category)|get all challenges with certain category
ArrayList\<Challenge\>|all challenges with this tag|getAllWithTag(String tag)|get all challenges with certain tag
ArrayList\<Challenge\>|all challenges with these categories|getAllWithCategories(ArrayList of Strings categories)|get all challenges with certain categories
ArrayList\<Challenge\>|all challenges with these tags|getAllWithTags(ArrayList of Strings tags)|get all challenges with certain tags

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite Challenge params in db with this object
ArrayList\<Comment\>|all comments belong to this challenge| getAllComments()| get all comments of this challenge
long|number of people who accept this challenge|numberOfPeopleWhoAccepted()|get number of people accepted challenge
long|number of people who complete this challenge|numberOfPeopleWhoComplete()|get number of people completed challenge
long|number of people who accept+complete this challenge|numberOfPeopleWhoCompleteAndAccepted()|get number of people accepted and completed challenge

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
private|String|reply_on_id|id of comment, on which this comment is a reply, "" if it isn\`t a reply|+|-

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
ArrayList\<Comment\>|all comments|getAllComments()|get all comments from db

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite Comment params in db with this object


### Trophy
**Fields:**<br/>

Modifier|Type|Name|Description|Has getter|Has setter
--------|------|-----|-------|--------|-----------
private|String|id|comment id|+|-
private|String|name|trophy name|+|+
private|String|description|trophy description|+|+

**Constructors:** <br/>

Constructor | Description
-------------|-------------
Trophy(String name, String description)| id is null untill object is added to db. has name, description validation

**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
String|id of new Trophy in db|addNewTrophy(Trophy trophy)add Trophy to db with all of it parameters, change id of trophy to relevant
String|id of new Trophy in db|addNewTrophy(String name, String description)|creaate Trophy in db. validation as in constructor
ArrayList\<Trophy\>|all trophies|getAllTrophies()|get all trophies from db
Trophy|trophy with certain id|getTrophyById(String id)|get trophy with id

**Methods** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
void||update()|rewrite Trophy params in db with this object
ArrayList\<User\>|users with this trophy|getUsersWithThisTrophy()|get all users with this trophy in trophies


### Utilities
**Static methods:** <br/>

Return|Return description|Method|Method Description
-------------|-------------|-------------|-------------
ArrayList\<String\>|all categories|getCategories()|get categories from db
boolean|if successfull|addNewCategory(String category)|add new category in db



