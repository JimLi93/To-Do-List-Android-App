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
            "\n" + "\n" + "\n" +"SCP-682 是隻大型、類似蜥蜴、來源不明之未知生物。該項目似乎極度聰明。SCP-682 似乎對所有生命表現出憎恨，此舉已反覆表現於收容期間之數次訪談。" +
                    "\n" + "SCP-682 持續被觀察到具有極高力量、速度、及反應力，但確切能力水平隨其外觀異動而變化。利用消耗能量或蛻皮，SCP-682 可快速改變其體型。" +
                    "SCP-682 可由攝入的任何物質中獲取能量，甚至是無機物。SCP-682 鼻孔內含之一組過濾鰓似乎有助於消化，可於任何溶液中擷取出有用物質，" +
                    "使其於強酸中仍可不斷再生。SCP-682 之再生與復原能力十分驚人，SCP-682 曾被觀察到於身體 87% 遭到損毀或腐爛之情況下持續移動及言語。",
            R.drawable.scp682
        ),
        Pet(
            "SCP-1048",
            2,
            "\n" + "\n" + "\n" +"SCP-1048是一個小型泰迪熊，大約有33釐米高。通過測試，項目的組成材料和其他無智能的泰迪熊沒有什麼可辨別的不同之處。SCP-1048有能力自主移動，並且通過一些手勢進行交流。它經常通過被人們發現它的可愛之處來表露對他人的友愛。這種友愛通常是通過用其短小的手臂進行摟抱來表現，它也曾被觀察到跳舞、原地跳躍，甚至向清潔人員展現過如同兒童所創作的圖畫。所有的與SCP-1048交流過的基金會人員都對其表露的友愛積極迴應。\n" +
                    "與SCP-1048直接進行交流的嘗試還沒有成功過。雖然它能夠用簡單的手勢來表達“是”或“不”的回答，它並不會對關於其性質或來源的直接詢問做出反應。我們不知道這是因為SCP-1048僅僅是不知道如何回答，還是因為它不想回答。儘管有能力畫畫，它並不會用藝術作品表達除友愛以外的意思，即使是被鼓勵這樣做的時候。" ,
            R.drawable.scp1048
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
