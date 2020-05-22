from flask import Flask
from flask import request
app = Flask(__name__)

@app.route("/",methods=['POST'])
def hello_world():
    import firebase_admin
    from firebase_admin import db
    from firebase_admin import credentials

    name = request.form['name']
    age = request.form['age']

    cred = {
        "type": "service_account",
        "project_id": "challengeup-49057",
        "private_key_id": "515971ee6685815173ae55ce4305907592bbc810",
        "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCnB22ICY+LYdRp\n/cGpb4Wo1O8WQyLN9OEcsKfqZkmYl7d+noUzTebjBS8TOUZz2sLBpuJKhywIdaZC\nvrG9qYwKejn57/m4Gtwx67e56keL6rUlrZFHWjzLaDD2myFsPcKJc8xHW7jid95J\nbMh6iZqoukl3ca+zq/yW0+aD5XVnzcAtTk/habcQ+Dda+IKxYHB1ig1H3PloGXWb\nCOoH55j6a6WiP9DQaKhP52786WXtEfGcE1rsM5uS3OOPf2lkme4yktqOmYBkt5Tu\n3rxHY5Mypfy9zn3VujiqSJ/zY5pe1UwgcC0GOrXzu90KUnqs4bLj3R2dOt5XciI7\nxy/bYtb/AgMBAAECggEAKHICgl36TeCHHJFPW1V/cpX5PRVNsLya5CUczAQvzwzx\n6G2/SGCVR6TCNO/6EOTIozbjdfOMZJIEjAX8KTaMjB7zugNznzKV3lq7VwAVihpv\n+esxEiuWLBUTQivnaHv+BuWs8hySRPiMsY/vbcizVCcj0hIL30CGvgkauRaPmu5R\nxAJEM0xbbjnp8g4ae+WlWQmZ8YO/2n5bgBzNjzeEstIBRzQ/OVJ6ZVs7/TnXPLav\n44Esb0rGgHg5IOCCXzyD3MlcTSTJWxLQeUpjvpk79bVPcD23ilcQh5qOuGskymda\nzUuT27ZAuC8bet3vBdI5sPzvryhZWlPsn+fqvKNHBQKBgQDb/YMz53pHCq62I2RS\nCOzGBvKUyYGjYgtYCvGcxYmIsbOp17HgC+rqpIwcTkSSAHc1E99JJRRVCjhaYPHl\nb+ycI1qvwkUdoRQ69cZbADF8NzWjLzcGPV1LEf7XQkpT/MTo6AFy+IklgSDh4HiO\nzHurlyUrRzsydfInzpRlVEZaXQKBgQDCXp9yID0hskSx3MPwW4jNXrojQrL5EdHZ\nF8bgBBL49d0XuDyVApH9ldfaZ+S94SsatH/ry+0Nnhw25KCs/ZNOibPtXGpZVFoI\nXqltLOKqP4zR11vS/ujjQogHzIQ7YWyqOJymoFwuUgGbhyIrHcRhwxkYo4rrQkr+\nrhIjddN5CwKBgQC+z2jZwFEjBXT8rj35mZwlNyl5PD6hhAzq1LM0k3Hzio6XJms2\nNaAQ3aBZfNmk+ZpsA0yMmQjFqhOu8J9frnDADTo/cHWaeXiem0bs74H8h7aSUAWd\n9+PPlVBBTVJC/t+cVH/NRR1P1my6/oQS9/Hcp0CqBSKnZf4f4OJ98VcEUQKBgAOu\n+2KUTdRHppmgT25MrckGlIx6hR3HQqzUkIZKTXm+XxRF/4/rXozpvA99jI40qDZ3\nWT1EvhdV+b7okkr0+0evwGMtLy7GNHaYJKnXAeysC6Umaa0T+6Hi7uUG8TKW5BJN\nxFo8C8Ms9q7V3sXmaIRZyC35W3ad6QHulpVpdDqFAoGBAIXGQQJXmvmiyb3QzYyE\nfJDPmymib0Hcw9ph7pUHIpqJcYI/vYRsZQYFDopIAdvH6Lc3D7pjdT3fyngkazh0\nXnL9j0dD0ZT8Oec45py6+LFGCDAx1vNHZZHTQBaYdRaGzsHJJqq279SFZbrpI6pI\nJjzab40qpBXIvlO8X9H3lv0a\n-----END PRIVATE KEY-----\n",
        "client_email": "firebase-adminsdk-637yd@challengeup-49057.iam.gserviceaccount.com",
        "client_id": "109835793352000854773",
        "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        "token_uri": "https://oauth2.googleapis.com/token",
        "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-637yd%40challengeup-49057.iam.gserviceaccount.com"
    }
    cred = credentials.Certificate(cred)
    fb = firebase_admin.initialize_app(cred, {'databaseURL': 'https://challengeup-49057.firebaseio.com/'})


    ref = db.reference('')
    users_ref = ref.child('users')
    users_ref.push({
        'name': name,
        'age': age

    })
    firebase_admin.delete_app(fb)
    return "asd"

app.run()