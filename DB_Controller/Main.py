import requests

data_user = {
    "name":"aaaaa",
    "email":"asd"
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


add_user_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_user'
add_challenge_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge'
add_comment_site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_comment'
add_user = "http://127.0.0.1:5000/add_user"
add_challenge = "http://127.0.0.1:5000/add_challenge"
add_comment = "http://127.0.0.1:5000/add_comment"

r = requests.post(add_comment_site, json=data_comment)
print(r.json())
