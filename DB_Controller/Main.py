import requests

data_user = {
    "tag":"aaaaa",
    "nick":"asd",
    "email":"asd",
    "password":"pass",
}
data_challenge =  {
    "name":"aaaaa",
    "task":"bb",
    "tags":["aa", "bb"],
    "categories":[],
    "creator_id": "asdsaddsa"
}

data_comment = {
        "user_id": "user_id",
        "challenge_id": "challenge_id",
        "date":"2200:11:24 12:23:43",
        "reply_on_id":"reply_on_id"

    }

data_id_user = {
    "user_id":"-M8KPoCqrB1soPInZzOb"
}

data_id_challenge = {
    "challenge_id":"-s-9yxL"
}


data_user_update= {
    "user_id":"-M8KPoCqrB1soPInZzOb",
    "name":"a",
    "email":"b"
}


add_user_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_user'
get_all_users_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/get_all_users'
get_user_by_id_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/get_user_by_id'
update_user_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/update_user'

add_challenge_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge'
get_all_challenges_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/get_all_challenges'
get_challenge_by_id_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/get_challenge_by_id'
update_challenge_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/update_challenge'

add_comment_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_comment'
get_all_comments_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/get_all_comments'
get_comment_by_id_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/get_comment_by_id'
update_comment_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/update_comment'


add_user = "http://127.0.0.1:5000/add_user"
get_all_users = "http://127.0.0.1:5000/get_all_users"
update_user = "http://127.0.0.1:5000/update_user"
get_user_by_id = "http://127.0.0.1:5000/get_user_by_id"

add_challenge = "http://127.0.0.1:5000/add_challenge"
get_all_challenges = "http://127.0.0.1:5000/get_all_challenges"
get_challenge_by_id = "http://127.0.0.1:5000/get_challenge_by_id"
update_challenge = "http://127.0.0.1:5000/update_challenge"

add_comment = "http://127.0.0.1:5000/add_comment"
get_all_comments = "http://127.0.0.1:5000/get_all_comments"
get_comment_by_id = "http://127.0.0.1:5000/get_comment_by_id"
update_comment = "http://127.0.0.1:5000/update_comment"



r = requests.get(get_all_challenges, json=data_id_challenge)

f = {
    "category":"gds",
    "s":"s"
}

#r = requests.post("http://127.0.0.1:5000/add_category", json=f)

print(r.status_code)
print(r.reason)
print(r.json())
