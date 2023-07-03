package com.example.data.db

import org.ktorm.database.Database

object DatabaseConnector {
    val database = Database.connect(
        url = "jdbc:mysql://localhost:3306/coffee_world",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = ""
    )
}