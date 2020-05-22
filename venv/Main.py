import requests

data = {
    "name":"aaaaa",
    "age":"asd"
}
site = 'https://us-central1-challengeup-49057.cloudfunctions.net/add_user/'
local = "http://127.0.0.1:5000/"
r = requests.post(local, data)
