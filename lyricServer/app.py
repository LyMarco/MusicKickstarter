"""
This script runs the application using a development server.
It contains the definition of routes and views for the application.
"""

from flask import Flask, jsonify
from suggestions import getSuggestions, findRhymes
app = Flask(__name__)

# Make the WSGI interface available at the top level so wfastcgi can get it.
wsgi_app = app.wsgi_app


@app.route('/')
def hello():
    """Renders a sample page."""
    return "Hello World!"

@app.route('/suggestions/<word>')
def suggestionsEndpoint(word):
    print("GET SUGGESTIONS")
    print (word)
    result = getSuggestions(word)
    return jsonify(result)

@app.route('/rhymes/<word>')
def rhymesEndpoint(word):
    print("GET RHYMES ONLY")
    print (word)
    result = findRhymes(word)
    return jsonify(result)

if __name__ == '__main__':
    import os
    HOST = os.environ.get('SERVER_HOST', 'localhost')
    try:
        PORT = int(os.environ.get('SERVER_PORT', '5555'))
    except ValueError:
        PORT = 5555
    app.run(HOST, PORT)
