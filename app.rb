require 'bundler/setup'
Bundler.require(:default)


connection = Bunny.new(automatically_recover: false)

get '/weather_speaker' do
  connection.start
  channel = connection.create_channel
  message = params[:name]
  queue = channel.queue('weather', durable: true)
  queue.publish(message, persistent: true)
  return " [x] Sent: #{message}"
end
