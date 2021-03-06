package com.funct.project.scooter

import java.time.LocalDateTime

import play.api.libs.json.{JsObject, Json}


/*
{
"id":1,
"coord": {
  "lat": 45.00,
  "long": 2.23
},
"battery": 1,
"km": {
  "total": 20,
  "afterLastCharge": 0
},
"timeInUse": 500,
"lastDayUsed": "2018-01-22T01:21:03.048"
"inUse":false,
"inCharge": false,
"speed": 20
"currentDayTime" : "2018-01-22T01:21:03.048"
}
 */
class Scooter () {
    var id: Int = 0
    var coord: Coord = new Coord()
    var battery: Int = 100
    var km: Km = new Km()
    var timeInUse: Int = 0
    var inUse: Boolean = false
    var inCharge: Boolean = false
    var speed: Int = 0
    var currentDayTime: String = LocalDateTime.now().toString()
    var lastDayUsed : String = "Not used yet"

    def ScooterMove(): Unit ={

        val rand = scala.util.Random
        val speedUpDown = rand.nextBoolean()


        var speed = this.speed
        if(this.speed < 15){
            if(speedUpDown)
                speed = rand.nextInt(30) + 15
        }
        else if(this.speed >= 15 && this.speed < 30) {
            if (speedUpDown)
                speed = rand.nextInt(50-30) + 30 // min 30 - max 49
            else{
                speed = speed - rand.nextInt(speed)+5 // min 6 - max speed
            }
        }
        else if(this.speed >= 30){
            if (!speedUpDown)
                speed = rand.nextInt(30-15)+15 // min 15 - max 29
            else {
                speed = speed + rand.nextInt(50-speed) // min actuel speed - max 49
            }
        }

        this.speed = speed

        var km = 0.000
        var plus = 0.000

        if(this.speed > 0 && this.speed < 15){
            plus = 0.001
            km = 0.008
        }
        else if( this.speed >= 15 && this.speed < 30 ){
            plus = 0.002
            km = 0.016
        }
        else if( this.speed >= 30 && this.speed <= 50 ){
            plus = 0.003
            km = 0.032
        }
        this.km.total = this.km.total + km
        this.km.afterLastCharge = this.km.afterLastCharge + km



        val direction = rand.nextInt(4)

        direction match {
            case 0 =>   this.coord.long = this.coord.long + plus
            case 1 =>   this.coord.long = this.coord.long - plus
            case 2 =>   this.coord.lat = this.coord.lat + plus
            case 3 =>   this.coord.lat = this.coord.lat - plus
        }

        if(this.battery != 0) {
            this.battery = this.battery - 1
            this.inCharge = false
            this.inUse = true
        }
        else if(this.battery == 0) {
            this.battery = 100
            this.inCharge = true
            this.speed = 0
            this.inUse = false
            this.km.afterLastCharge = 0
            this.lastDayUsed = LocalDateTime.now().toString()
        }


        if(this.inUse){

            this.timeInUse = this.timeInUse + 2
        }
        else {
            this.timeInUse = 0
        }



        // if battery = 0
        //

    }

    def toJsonString(): String ={
      val rawJson = """ { "id": """ + this.id + """,""" +
        """ "coord" : { "lat":""" + this.coord.lat + """, "long": """ + this.coord.long +
      """},""" + """ "battery":""" + this.battery + """,  "km": { "total": """  +
        this.km.total + """, "afterLastCharge": """ + this.km.afterLastCharge + """},"""+
      """ "timeInUse": """ + this.timeInUse + """, "lastDayUsed":""""+this.lastDayUsed+"""","inUse": """ + this.inUse + """, "inCharge": """ + this.inCharge +
      """, "speed": """ + this.speed + """, "currentDayTime":""""+this.currentDayTime+""""} """
      return rawJson
    }


}

class Coord(var lat: Double = 0.0,
  var long: Double = 0.0){
}

class Km(var total: Double = 0.0, var afterLastCharge: Double = 0.0){
}

class Date(var day: Int = 1,
  var month: Int = 1,
  var year: Int = 2019){
}