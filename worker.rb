#!/usr/bin/env ruby
require 'bunny'

connection = Bunny.new
connection.start

channel = connection.create_channel
exchange = channel.topic("weather")
queue = channel.queue('weather', durable: true)

channel.prefetch(1)

puts ' [*] Waiting for signals. To exit press CTRL+C'

begin
  queue.subscribe(manual_ack: true, block: true) do |delivery_info, _properties, body|
    puts "-- Received #{body}"
    `python3 ./speech.py #{body}`
    channel.ack(delivery_info.delivery_tag)
  end
rescue Interrupt => _
  channel.close
  connection.close
  exit(0)
end
