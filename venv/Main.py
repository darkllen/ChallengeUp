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
site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_user'
site2 = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge'
add_user = "http://127.0.0.1:5000/add_user"
add_challenge = "http://127.0.0.1:5000/add_challenge"

r = requests.post(site2, json=data_challenge)
print (r.json())
