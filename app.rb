require 'sinatra'
require 'bunny'
require 'pry'

connection = Bunny.new(automatically_recover: false)
get '/weather_speaker' do
  connection.start
  channel = connection.create_channel
  message = params[:name]
  queue = channel.queue('weather', durable: true)
  queue.publish(message, persistent: true)
  puts " [x] Sent #{message}"
end
