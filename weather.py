import requests
import json
import yaml
f = open("./config.yaml")
opts = yaml.load(f)
f.close()

api_key = opts["weather"]["api_key"]

def get_weather():
    url =  'https://api.seniverse.com/v3/weather/now.json?key={api_key}&location=beijing&language=zh-Hans&unit=c'.format(api_key=api_key)
    resp = requests.get(url)
    r = json.loads(resp.content.decode('utf-8'))
    return r
