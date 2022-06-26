package com.swu._main.bean

data class BombUserResp(
    val results: List<BombUser>
) {
    fun getUser(): BombUser = results[0]
}

data class BombUser(
    val account: String,
    val createdAt: String,
    val objectId: String,
    val pwd: String,
    val updatedAt: String
)