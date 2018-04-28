from flask import Flask, request
import pika

app = Flask(__name__)
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.queue_declare(queue='weather', durable=True)


@app.route("/weather_speaker")
def seather_speak():
    message = request.args.get("name")
    channel.basic_publish(exchange='',
	    routing_key='weather',
	    body=message,
	    properties=pika.BasicProperties( delivery_mode = 2,)) # make message persistent
    return "{message} sent".format(message=message)


if __name__ == "__main__":
    app.run()
