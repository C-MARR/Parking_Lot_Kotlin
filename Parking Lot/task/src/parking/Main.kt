package parking

fun main() {
    var lotNotCreated = true
    while (true) {
        val input = readLine()!!
        when {
            input.contains("create") -> {
                val (command, parkingSpaces) = input.split(" ")
                ParkingLot.createLot(parkingSpaces.toInt())
                lotNotCreated = false
            }
            input == "exit" -> {
                break
            }
            lotNotCreated -> {
                println("Sorry, a parking lot has not been created.")
            }
            input.contains("spot_by_") && !lotNotCreated -> {
                val (command, parameter) = input.split(" ")
                ParkingLot.spotBy(parameter, command)
            }
            input.contains("reg_by_color") && !lotNotCreated -> {
                val (command, color) = input.split(" ")
                ParkingLot.regByColor(color)
            }
            input.contains("leave") && !lotNotCreated -> {
                val (command, parkingSpace) = input.split(" ")
                ParkingLot.leave(parkingSpace.toInt())
            }
            input.contains("park") && !lotNotCreated -> {
                val (command, registrationNumber, color) = input.split(" ")
                val car = Car(color, registrationNumber)
                for (space in ParkingLot.spaces.indices) {
                    if (ParkingLot.spaces[space] == Space(!ParkingLot.occupied) &&
                        !ParkingLot.fullLot) {
                        ParkingLot.park(space + 1, car)
                        break
                    } else if (ParkingLot.fullLot) {
                        println("Sorry, the parking lot is full.")
                        break
                    }
                }
            }
            input == "status" && !lotNotCreated -> {
                ParkingLot.parkingLotStatus()
            }
        }
    }
}
