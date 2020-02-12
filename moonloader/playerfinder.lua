require "lib.moonloader"
require "lib.sampfuncs"

local host, port = "127.0.0.1", 5230
local socket = require("socket")
local weapons = require 'game.weapons'

local HOLD_NUMPAD = "Hold NUMPAD+ to begin"

function main()
  while not isSampAvailable() do wait(0) end

	local connected = false
	local id = nil
	local previous = ""

	while true do
		wait(100)
		if not connected then
			tcp = connect()
			connected = true
		else
			if isKeyDown(107) then
				id, connected = wait_for_id(tcp, previous)
			end
		end
		previous, connected = handle_id(id, tcp, previous)
	end
end

function handle_id(id, tcp, previous)
	if id ~= nil then
		if sampIsPlayerConnected(id) then
			return find_player(id, tcp, previous)
		else
			return send_message("Player " .. id .. " not connected", tcp, previous)
		end
	else
		return send_message(HOLD_NUMPAD, tcp, previous)
	end
end

function find_player(id, tcp, previous)
	local x1, y1, z1 = getCharCoordinates(PLAYER_PED)
	local streamed, ped = sampGetCharHandleBySampPlayerId(id)
	local name = sampGetPlayerNickname(id)
	if not streamed then
		return send_streamed_out_info(id, x1, y1, z1, name, tcp, previous)
	end
	return send_streamed_in_info(ped, id, x1, y1, z1, name, tcp, previous)
end

function send_streamed_out_info(id, x1, y1, z1, name, tcp, previous)
	local x, y, success, distance = get_streamed_out_info(id, x1, y1, z1)
	if not success then
		return send_message("Unable to locate player", tcp, previous)
	end
	return send_message(x .. ";" .. y .. ";" .. name .. ";" .. distance .. "; ; ; ", tcp, previous)
end

function send_streamed_in_info(ped, id, x1, y1, z1, name, tcp, previous)
	local x, y, distance, skin, health, additional_info = get_streamed_info(ped, id, x1, y1, z1)
	return send_message(x .. ";" .. y .. "; " .. name .. ";" .. distance .. ";" .. skin .. ";" .. health .. ";" .. additional_info, tcp, previous)
end

function get_streamed_out_info(i, x1, y1, z1)
	local success, x, y, z = sampGetStreamedOutPlayerPos(i)
	if success then
		distance = math.floor(getDistanceBetweenCoords3d(x1, y1, z1, x, y, z))
		direction = get_direction(x, y, z, x1, y1, z1)
		return x, y, success, distance .. "m " .. " " .. direction
	end
	return nil, nil, success, nil
end

function get_streamed_info(ped, id, x1, y1, z1)
	local x, y, z = getCharCoordinates(ped)
	local distance = math.floor(getDistanceBetweenCoords3d(x1, y1, z1, x, y, z))
	local direction = get_direction(x, y, z, x1, y1 ,z1)
	local skin = getCharModel(ped)
	local health = sampGetPlayerHealth(id)
	local armor = sampGetPlayerArmor(id)
	local additional_info = get_additional_info(ped)
	return x, y, distance .. "m " .. direction, skin, "HP: " .. health .. ", Armor: " ..  armor, additional_info
end

function get_additional_info(ped)
	if isCharInAnyCar(ped) then
		local model = getCarModel(getCarCharIsUsing(ped))
		return getNameOfVehicleModel(model)
	else
		local weapon, _, _ = getCurrentCharWeapon(ped)
		return weapons.get_name(weapon)
	end
end

function connect()
	local connected = false
	local tcp = assert(socket.tcp())
	while not connected do
		tcp:connect(host, port)
		tcp:settimeout(0)
		wait(1000)
		_, connected = send_message(HOLD_NUMPAD, tcp)
	end
	return tcp
end

function get_direction(x, y, z, x1, y1, z1)
	local long = ""
	local lat = ""
	if exceeds_long_threshold(x, y, x1, y1) then
		if y > y1 then long = "NORTH " else long = "SOUTH " end
	end
	if exceeds_lat_threshold(x, y, x1, y1) then
		if x > x1 then lat = "EAST" else lat = "WEST" end
	end
	return long .. lat
end

	function exceeds_long_threshold(x, y, x1, y1)
		return 3 * math.sqrt(math.pow((y - y1), 2)) >= math.sqrt(math.pow((x - x1), 2))
	end

function exceeds_lat_threshold(x, y, x1, y1)
	return math.sqrt(math.pow((y - y1), 2)) <= 3 * math.sqrt(math.pow((x - x1), 2))
end

function send_message(message, tcp, previous)
	if message ~= previous then
		return send_message(message, tcp)
	end
	return message, true
end

function send_message(message, tcp)
	local index, _ = tcp:send(message .. "\n")
	return message, index == string.len(message) + 1
end

function wait_for_id(tcp, previous)
	local input, connected = send_message("ID: ", tcp, previous)
	while isKeyDown(107) and connected do
		wait(10)
		if wasKeyPressed(96) or wasKeyPressed(48) then
			input, connected = send_message(input .. "0", tcp)
		elseif wasKeyPressed(97) or wasKeyPressed(49) then
			input, connected = send_message(input .. "1", tcp)
		elseif wasKeyPressed(98) or wasKeyPressed(50) then
			input, connected = send_message(input .. "2", tcp)
		elseif wasKeyPressed(99) or wasKeyPressed(51) then
			input, connected = send_message(input .. "3", tcp)
		elseif wasKeyPressed(100) or wasKeyPressed(52) then
			input, connected = send_message(input .. "4", tcp)
		elseif wasKeyPressed(101) or wasKeyPressed(53) then
			input, connected = send_message(input .. "5", tcp)
		elseif wasKeyPressed(102) or wasKeyPressed(54) then
			input, connected = send_message(input .. "6", tcp)
		elseif wasKeyPressed(103) or wasKeyPressed(55) then
			input, connected = send_message(input .. "7", tcp)
		elseif wasKeyPressed(104) or wasKeyPressed(56) then
			input, connected = send_message(input .. "8", tcp)
		elseif wasKeyPressed(105) or wasKeyPressed(57) then
			input, connected = send_message(input .. "9", tcp)
		elseif wasKeyPressed(8) then
			if string.len(input) > 4 then
				input, connected = send_message(input:sub(1, -2), tcp, previous)
			end
		end
	end
	if string.len(input) > 4 then
		return tonumber(input:sub(5)), connected
	end
	return nil, connected
end
