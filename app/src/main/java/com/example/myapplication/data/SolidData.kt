package com.example.myapplication.data

import com.example.myapplication.R


data class Pet(
    val name: String,
    val image_id: Int,
    val detail: String,
    val image: Int
)

data class Story(
    val chapter: Int,
    val subchapter: Int,
    val detail: String
)


object SolidUserData {
    val pets: List<Pet> = listOf(
        Pet(
            "SCP-682",
            1,
            "SCP-682",
            R.drawable.greeting_cat
        ),
        Pet(
            "SCP-1048",
            2,
            "SCP-1048",
            R.drawable.graycat
        )

    )

/*
    fun getAccount(accountName: String?): Account {
        return tasks.first { it.name == accountName }
    }*/
}
