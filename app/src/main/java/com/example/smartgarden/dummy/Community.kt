package com.example.smartgarden.dummy

import android.os.Parcelable
import com.example.smartgarden.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Community (
    val user: String,
    val title: String,
    val desc: String,
    val photo: Int,
    val date: String,
    val detail: String,
): Parcelable

lateinit var communityArrayList: ArrayList<Community>

lateinit var user: Array<String>
lateinit var image: Array<Int>
lateinit var title: Array<String>
lateinit var descriptions: Array<String>
lateinit var date: Array<String>
lateinit var detail: Array<String>

fun dataInitialize() {
    communityArrayList = arrayListOf()

    user = arrayOf(
        "Rijal",
        "Iwan",
        "Ahmad",
        "Zaki",
        "Asyrof",
    )

    image = arrayOf(
        R.drawable.daun_kuning,
        R.drawable.kantong_semar,
        R.drawable.bayam,
        R.drawable.pohon_mangga,
        R.drawable.daun_bawang,
    )
    title = arrayOf(
        "Bagaimana Cara Mengatasinya?",
        "Dimana saya bisa mendapatkan tanaman ini?",
        "Rawat murah hasil mahal",
        "Kenapa tanaman mangga saya susah berbuah?",
        "Tanaman penghasil uang",
    )
    descriptions = arrayOf(
        "Daun Mengering dan kuning",
        "Saya ingin memiliki tanaman ini, karena di wilayah saya tidak terdapat tanaman tersebut.",
        "Saya memiliki tanaman yang mudah untuk dirawat dan bisa menghasilkan uang dari merawat tanaman ini.",
        "Tanaman mangga saya susah berbuah, saya tinggal di daerah Sulawesi Tenggara yang umumnya tanah subur.",
        "Tanaman ini memiliki nilai yang mahal apabila dijual, karena banyak dikonsumsi orang.",
    )
    detail = arrayOf(
        "1. Gunakan Pestisida Tanaman\n" +
                "Cara pertama yang bisa kamu lakukan adalah dengan menggunakan pestisida untuk tanaman hias.\n" +
                "2. Sediakan Tanaman Penangkal Hama\n" +
                "Memiliki tanaman penangkal hama juga bisa menjadi solusi untuk menghindari hama di tanaman.\n" +
                "3. Menjaga Kualitas Tanah\n" +
                "Menjaga kualitas tanah agar tetap subur juga menjadi salah satu cara terampuh untuk membunuh hama ulat bulu, lho.\n" +
                "4. Gunakan Perangkap Lem Serangga\n" +
                "Pernahkah kamu melihat pot tanaman yang diberikan botol plastik bekas air mineral",

        "Mungkin teman-teman bisa memberitahu ada di wilayah mana ya tanaman ini banyak dijual.\n" +
                "Saya ingin merawatnya di rumah, karena kebetulan juga saya pencinta tanaman langka",

        "Di sini saya ingin berbagi pengalaman saya dari merawat tanaman Bayam yang mungkin sudah banyak diketahui banyak orang.\n" +
                "Cara panen bayam dilakukan dengan dengan memetik pucuk-pucuk daun atau ujung-ujung cabang, dapat dilakukan sacra manual dengan tangan atau menggunakan alat.\n" +
                "Kalian bisa melihara bayam di rumah dengan peralatan sederhana, seperti botol bekas atau galon bekas, kalian juga bisa membuatnya dari pipa paralon.",

        "Saya sudah merawat pohon mangga ini sekitar 1 tahun lebih. Hingga sekarang belum berbuah.\n" +
                "Mungkin teman-teman bisa memberitahu kenapa bisa alasan tidak bisa berbuah",

        "Saya memiliki rekomendasi tanaman yang bisa menghasilkan cuan dari hasil penjualan dari tanaman Daun Bawang.",
    )
    date = arrayOf(
        "Minggu, 03 Desember 2023",
        "Minggu, 03 Desember 2023",
        "Minggu, 03 Desember 2023",
        "Minggu, 03 Desember 2023",
        "Minggu, 03 Desember 2023",
    )
    for (i in image.indices) {
        val community = Community(user[i],title[i],descriptions[i],image[i], date[i], detail[i])
        communityArrayList.add(community)
    }
}