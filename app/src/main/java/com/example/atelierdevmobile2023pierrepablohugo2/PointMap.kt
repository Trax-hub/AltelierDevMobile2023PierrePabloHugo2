package com.example.atelierdevmobile2023pierrepablohugo2

class PointMap {
    var storeId: Double
    var nom: String
    var desc: String
    var pictureStore: String
    var adresse: String
    var codePostal: String
    var ville: String
    var latitude: Double
    var longitude: Double

    constructor(storeId: Double, nom: String, desc: String, pictureStore: String, adresse: String, codePostal: String, ville: String, latitude: Double, longitude: Double) {
        this.storeId = storeId
        this.nom = nom
        this.desc = desc
        this.pictureStore = pictureStore
        this.adresse = adresse
        this.codePostal = codePostal
        this.ville = ville
        this.latitude = latitude
        this.longitude = longitude
    }

}