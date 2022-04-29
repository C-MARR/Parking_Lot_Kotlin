package parking

object ParkingLot {
    const val occupied = true
    var fullLot = false
    var lotSize = 20
    var spaces = MutableList(lotSize) { Space(!occupied) }

    fun park(parkingSpot: Int, vehicle: Vehicle?) {
        if (vehicle != null && spaces[parkingSpot - 1] == Space(!occupied)) {
            spaces[parkingSpot - 1] = Space(occupied, vehicle)
            println("${vehicle.color} ${vehicle.name} parked in spot $parkingSpot.")
            fullLot = checkForFullLot()
        } else {
            println("The Space is Occupied")
        }

    }

    fun leave(parkingSpot: Int, vehicle: Vehicle? = null) {
        if (spaces[parkingSpot - 1] == Space(!occupied)) {
            println("There is no car in spot $parkingSpot.")
            fullLot = checkForFullLot()
        } else {
            spaces[parkingSpot - 1] = Space(!occupied, null)
            println("Spot $parkingSpot is free.")
            fullLot = checkForFullLot()
        }
    }

    private fun checkForFullLot() = spaces.all { it.occupied }

    fun parkingLotStatus() {
        if (spaces.all { !it.occupied } ) {
            println("Parking lot is empty.")
            return
        } else {
            for (space in spaces.indices)
                if (spaces[space].occupied) {
                    println("${space + 1} ${spaces[space].vehicle?.registrationNumber} ${spaces[space].vehicle?.color}")
                }
        }
        return

    }


    fun createLot(parkingSpaces: Int) {
        lotSize = parkingSpaces.toInt()
        spaces = MutableList(lotSize) { Space(!occupied) }
        println("Created a parking lot with $parkingSpaces spots.")
    }

    fun spotBy(parameter: String, command: String) {
        val list = mutableListOf<Int>()
        if (command == "spot_by_reg") {
            spaces.forEachIndexed { num, vehicle ->
                if (vehicle.vehicle != null && vehicle.vehicle.registrationNumber == parameter) {
                    list.add(num + 1)
                }
            }
        } else if (command == "spot_by_color") {
            spaces.forEachIndexed { num, vehicle ->
                if (vehicle.vehicle != null && vehicle.vehicle.color.lowercase() == parameter.lowercase()) {
                    list.add(num + 1)
                }
            }
        }
        if (list.isEmpty()) {
            val output = if (command.contains("color")) "color" else "registration number"
            println("No cars with $output $parameter were found.")
        } else {
            println(list.joinToString(", "))
        }
    }

    fun regByColor(color: String) {
        val list = mutableListOf<String>()
        spaces.forEach {
            if (it.vehicle != null && it.vehicle.color.lowercase() == color.lowercase()) {
                list.add(it.vehicle.registrationNumber)
            }
        }
        if (list.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            println(list.joinToString(", "))
        }
    }

}