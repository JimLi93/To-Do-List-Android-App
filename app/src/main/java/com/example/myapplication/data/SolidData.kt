package com.example.myapplication.data

import androidx.compose.ui.graphics.vector.ImageVector
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
    val detail: List<StoryDetail>,
    val valid: Boolean
)

data class StoryDetail(
    val id: Int,
    val character: String,
    val characterImage: Int,
    val content: String
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

    private val contents: List<StoryDetail> = listOf(
        StoryDetail(
            0,
            "Narrative",
            R.drawable.graycat,
            "Dr. Bright用湯匙慢慢的攪拌咖啡，花了很長的一段時間品味香氣。" +
                    "他和963的聯繫中滿詭異的一點是，他所佔據的每個身體都有著截然不" +
                    "同的感官體驗。顏色看上去如此相異，氣味也能夠隨之帶出不同的情緒" +
                    "，而咖啡…………廉價的即溶咖啡和這個身體異常的合得來。誰知道黑猩" +
                    "猩的味蕾和即溶咖啡的相性如此的好？"
        ),
        StoryDetail(
            1,
            "Dr. Kain",
            R.drawable.doctor,
            "早安，老兄。我有一個好消息和一個壞消息。首先，好消息是：682昨晚又跑了。"
        ),
        StoryDetail(
            2,
            "Dr. Bright",
            R.drawable.doctor,
            "那算是好消息嗎？"
        ),
        StoryDetail(
            1,
            "Dr. Kain",
            R.drawable.doctor,
            "這個嘛，在逃脫的路上幹掉792名安保人員後，他偷了一台車，然後酒駕地開到了兩個州之外。"
        ),
        StoryDetail(
            2,
            "Dr. Bright",
            R.drawable.doctor,
            "在開玩笑吧，那樣怎麼可能算是個好消——"
        ),
        StoryDetail(
            1,
            "Dr. Kain",
            R.drawable.doctor,
            "我還沒說完！682用150哩的時速撞上了一棵樹，安全氣囊剛好故障了。那頭機車大蜥蜴死了！"
        ),
        StoryDetail(
            2,
            "Dr. Bright",
            R.drawable.doctor,
            "哦，當然啊。酒駕！我們怎麼會沒想到這一招呢？那麼，壞消息是什麼？"
        ),
        StoryDetail(
            1,
            "Dr. Kain",
            R.drawable.doctor,
            "那是你的車。"
        )
    )

    val stories: List<Story> = listOf(

        //chapter1
        Story(
            1,
            1,
            contents,
            true
        ),
        Story(
            1,
            2,
            contents,
            true
        ),
        Story(
            1,
            3,
            contents,
            false
        ),
        Story(
            1,
            4,
            contents,
            false
        ),
        Story(
            1,
            5,
            contents,
            false
        ),

        //chapter2
        Story(
            2,
            1,
            contents,
            false
        ),
        Story(
            2,
            2,
            contents,
            false
        ),
        Story(
            2,
            3,
            contents,
            false
        ),
        Story(
            2,
            4,
            contents,
            false
        ),
        Story(
            2,
            5,
            contents,
            false
        )


    )

/*
    fun getAccount(accountName: String?): Account {
        return tasks.first { it.name == accountName }
    }*/
}
