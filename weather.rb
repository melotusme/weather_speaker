require 'rest-client'
require 'json'
require 'yaml'
opts = YAML.load_file("./config.yaml")
api_key = opts["weather"]["api_key"]

def get_weather
  url =  "https://api.seniverse.com/v3/weather/now.json?key=#{api_key}&location=beijing&language=zh-Hans&unit=c"
  resp = RestClient.get url
  r = JSON.parse resp
end
