from flask import Flask
from flask import request


app = Flask(__name__)

@app.route("/add_user",methods=['POST'])
def add_user():
    # imports
    import firebase_admin
    import json
    import os
    from firebase_admin import db
    from firebase_admin import credentials
    from flask import jsonify
    # init firebase app

    from dotenv import load_dotenv
    dotenv_path = os.path.dirname(__file__) + "/cred.env"
    load_dotenv(dotenv_path)
    cred = credentials.Certificate(json.loads(os.getenv("FIREBASE_CRED")))
    fb = firebase_admin.initialize_app(cred, {'databaseURL': 'https://challengeup-49057.firebaseio.com'})

    # get all info from request
    name = request.get_json(silent=True)['name']
    email = request.get_json(silent=True)['email']

    # get reference to users table
    users_ref = db.reference('').child('users')

    # insert new
    new_user = users_ref.push({
        'name': name,
        'email': email

    })
    id = new_user.key

    firebase_admin.delete_app(fb)

    response = {
        "status": 200,
        "message": "user created",
        "id": id
    }
    return jsonify(response)

@app.route("/add_challenge",methods=['POST'])
def add_challenge():
    # imports
    import firebase_admin
    import json
    import os
    from firebase_admin import db
    from firebase_admin import credentials
    from flask import jsonify
    # init firebase app

    from dotenv import load_dotenv
    dotenv_path = os.path.dirname(__file__) + "/cred.env"
    load_dotenv(dotenv_path)
    cred = credentials.Certificate(json.loads(os.getenv("FIREBASE_CRED")))
    fb = firebase_admin.initialize_app(cred, {'databaseURL': 'https://challengeup-49057.firebaseio.com/'})

    #get all info from request
    name = request.get_json(silent=True)['name']
    task = request.get_json(silent=True)['task']
    likes = 0
    list_of_tags = request.get_json(silent=True)['tags']
    list_of_categories = request.get_json(silent=True)['categories']
    creator_id = request.get_json(silent=True)['creator_id']

    #get reference to challenges table
    challenges_refs = db.reference('').child('challenges')
    #insert new
    new_challenge = challenges_refs.push({
        'name': name,
        'task': task,
        'likes':0,
        'tags':list_of_tags,
        'categories':list_of_categories,
        'creator_id':creator_id
    })
    id = new_challenge.key

    firebase_admin.delete_app(fb)
    response = {
        "status":200,
        "message":"challenge created",
        "id":id
    }
    return jsonify(response)

@app.route("/add_comment",methods=['POST'])
def add_comment():
    # imports
    import firebase_admin
    import json
    import os
    from firebase_admin import db
    from firebase_admin import credentials
    from flask import jsonify
    # init firebase app

    from dotenv import load_dotenv
    dotenv_path = os.path.dirname(__file__) + "/cred.env"
    load_dotenv(dotenv_path)
    cred = credentials.Certificate(json.loads(os.getenv("FIREBASE_CRED")))
    fb = firebase_admin.initialize_app(cred, {'databaseURL': 'https://challengeup-49057.firebaseio.com'})

    # get all info from request
    user_id = request.get_json(silent=True)['user_id']
    challenge_id = request.get_json(silent=True)['challenge_id']
    likes = 0
    date = request.get_json(silent=True)['date']
    reply_on_id = request.get_json(silent=True)['reply_on_id']

    # get reference to users table
    comments_ref = db.reference('').child('comments')

    # insert new
    new_comment = comments_ref.push({
        'user_id': user_id,
        'challenge_id': challenge_id,
        "likes":likes,
        "date":date,
        "reply_on_id":reply_on_id

    })
    id = new_comment.key

    firebase_admin.delete_app(fb)

    response = {
        "status": 200,
        "message": "comment created",
        "id": id
    }
    return jsonify(response)

app.run()