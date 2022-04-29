package parking

abstract class Vehicle {
    abstract val color: String
    abstract val name: String
    abstract val registrationNumber: String

}

class Car(override val color: String, override val registrationNumber: String,
          override val name: String = "car") : Vehicle() {

}
